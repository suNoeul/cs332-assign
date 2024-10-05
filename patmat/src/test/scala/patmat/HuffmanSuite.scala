package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    val t3 = Fork(Leaf('a', 5), Fork(Leaf('b', 3), Leaf('c', 2), List('b', 'c'), 5), List('a', 'b', 'c'), 10)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  //============ test code 추가 ============//

  test("decode secret message") {
    val expectedMessage = "huffmanestcool"  // 비트 시퀀스 해석 결과
    assert(decodedSecret.mkString === expectedMessage)
  }

  test("encode simple text using Huffman tree") {
    new TestTrees {
      assert(encode(t1)("ab".toList) === List(0, 1))
      assert(encode(t2)("abd".toList) === List(0, 0, 0, 1, 1))
    }
  }

  test("quickEncode simple text using code table") {
    new TestTrees {
      assert(quickEncode(t1)("ab".toList) === List(0, 1))
      assert(quickEncode(t2)("abd".toList) === List(0, 0, 0, 1, 1))
    }
  }

  test("codeBits for specific characters in code table") {
    new TestTrees {
      val table = convert(t1)
      assert(codeBits(table)('a') === List(0))
      assert(codeBits(table)('b') === List(1))
    }
  }

  test("convert Huffman tree to code table") {
    new TestTrees {
      val table = convert(t2)
      assert(table === List(('a', List(0, 0)), ('b', List(0, 1)), ('d', List(1))))
    }
  }

  // 추가 테스트 3: 더 복잡한 트리 구조 테스트
  test("convert Huffman tree to code table for more complex tree") {
    new TestTrees {
      val table = convert(t3)
      assert(table === List(('a', List(0)), ('b', List(1, 0)), ('c', List(1, 1))))
    }
  }

  test("merge two code tables") {
    val table1: CodeTable = List(('a', List(0)), ('b', List(1)))
    val table2: CodeTable = List(('c', List(0, 0)), ('d', List(0, 1)))

    val mergedTable = mergeCodeTables(table1, table2)
    assert(mergedTable === List(('a', List(0)), ('b', List(1)), ('c', List(0, 0)), ('d', List(0, 1))))
  }
}
