package streams

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Bloxorz._

@RunWith(classOf[JUnitRunner])
class BloxorzSuite extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) => move match {
        case Left => block.left
        case Right => block.right
        case Up => block.up
        case Down => block.down
      }
    }
  }

  trait Level1 extends SolutionChecker {
      /* terrain for level 1*/

    val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  }

  test("terrain function level 1") {
    new Level1 {
      assert(terrain(Pos(0,0)), "0,0")
      assert(!terrain(Pos(4,11)), "4,11")
    }
  }

  test("findChar level 1") {
    new Level1 {
      assert(startPos == Pos(1,1))
    }
  }

  test("optimal solution for level 1") {
    new Level1 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
    }
  }

  test("optimal solution matches expected moves") {
    new Level1 {
      assert(solution == optsolution,
        s"Expected: $optsolution, but got: $solution")
    }
  }

  test("neighborsWithHistory test") {
    new Level1 {
      val start: Block = Block(startPos, startPos)
      val neighbors: Stream[(Block, List[Move])] = neighborsWithHistory(start, List(Left, Up))

      assert(neighbors.contains(
        (Block(Pos(1, 2), Pos(1, 3)), List(Right, Left, Up))
      ), "Right move failed")

      assert(neighbors.contains(
        (Block(Pos(2, 1), Pos(3, 1)), List(Down, Left, Up))
      ), "Down move failed")
    }
  }

  test("newNeighborsOnly test") {
    new Level1 {
      val start: Block = Block(startPos, startPos)
      val explored: Set[Block] = Set(start)
      val newNeighbors: Stream[(Block, List[Move])] = newNeighborsOnly(
        neighborsWithHistory(start, List()), explored
      )

      assert(newNeighbors.forall { case (block, _) => block != start },
        "Explored block not filtered")
    }
  }

  test("isStanding test") {
    new Level1 {
      val block1: Block = Block(Pos(1, 1), Pos(1, 1))  // Standing block
      val block2: Block = Block(Pos(1, 1), Pos(1, 2))  // Not standing

      assert(block1.isStanding, "Block1 should be standing")
      assert(!block2.isStanding, "Block2 should not be standing")
    }
  }

  test("isLegal test with edge cases") {
    new Level1 {
      val legalBlock: Block = Block(Pos(1, 1), Pos(1, 1))
      val illegalBlock: Block = Block(Pos(4, 11), Pos(4, 11))
      val edgeBlock: Block = Block(Pos(0, 0), Pos(0, 0))
      val outOfBoundsBlock: Block = Block(Pos(-1, 0), Pos(0, 0))

      assert(legalBlock.isLegal, "Block should be legal")
      assert(!illegalBlock.isLegal, "Block should be illegal")
      assert(edgeBlock.isLegal, "Edge block should be legal")
      assert(!outOfBoundsBlock.isLegal, "Out of bounds block should be illegal")
    }
  }
}
