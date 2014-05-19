object intsets {
  println("welcome!")

  val t1 = new NonEmpty(3, Empty, Empty)
  val t2 = t1 incl 4


  abstract class IntSet {
    /**
     * @param x : the int value to include in the set.
     */
    def incl(x: Int): IntSet

    /**
     * @param x : the int to test existence in the set.
     * @return true if the int x exists in the set.
     */
    def contains(x: Int): Boolean

    def union(other: IntSet): IntSet

  }

  class NonEmpty(elem: Int, left: IntSet, right:IntSet) extends IntSet {


    def contains(x: Int): Boolean =
      if (x < elem) left contains x
      else if (x > elem) right contains x
      else true

    def incl(x: Int): IntSet =
      if (x < elem) new NonEmpty(elem, left incl x, right)
      else if (x > elem) new NonEmpty(elem, left, right incl x)
      else this


    override def toString = "{" + left + elem + right + "}"

    def union(other: IntSet): IntSet =
      ((left union right) union other) incl elem
  }

  object Empty extends IntSet {
    def contains(x: Int): Boolean = false
    def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
    override def toString = "."
    def union(other: IntSet): IntSet = other
  }

  object Hello {
    def main(args: Array[String]) = println("hello world!")
  }
}
