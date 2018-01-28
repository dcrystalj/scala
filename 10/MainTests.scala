import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.BooleanOperators
import org.scalacheck._


// object ExampleSpecification extends Properties("Example") {

//   property("startsWith") = forAll { (a: String, b: String) =>
//     (a+b).startsWith(a)
//   }

//   property("concatenate") = forAll { (a: String, b: String) =>
//     (a+b).length >= a.length && (a+b).length >= b.length
//   }

// }

object Naloga1Specification extends Properties("Naloga1") {
    // val h = Gen.containerOf[List, String](g)
    val g = Gen.containerOf[List, Char](Gen.choose('a','z'))
    val gc = Gen.choose(-3000, 3000)
    property("encode") = forAll (g, gc)
    { (chars:List[Char], move:Int) =>
        val s = chars.foldLeft ("") ((s,c) => s+c)
        s == Main.encode(Main.encode(s, move), -move)
    }

    property("decode") = forAll (g, gc)
    { (chars:List[Char], move:Int) =>
        val s = chars.foldLeft ("") ((s,c) => s+c)
        s == Main.decode(Main.decode(s, move), -move)
    }

    // val intC = Gen.containerOf[List, Char](Gen.choose('a','z'))
    val i = Gen.containerOf[List, Int](gc)
    property("duplicate") = forAll (i) {
        l:List[Int] => Main.duplicate(l).length == 2*l.length
    }

    property("is palindrom") = forAll (g) {
        l:List[Char] =>
        val s = l.foldLeft ("") ((s,c) => s+c)
        Main.isPalindrome(s) == Main.isPalindrome(s.reverse)
    }

    property("sorted ") = forAll (i) {
        l:List[Int] => Main.sort(l) == Main.sort(l.reverse)
    }

}


object Naloga2Specification extends Properties("Naloga2") {
    val gc = Gen.choose(-3000, 3000)
    property("gcd") = forAll (gc, gc) {
        (i:Int, j:Int) =>
            val gcd = Main.gcd(i, j)
            i % gcd == 0 && j % gcd == 0
    }

    property("slice concat") = forAll (genBoundedList(10, gc), gc) {
        (l:List[Int], k:Int) =>
            val s = Main.slice(l, k)
            s._1 ++ s._2 == l
    }


    property("slice length") = forAll (genBoundedList(10, gc), Gen.choose(0,10)) {
        (l:List[Int], k:Int) =>
            val i = k.min(l.length)
            val s = Main.slice(l, k)
            s._1.length == i && s._2.length == (l.length - i)
    }

    def genBoundedList(maxSize: Int, g: Gen[Int]): Gen[List[Int]] =
        Gen.choose(0, maxSize) flatMap { sz => Gen.listOfN(sz, g) }

    property("powerset") = forAll (genBoundedList(10, gc)) {
        (l:List[Int]) => Main.powerset(l).contains(l)
    }


}

object Naloga3Specification extends Properties("Naloga2") {


}
