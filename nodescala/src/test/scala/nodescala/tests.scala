package nodescala

import scala.language.postfixOps
import scala.util.{Try, Success, Failure}
import scala.collection._
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.async.Async.{async, await}
import org.scalatest._
import NodeScala._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class NodeScalaSuite extends FunSuite {

  test("A Future should always be completed") {
    val always = Future.always(517)

    assert(Await.result(always, 0 nanos) == 517)
  }

  test("A Future should never be completed") {
    val never = Future.never[Int]

    try {
      Await.result(never, 1 second)
      assert(false)
    } catch {
      case t: TimeoutException => // ok!
    }
  }

  class DummyExchange(val request: Request) extends Exchange {
    @volatile var response = ""
    val loaded = Promise[String]()
    def write(s: String) {
      response += s
    }
    def close() {
      loaded.success(response)
    }
  }

  class DummyListener(val port: Int, val relativePath: String) extends NodeScala.Listener {
    self =>

    @volatile private var started = false
    var handler: Exchange => Unit = null

    def createContext(h: Exchange => Unit) = this.synchronized {
      assert(started, "is server started?")
      handler = h
    }

    def removeContext() = this.synchronized {
      assert(started, "is server started?")
      handler = null
    }

    def start() = self.synchronized {
      started = true
      new Subscription {
        def unsubscribe() = self.synchronized {
          started = false
        }
      }
    }

    def emit(req: Request) = {
      val exchange = new DummyExchange(req)
      if (handler != null) handler(exchange)
      exchange
    }
  }

  class DummyServer(val port: Int) extends NodeScala {
    self =>
    val listeners = mutable.Map[String, DummyListener]()

    def createListener(relativePath: String) = {
      val l = new DummyListener(port, relativePath)
      listeners(relativePath) = l
      l
    }

    def emit(relativePath: String, req: Request) = this.synchronized {
      val l = listeners(relativePath)
      l.emit(req)
    }
  }

  test("Server should serve requests") {
    val dummy = new DummyServer(8191)
    val dummySubscription = dummy.start("/testDir") {
      request => for (kv <- request.iterator) yield (kv + "\n").toString
    }

    // wait until server is really installed
    Thread.sleep(500)

    def test(req: Request) {
      val webpage = dummy.emit("/testDir", req)
      val content = Await.result(webpage.loaded.future, 1 second)
      val expected = (for (kv <- req.iterator) yield (kv + "\n").toString).mkString
      assert(content == expected, s"'$content' vs. '$expected'")
    }

    test(immutable.Map("StrangeRequest" -> List("Does it work?")))
    test(immutable.Map("StrangeRequest" -> List("It works!")))
    test(immutable.Map("WorksForThree" -> List("Always works. Trust me.")))

    dummySubscription.unsubscribe()
  }

  // 추가 테스트 1: 서버가 비동기적으로 응답하는지 검증
  test("Server should respond asynchronously") {
    val dummy = new DummyServer(8191)
    val dummySubscription = dummy.start("/asyncTest") {
      request => Iterator("Asynchronous Response")
    }

    // 비동기적으로 클라이언트 요청 보내기
    val responseFuture = Future {
      val response = dummy.emit("/asyncTest", immutable.Map())
      Await.result(response.loaded.future, 1 second)
    }

    val content = Await.result(responseFuture, 2 seconds)
    assert(content == "Asynchronous Response", s"Response was '$content', expected 'Asynchronous Response'")

    dummySubscription.unsubscribe()
  }

  // 추가 테스트 2: 서버가 잘못된 경로에서 응답하지 않는지 확인
  test("Server should not respond to non-existent path") {
    val dummy = new DummyServer(8191)
    val dummySubscription = dummy.start("/validPath") {
      request => Iterator("Valid Path Response")
    }

    // 잘못된 경로로 요청 보내기
    try {
      val response = dummy.emit("/invalidPath", immutable.Map())
      val content = Await.result(response.loaded.future, 1 second)
      assert(false, s"Expected no response but received: '$content'")
    } catch {
      case _: NoSuchElementException => // 정상적인 동작, 응답이 없어야 함
    }

    dummySubscription.unsubscribe()
  }

}




