import org.specs2.mutable._

class Week2Tests extends Specification {
  val solution = new Main

  "The set functions" should {
    "create the correct singleton" in {
      val s1 = solution.singleton(5)
      s1(5) mustEqual(true)
      s1(4) mustEqual(false)
      s1(6) mustEqual(false)
    }

    "make union" in {
      val s1 = solution.singleton(5)
      val s2 = solution.singleton(4)
      val s3 = solution.union(s1, s2)

      s3(5) mustEqual(true)
      s3(4) mustEqual(true)
      s3(2) mustEqual(false)
      s3(6) mustEqual(false)
    }

    "intersect" in {
      val s1 = solution.singleton(5)
      val s2 = solution.singleton(6)
      val s3 = solution.union(s1, s2)
      val s4 = solution.intersect(s3, s2)
      s4(6) mustEqual (true)
      s4(5) mustEqual (false)
      s4(4) mustEqual (false)
    }

    "interval" in {
      val s1 = solution.singleton(5)
      val s2 = solution.singleton(6)
      val s3 = solution.interval(5, 7)
      s3(5) mustEqual (true)
      s3(6) mustEqual (true)
      s3(7) mustEqual (true)
      s3(8) mustEqual (false)
      s3(4) mustEqual (false)
    }
    "dedup" in {
      val l0 = List()
      val l1 = List(1,2,3)
      val l2 = List(2,3,1,2,3)
      val s0 = solution.dedup(l0)
      val s1 = solution.dedup(l1)
      val s2 = solution.dedup(l2)
      s0 mustEqual (l0)
      s1 mustEqual (s1)
      s2 must contain (l1(0))
      s2 must contain (l1(1))
      s2 must contain (l1(2))

    }
  }

  "The rectangle functions" should {
    //TODO
  }

  "The real functions" should {
    "compose 2 functions" in {
      val f1 = (k: Double) => k + 0.01
      val f2 = (k: Double) => k + 0.02
      solution.compose(f1, f2)(0.01) mustEqual (0.04)
    }

    "sum 2 functions" in {
      val f1 = (k: Double) => k + 0.01
      val f2 = (k: Double) => k + 0.02
      solution.sum(f1, f2)(0.01) mustEqual (0.05)
    }

    "mult 2 functions" in {
      val f1 = (k: Double) => k + 0.01
      val f2 = (k: Double) => k + 0.02
      solution.mult(f1, f2)(0.01) mustEqual (0.02 * 0.03)
    }

    "derive fun" in {
      val f1 = (k: Double) => k*k
      val eps = 0.00001
      solution.derive(f1)(3) must beCloseTo (6, eps)
    }


  }

  "The coding functions" should {
    "encode fun" in {
      val f1 = (x: Char) => ((x + 3) % 255).toChar
      solution.encode("abc", f1) mustEqual("def")
    }

    "isBijective" in {
      val f1 = (x: Char) => ((x + 3) % 255).toChar
      val f2 = (x: Char) => ((x + 3) % 5 + 'a').toChar
      solution.isBijective(f1) mustEqual(false)
      solution.isBijective(f2) mustEqual(true)
    }

    "get function inverse " in {
      val f1 = (x: Char) => ((x + 3) % 255).toChar
      val f2 = (x: Char) => ((x + 3) % 5 + 'a').toChar
      solution.inverse(f1)('d') mustEqual('a')
      solution.inverse(f2)('a') mustEqual(2.toChar)
    }

    "decode string" in {
      val f1 = (x: Char) => ((x + 3) % 255).toChar
      val f2 = (x: Char) => ((x + 3) % 5 + 'a').toChar
      val d1 = f1('a').toString + f1('b') + f1('c')
      val d2 = f2(2).toString + f2(3) + f2(4)

      solution.decode(d1, f1) mustEqual ("abc")
      solution.decode(d2, f2) mustEqual ("\2\3\4")


    }
  }
}
