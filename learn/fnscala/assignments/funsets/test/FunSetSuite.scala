package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

    val negBoundSet = singletonSet(-1000)
    val s0 = singletonSet(0)

  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("(1) diff (2) contains 1 and nothing else") {
    new TestSets {
      val s = diff(s1, s2)
      assert(contains(s, 1), "Diff 1")
      assert(!contains(s, 2), "Diff 2")
      assert(!contains(s, 3), "Diff 3")
    }
  }

  test("(1,2) diff (3,1) contains 2 and nothing else") {
    new TestSets {
      val a = union(s1, s2)
      val b = union(s3, s1)

      val s = diff(a, b)

      assert(!contains(s, 1), "Diff2 1")
      assert(contains(s, 2), "Diff2 2")
      assert(!contains(s, 3), "Diff2 3")
    }
  }

  test("(1,2) intersect (3,1) intersect contains 1 and nothing else") {
    new TestSets {
      val a = union(s1, s2)
      val b = union(s3, s1)

      val s = intersect(a, b)

      assert(contains(s, 1), "Intersect 1")
      assert(!contains(s, 2), "Intersect 2")
      assert(!contains(s, 3), "Intersect 3")
    }
  }

  test("filter (1,2,3), x>1 contains (2,3)") {
    new TestSets {
      val a = union(s1, s2)
      val b = union(a, s3)

      val s = filter(b, x => x > 1)

      assert(!contains(s, 1), "Intersect 1")
      assert(contains(s, 2), "Intersect 2")
      assert(contains(s, 3), "Intersect 3")
    }
  }


  test("forall (1,2,3), x>1 returns false") {
    new TestSets {
      val a = union(s1, s2)
      val b = union(a, s3)

      assert(!forall(b, x => x > 1), "Forall")
    }
  }

  test("forall (1,2,3), x<4 returns true") {
    new TestSets {
      val a = union(s1, s2)
      val b = union(a, s3)

      assert(forall(b, x => x < 4), "Forall")
    }
  }

  test("forall (-1000, 0), x<1000 returns true") {
    new TestSets {
      val a = union(negBoundSet, s0)

      assert(forall(a, x => x < 1000), "Forall")
    }
  }

  test("forall (1,2,3)->even returns true") {
    new TestSets {
      val a = union(s1, s2)
      val b = union(a, s3)

      val c = filter(b, x => x % 2 == 0)

      assert(forall(c, x => x % 2 == 0), "Forall")
    }
  }

  test("exists (1,2,3), x>1 returns true") {
    new TestSets {
      val a = union(s1, s2)
      val b = union(a, s3)

      assert(exists(b, x => x > 1), "exists")
    }
  }


  test("map (1,2,3), x*x returns (1,4,9)") {
    new TestSets {
      val a = union(s1, s2)
      val b = union(a, s3)

      val c = map(b, x => x * x)

      assert(contains(c, 1), "Map 1")
      assert(contains(c, 4), "Map 2")
      assert(contains(c, 9), "Map 3")
    }
  }

  test("map (1,2,3,5,7,1000), x returns -x") {
    new TestSets {
      val a = union(singletonSet(1), singletonSet(2))
      val b = union(a, singletonSet(3))
      val c = union(b, singletonSet(5))
      val d = union(c, singletonSet(7))
      val e = union(d, singletonSet(1000))

      val f = map(e, x => -x)

      assert(contains(f, -1), "Map 1")
      assert(contains(f, -2), "Map 2")
      assert(contains(f, -3), "Map 3")
      assert(contains(f, -5), "Map 5")
      assert(contains(f, -7), "Map 7")
      assert(contains(f, -1000), "Map 1000")
    }
  }
}
