package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c < 0)
      0
    else if (c > r)
      0
    else if (c == 0 && r == 0)
      1
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   *
   * ()())
   */
  def balance(chars: List[Char]): Boolean = {
    def balance_count(chars: List[Char]): Long = {
      if (chars.isEmpty)
        0
      else {
        val head = chars.head
        val head_unit = head match {
          case'(' => 1
          case ')'=> -1
          case _ => 0
        }
        val tail_balance = balance_count(chars.tail)
        if (tail_balance > 0)
          tail_balance
        else
          head_unit + tail_balance
      }
    }
    balance_count(chars) == 0
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0) {
      0
    } else if (money == 0) {
      1
    } else if (coins.isEmpty) {
      0
    } else {
      countChange(money - coins.head, coins) +
      countChange(money, coins.tail)
    }
  }
}
