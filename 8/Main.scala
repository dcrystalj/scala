
//Naloga 1.
object InfLists {

  def from(n: Int): Stream[Int] = {
    (n)#::from(n+1)//.map(x=>x+1)
  }

  val factorial:Stream[Int] = {
    def fact(n: Int): Stream[Int] = {
      (n)#::fact(n+1).map(x=>x*n)
    }
    fact(1)
  }

  val exCoef:Stream[Double] = {
    (1.0)#::factorial.map(x=>1/x.toDouble)
  }

  val sinCoef:Stream[Double] = {
    val even = factorial.drop(1).zipWithIndex.filter(x => x._2 % 2 == 0).map(x => x._1)
    val neg_pos:Stream[Int] = even.zipWithIndex.map(param =>
        if ((param._2 % 2)==0) -param._1
        else param._1
    )
    (1.0)#::neg_pos.map(x=>1/x.toDouble)
  }

  val cosCoef:Stream[Double] = {
    val even = factorial.drop(1).zipWithIndex.filter(x => x._2 % 2 == 1).map(x => x._1)
    val neg_pos:Stream[Int] = even.zipWithIndex.map(param =>
        if ((param._2 % 2)==0) -param._1
        else param._1
    )
    (1.0)#::neg_pos.map(x=>1/x.toDouble)
  }

  val recaman:Stream[Int] = {
    1#:: (recaman zip from(2)).map{ x => if (x._1 - x._2 <= 0) x._1 + x._2 else x._1 - x._2}
  }
}

//Naloga 2.
object Collatz {
  def trace(n: Int): Stream[Int] = {
    def nextTrace(n: Int): Stream[Int] = {
      if (n <= 1) Stream(1)
      else {
        if (n%2==0) (n/2)#:: nextTrace(n/2)
        else (n*3+1)#:: nextTrace((n*3+1))
      }
    }
    n#::nextTrace(n)
  }

  def allTraces: Stream[Stream[Int]] = {
    InfLists.from(1).map(x => trace(x))
  }

  def findMaxSteps(n: Int): (Int, Int) = {
    val r = allTraces.take(n).map(x => x.length-1).zipWithIndex.max
    (r._2+1, r._1)
  }
}

//Naloga 3.
// object PowerSeries {
//   def apply(f: Int => Double): PowerSeries = ???
//   //val ex:PowerSeries = ???
//   //val cos:PowerSeries = ???
//   //val sin:PowerSeries = ???
// }
// case class PowerSeries(coef: Stream[Double]) {
//   def apply(x: Double): Stream[Double] = ???
//   def +(other: PowerSeries): PowerSeries = ???
//   def derive: PowerSeries = ???
// }
