// Part 5: Pattern Matching
// types: Number, Sum, Prod, Var
// eval,show, simplify

// Observation: the sole purpose of test and accessor functions is to reverse the construction process.
// Recover what kind of constructor we used and what the arguments were.
// Situation is so common in functional languages. So automated with pattern matching.

// In scala we get Pattern Matching by way of case classes.

// Case class definition
// Like normal class def but preceded by case
// Defines a trait expression and two concrete classes
// But get some added functionality by adding modifier case
// It also implicitly defines companion objects with apply methods.
// so now you can write Number(1) instead of new Number(1)
// Pattern matching is a generalization of switch from C/Java to class hierarchies.
object patmat {

  trait Expr {
    // Can also do:
    // Better if all you do is create new classes.
    // However, if what we do is create lots of new methods,
    // but class hierarchy is stable, then pattern matching has advantages.
    // Each new method in pattern matching is just a local change,
    // where as new method requires new incrementation in each subclass.
    def eval: Int = this match {
      case Number(n) => n
      case Sum(e1, e2) => e1.eval + e2.eval
    }
  }

  case class Number(n: Int) extends Expr

  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr
  case class Var(x: String) extends Expr

  // selector e
  // keyword match
  // keyword case pat => expr,
  // ...
  // case pattern => expr_n

  // e is matched against all the pattern.
  // first match leads to expr being evalualted
  // if none match get a MatchError

  def eval(e: Expr): Int = e match {
    // Number(_) same as Number(n) but does not extract value of n.
    // Examples:
    // 1, true, "abc", Constants: N (val N = 2)
    // Sum(Number(1), Var(x)) => x
    // Variables must always begin with lower case, Constants always upper case.
    // Same variable name can only appear once in a pattern.
    // Sum(x, x) not valid, need to write Sum(x,y)

    // If a pattern matches the expression is re-written to right hand side of pattern match.

    case Number(n) => n
    case Sum(e1, e2) => eval(e1) + eval(e2)
  }

  // A constructor pattern C(p1,...,pn) matches all values of type C (or a subtype) that have been constructed with arguments matching patterns p1,...,pn
  // A variable pattern x matches any value, and binds the name of the variable to this value.
  // A constant pattern c matches values that are equal to c (in the sense of ==)


  def show(e: Expr): String = e match {
    case Number(x) => x.toString
    case Sum(l, r) => show(l) + " + " + show(r)
    case Prod(Sum(ll, lr), Sum(rl, rr)) => "(" + show(Sum(ll, lr)) + ") * (" + show(Sum(rl, rr)) + ")"
    case Prod(Sum(ll, lr), r) => "(" + show(Sum(ll, lr)) + ") * " + show(r)
    case Prod(l, Sum(rl, rr)) => "(" + show(l) + " * " + show(Sum(rl, rr))
    case Prod(l, r) => show(l) + " * " + show(r)
    case Var(n) => n
  }
  show(Sum(Number(1), Sum(Number(2), Number(3))))
  // 2 * x + y
  show(Sum(Prod(Number(2), Var("x")), Var("y")))
  // (2 + x) * y
  show(Prod(Sum(Number(2), Var("x")), Var("y")))
}




