package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   * val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  /** 추가 Test code 작성 */
  test("intersect contains only common elements") {
    new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Intersect 1") // s1과 s2는 공통 요소가 없음
      assert(!contains(s, 2), "Intersect 2")
      assert(!contains(s, 3), "Intersect 3")
    }

    new TestSets {
      val s = intersect(s1, s1)
      assert(contains(s, 1), "Intersect Same Set") // s1과 s1의 교집합은 s1과 같음
    }
  }

  test("diff contains elements in s1 but not in s2") {
    new TestSets {
      val s = diff(s1, s2)
      assert(contains(s, 1), "Diff 1") // s1에는 1이 있고, s2에는 없음
      assert(!contains(s, 2), "Diff 2")
      assert(!contains(s, 3), "Diff 3")
    }

    new TestSets {
      val s = diff(s1, s1)
      assert(!contains(s, 1), "Diff Same Set") // 동일한 집합의 차집합은 빈 집합
    }
  }

  test("filter returns only elements that satisfy the predicate") {
    new TestSets {
      val s = filter(s1, x => x == 1)
      assert(contains(s, 1), "Filter 1") // s1에서 x == 1인 요소만 남음
      assert(!contains(s, 2), "Filter 2")
    }

    new TestSets {
      val s = filter(s2, x => x > 1)
      assert(contains(s, 2), "Filter Greater than 1") // s2에서 x > 1인 요소만 남음
      assert(!contains(s, 1), "Filter Greater than 1 - not in")
    }
  }

  test("map transforms each element of the set") {
    new TestSets {
      val s = map(s1, x => x * 2)
      assert(contains(s, 2), "Map to 2") // s1의 요소 1이 2로 변환됨
      assert(!contains(s, 1), "Map 1 not present")
    }

    new TestSets {
      val s = map(s2, x => x * x)
      assert(contains(s, 4), "Map to 4") // s2의 요소 2가 제곱되어 4가 됨
      assert(!contains(s, 2), "Map 2 not present")
    }
  }
}

