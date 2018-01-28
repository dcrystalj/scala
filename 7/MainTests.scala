import org.specs2.mutable._

class Week7Tests extends Specification {

  "exercise 1 " should {
    "be closed " in {
        val s = List(0,1,2,3)
        AlgebraStruct(s:List[Int], (a:Int, b:Int)=> (a+b) % 4).isClosed mustEqual true
        AlgebraStruct(s:List[Int], (a:Int, b:Int)=> (a+b) % 5).isClosed mustEqual false
    }

    "is assoc " in {
        val s = List(0,1,2,3)
        AlgebraStruct(s:List[Int], (a:Int, b:Int)=> (a+b) % 4).isAssoc mustEqual true
        AlgebraStruct(s:List[Int], (a:Int, b:Int)=> a).isAssoc mustEqual false
    }

    "has unit " in {
        val s = List(0,1,2,3)
        AlgebraStruct(s:List[Int], (a:Int, b:Int) => (a+b)).hasUnit mustEqual true
        AlgebraStruct(s:List[Int], (a:Int, b:Int) => a).hasUnit mustEqual false
    }

    "has inverse  " in {
        val s = List(0,1,2,3)
        AlgebraStruct(s:List[Int], (a:Int, b:Int) => (a*b)).hasInverse(6) mustEqual true
        AlgebraStruct(s:List[Int], (a:Int, b:Int) => a).hasInverse(5) mustEqual false
    }

    " is comutative " in {
        val s = List(0,1,2,3)
        AlgebraStruct(s:List[Int], (a:Int, b:Int)=> (a+b) % 4).isCommut mustEqual true
        AlgebraStruct(s:List[Int], (a:Int, b:Int)=> a).isCommut mustEqual false
    }
  }

  //Closure tests

  //RList tests

}
