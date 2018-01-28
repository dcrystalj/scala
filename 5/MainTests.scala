import org.specs2.mutable._
import scala.collection.immutable.HashMap

class Week5Tests extends Specification {

  "naloga 1" should {
    "value" in {
        Var("a").value(HashMap("a"->3.0)) mustEqual Some(3.0)
        Var("a").value(HashMap("b"->3.0)) mustEqual None
        Num(3.0).value() mustEqual Some(3.0)
        Sum(Num(3.0),Num(2.0)).value() mustEqual Some(5.0)
        Sum(Sum(Num(3.0),Num(2.0)),Num(2.0)).value() mustEqual Some(7.0)
        Sum(Sum(Num(3.0),Num(2.0)),Var("a")).value(HashMap("b"->2.0)) mustEqual None
        Sum(Sum(Num(3.0),Var("a")),Num(2.0)).value(HashMap("b"->2.0)) mustEqual None

        Prod(Num(3.0),Num(2.0)).value() mustEqual Some(6.0)
        Quot(Num(3.0),Num(2.0)).value() mustEqual Some(1.5)
        Pow(Num(3.0),2).value() mustEqual Some(9.0)
    }

    //testi za ustvarjanje izrazov
    "apply " in {
        Expr("a 5 + 4 / 2 c ^5 + *") mustEqual Prod(Quot(Sum(Var("a"), Num(5.0)), Num(4.0)), Sum(Num(2.0), Pow(Var("c"), 5)))
        Expr("1 ^2") mustEqual Pow(Num(1), 2)
    }

    "derive " in {
       Var("a").derive(Var("a")) mustEqual Num(1.0)
       Num(3.0).derive(Var("a")) mustEqual Num(0)
       Sum(Var("a"), Num(3.0)).derive(Var("a")) mustEqual Sum(Num(1.0), Num(0))
       // Prod(Var("a"), Num(3.0)).derive(Var("a")) mustEqual Num(3.0)
       // Prod(Var("a"), Var("a")).derive(Var("a")) mustEqual Prod(Num(2.0), Var("a"))
       // Prod(Num(2.0), Num(2.0)).derive(Var("a")) mustEqual Num(3.0)
       Pow(Num(3.0),3).derive(Var("a")) mustEqual Num(0)
       Pow(Var("a"),3).derive(Var("a")) mustEqual Prod(Num(3.0), Pow(Var("a"), 2))
    }

    "simplify " in {
        Expr("0 3 +").simplify mustEqual Num(3)
        Expr("0 3 *").simplify mustEqual Num(0)
        Expr("1 3 *").simplify mustEqual Num(3)
        Expr("1 ^3").simplify mustEqual Num(1)
        Expr("a ^1").simplify mustEqual Var("a")
        Expr("a ^0").simplify mustEqual Num(1)

    }
  }


}
