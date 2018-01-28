import org.specs2.mutable._

class Week8Tests extends Specification {

  "infList" should {
    "create list from n " in {
        val s = InfLists.from(3)
        s.head mustEqual 3
        s.tail.head mustEqual 4
        s.tail.tail.head mustEqual 5
    }

    "factorial " in {
        val s = InfLists.factorial
        s.head mustEqual 1
        s.tail.head mustEqual 2
        s.tail.tail.head mustEqual 6
        s.tail.tail.tail.head mustEqual 24
        s.tail.tail.tail.tail.head mustEqual 120
    }

    "create stream of e^x coeficients" in {
        val s = InfLists.exCoef
        s.head mustEqual 1.0
        s.tail.head mustEqual 1.0
        s.tail.tail.head mustEqual 1/2.0
        s.tail.tail.tail.head mustEqual 1/6.0
        s.tail.tail.tail.tail.head mustEqual 1/24.0
    }

    "create stream of sinx coeficients" in {
        val s = InfLists.sinCoef
        s.head mustEqual 1.0
        s.tail.head mustEqual -1/2.0
        s.tail.tail.head mustEqual 1/24.0
        s.tail.tail.tail.head mustEqual -1/720.0
    }

    "create stream of cosx coeficients" in {
        val s = InfLists.cosCoef
        s.head mustEqual 1.0
        s.tail.head mustEqual -1/6.0
        s.tail.tail.head mustEqual 1/120.0
        s.tail.tail.tail.head mustEqual -1/5040.0
    }

    "recaman " in {
        val r = InfLists.recaman
        r.head mustEqual 1
        r.tail.head mustEqual 3
        r.tail.tail.head mustEqual 6
        r.tail.tail.tail.head mustEqual 2
        r.tail.tail.tail.tail.head mustEqual 7
    }
  }

  "Collatz" should {
    "trace " in {
        val c = Collatz.trace(7)
        c.head mustEqual 7
        c.tail.head mustEqual 22
        c.tail.tail.head mustEqual 11
        c.tail.tail.tail.head mustEqual 34
    }

    "all traces" in {
        val c = Collatz.allTraces
        c.head.head mustEqual 1
        c.tail.head.head mustEqual 2
        c.tail.head.tail.head mustEqual 1
        c.tail.tail.head.head mustEqual 3
        c.tail.tail.head.tail.head mustEqual 10
        c.tail.tail.head.tail.tail.head mustEqual 5
        c.tail.tail.head.tail.tail.tail.head mustEqual 16
    }

    "findmaxsteps" in {
        val c = Collatz.findMaxSteps(8)
        c mustEqual (7, 17)
        val d = Collatz.findMaxSteps(3)
        d mustEqual (3, 8)
    }
  }
  //test from

  //test infinite series

  //test Recaman


  //test 3n+1

  //test power series
}
