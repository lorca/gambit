object booleans {
  abstract class Boole {
    def ifThenElse[T](t: => T, e: => T): T
    def && (x: => Boole): Boole = ifThenElse(x, False)
    def || (x: => Boole): Boole = ifThenElse(True, x)
    def unary_!(): Boole = ifThenElse(False, True)
    def == (x: Boole): Boole = ifThenElse(x, x.unary_!)
    def != (x: Boole): Boole = ifThenElse(x.unary_!, x)
    def < (x: Boole): Boole = ifThenElse(False, x)
  }
  object False extends Boole {
    def ifThenElse[T](t: => T, e: => T): T = e
  }
  object True extends Boole {
    def ifThenElse[T](t: => T, e: => T): T = t
  }
  True < False
  False < True

  // Peano numbers
  abstract class Nat {
    def isZero: Boole
    def predecessor: Nat
    def successor: Nat
    def + (that: Nat): Nat
    def - (that: Nat): Nat
  }
  object Zero extends Nat {
    def isZero: Boole = True
    def predecessor: Nat = throw new Error("0.predecessor");
    def successor: Nat = new Succ(Zero)
    def +(that: Nat): Nat = that
    def -(that: Nat): Nat = (that.isZero == True).ifThenElse(this, throw new Error("negative number"))
  }
  class Succ(n: Nat) extends Nat {
    def isZero: Boole = False
    def predecessor: Nat = (n.isZero == True).ifThenElse(Zero, n)
    def successor: Nat = new Succ(this)
    //def add(result: Nat, times: Nat): Nat =
    //  (times.isZero == True).ifThenElse(result, add(result.successor, times.predecessor))
    //def sub(result: Nat, times: Nat): Nat =
    //  (times.isZero == True).ifThenElse(result, sub(result.predecessor, times.predecessor))
    //def +(that: Nat): Nat = add(this, that)
    //def -(that: Nat): Nat = sub(this, that)

    def +(that: Nat) = new Succ(n + that)
    def -(that: Nat) = (that.isZero == True).ifThenElse(this, n - that.predecessor)
  }
  ((Zero + Zero.successor) + Zero.successor).predecessor.predecessor


}

