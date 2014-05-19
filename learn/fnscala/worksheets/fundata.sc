
class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be non-zero")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  private val g : Int = gcd(x, y)

  val numer : Int = x / g
  val denom : Int = y / g

//  val numer = x
 // val denom = y

  def makeString = {
    //val g = gcd(numer, denom)

  //  numer/g + "/" + denom/g

    numer + "/" + denom
  }

  override def toString = makeString

  def + (that: Rational) : Rational =
    new Rational(numer * that.denom + that.numer * denom,
      denom * that.denom)
  def unary_- : Rational = new Rational(-numer, denom)
  def - (that: Rational) = this + -that
  def < (that: Rational) = numer * that.denom < that.numer * denom
  def max(that: Rational) = if (this < that) that else this

}
println("Welcome to the Scala worksheet")

val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)
x.makeString
x + y
x - y - z
y + y
x < y
x.max(y)
new Rational(2)
x + new Rational(52)
//val strange = new Rational(1, 0)
//strange.add(strange)

x - y - z

//val +?%& = x

//+?%&


