import example.Lists

object Main extends App {
  val numbers = List(1, 2, 3, 4, 5)
  println("Sum of numbers: " + Lists.sum(numbers))
  println("Max of numbers: " + Lists.max(numbers))
}
