

import java.util.NoSuchElementException
object scratch {

  trait List[T] {
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
    def nth(n : Int) : T
  }

  class Cons[T](val head: T, val tail: List[T]) extends List[T] {
    def isEmpty = false
    def nth(n: Int) : T = {
      if (n == 0) head else tail.nth(n-1)
    }
    override def toString = "[" + head + "," + tail + "]"
  }
  class Nil[T] extends List[T] {
    def isEmpty = true
    def head: Nothing = throw new NoSuchElementException("Nil.head")
    def tail: Nothing = throw new NoSuchElementException("Nil.tail")
    def nth(n: Int) = throw new IndexOutOfBoundsException("Nil.nth")
    override def toString = "."
  }
  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
  val r = singleton(1)
  singleton[Boolean](true)

  val p = new Cons(5, new Cons(4, r))

  object List {
    def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))
    def apply[T]() = new Nil
  }

  val k = List()

  val g = List(2, 3)

}

