object lists {
  // Next series: close look at Collections
  // Immutable collections in purely functional programs
  // Enable expression high-level and concise way with high chance of being correct
  // Start off with lists.
  // Fundamental data structure.
  // Now going to show how lists are defined in scala standard library and what kinds of operations they support.
  // See how they are defined and what you can do with them.
  val fruit = List("apples", "oranges", "pairs")
  val nums = List(1, 2, 3, 4)
  val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
  val empty = List()
  // Two important differences between lists and arrays.
  // Lists are immutable.
  // Lists are recursive; while arrays are flat.
  val fruit2: List[String] = List("apples", "oranges", "pairs")
  val nums2: List[Int] = List(1, 2, 3, 4)
  val diag2: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
  val empty2: List[Nothing] = List()
  // All lists are constructed from empty list Nil
  // and the construction operation :: (pronounced cons)
  // x ::: xs gives a new list with first element x, followed by the element xs.
  val fruit3 = "apples" :: ("oranges" :: ("pears" :: Nil))
  val nums3 = 1 :: (2 :: (3 :: (4 :: Nil)))
  val empty3 = Nil
  // Similar to Cons(x, xs) same as x :: xs
  // Convention. Operators ending in ":" assciate to the right.
  // A :: B :: C is interpreted as A :: (B :: C)
  val nums4 = 1 :: 2 :: 3 :: 4 :: Nil
  // Operators ending in ":" are also different in that they are seen as method calls of the right-hand operand
  val nums5 = Nil.::(4).::(3).::(2).::(1)
  // :: =~ prepend

  // All operations on lists can be expressed in terms of three operations
  // head : first element
  // tail : the list of all elemennts except if first
  // isEmpty: true if list is empty, false otherwise
  fruit.head == "apples"
  fruit.tail.head == "oranges"
  diag3.head == List(1, 0, 0)
  //empty.head == throw new NoSuchElementExeption("head of empty list")

  // It is also possible to decompose lists with pattern matching.
  //
  // Nil : the Nil constant
  // p :: ps : a Pattern matching a list with a head matching p and a tail matching ps
  // List(p1, ... pn) same as p1 :: ... :: pn :: Nil
  def matcher(l: List[Int]): Int = {
    l match {
      case 1 :: 2 :: xs => 3
      case x :: y :: z => 42
      case x :: Nil => 4
      case List(x) => 5
      case List() => 6
      case 2 :: xs => 7
    }
  }
  val myList = List[Int](9, 8, 3, 92, 513)
  matcher(myList)
  // One way to sort the list List(7, 3, 9, 2) is to sort the tail
  // List(3, 9, 2) to obtain List(2, 3, 9)
  // Next step is to add 7 in the right place.
  def isort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case y :: ys => insert(y, isort(ys))
  }
  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => x :: Nil
    case y :: ys => if (x < y) x :: y :: ys else y :: insert(x, ys)
  }

  val myNums = List(7, 3, 9, 2)
  isort(myNums)

  // Complexity is N*N
}

