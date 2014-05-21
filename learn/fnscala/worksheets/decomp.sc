object decomp {

  trait Expr {
    // classification
    def isNumber: Boolean
    def isSum: Boolean

    // accessor
    def numValue: Int
    def leftOp: Expr
    def rightOp: Expr

    // Could do
    def eval: Int

    // Part3.1: What if we want to def show: String?
    // OO does not always work require non-local knowledge of tree.
  }

  class Number(n: Int) extends Expr {
    def isNumber: Boolean = true
    def isSum: Boolean = false
    def numValue: Int = n
    def leftOp: Expr = throw new Error("Number.leftOp")
    def rightOp: Expr = throw new Error("Number.rightOp")

    def eval: Int = n
  }

  class Sum(e1: Expr, e2: Expr) extends Expr {
    def isNumber: Boolean = false
    def isSum: Boolean = true
    def numValue: Int = throw new Error("Sum.numValue")
    def leftOp: Expr = e1
    def rightOp: Expr = e2

    def eval: Int= e1.eval + e2.eval
  }

  def eval(e: Expr): Int = {
    // Problem: Writing all these classification and accessor functions quickly becomes tedious.
    if (e.isNumber) e.numValue
    else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
    else throw new Error("Unknown Expression " + e)
  }

  /*
  Part 2:
  Quadradic explosian of classification tests
  class Prod(e1: Expr, e2: Expr) extends Expr {}
  class Var(x: String) extends Expr {}
  Unsafe, low-level
  */

  // Part2.2: Recommend to stay away from typecasts
  def eval2(e: Expr): Int =
    if (e.isInstanceOf[Number])
      e.asInstanceOf[Number].numValue
    else if (e.isInstanceOf[Sum])
      eval(e.asInstanceOf[Sum].leftOp) +
      eval(e.asInstanceOf[Sum].rightOp)
    else throw new Error("Unknown expression " + e)

  // Part4
  // And what if you wanted to simplify the expressions say using the rule:
  // a * b + a * c -> a * (b + c)
  // Problem this is a non-local simplification. It cannot be encapsulated in the methood of a single object.
  // You need to test and access methods for all the different classes.

}

