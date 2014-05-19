def sumInts(a: Int, b:Int): Int =
  if (a > b) 0 else a + sumInts(a + 1, b)

def cube(x: Int): Int = x * x * x
def sumCubes(a: Int, b: Int): Int =
  if (a > b) 0 else cube(a) + sumCubes(a + 1, b)
sumInts(3, 5)
def sumFactorials(a: Int, b:Int): Int =
  if (a > b) 0 else fact(a) + sumFactorials(a + 1, b)
def fact(x: Int): Int =
  if (x == 1) x else x * fact(x-1)

sumFactorials(3, 5)
def sum(f: Int => Int, a: Int, b: Int): Int =
  if (a > b) 0
  else f(a) + sum(f, a + 1, b)
def id(x : Int): Int = x
def cuber(x: Int): Int = x * x * x
sum(id, 3, 5)
sum(cuber, 3, 5)
sum((x: Int) => x * x * x, 3, 5)
sum(x => x * x * x, 3, 5)
// (def f(x : Int): Int = x * x * x); f
sum(x => x, 3, 5)
def sumInts2(a: Int, b:Int) = sum(x => x, a, b)

sumInts2(3, 5)
def sum2(f: Int => Int, a: Int, b:Int): Int = {
  def loop(a: Int, acc:Int): Int = {
    if (a > b) acc
    else loop(a + 1, f(a) + acc)
  }
  loop(a, 0)
}
sum2(x => x * x, 3, 5)
def sum3(f: Int => Int)(a: Int, b:Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, f(a) + acc)
  }
  loop(a, 0)
}
sum3(x => x * x)(3, 5)

val raf = sum3(x => x * x)_

raf(3, 5)




def sum4(f: Int => Int): (Int, Int) => Int = {
  def sumF(a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sumF(a + 1, b)
  }
  sumF
}

sum4(x => x * x)(3, 5)

def product(f: Int=>Int)(a: Int, b:Int): Int = {
  if (a > b) 1
  else {
    f(a) * product(f)(a + 1, b)
  }
}

product(x => x * x)(3, 4)

def fact2(n: Int) = product(x => x)(1, n)
fact2(5)

def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b:Int): Int = {
  if (a > b) zero
  else combine(f(a), mapReduce(f, combine, zero)(a+1,b))
}

def product2(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)

product2(x => x * x)(3, 5)


