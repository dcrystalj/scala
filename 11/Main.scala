//Naloga 1.
trait Nat{
  def +(other:Nat):Nat = {
    other match {
        case Z => this
        case S(x) => S(this) + x
    }
  }

  def *(other:Nat):Nat = {
    other match {
        case Z => Z
        case S(x) => this + (this*x)
    }
  }

  def -(other:Nat):Nat = {
    other match {
        case Z => this
        case S(x) => x - this
    }
  }

  def <(other:Nat):Boolean = {
    if(this-other != Z || this == other) false
    else true
  }

}
case class S(n:Nat) extends Nat
case object Z extends Nat

object Nat {
    def apply(n:Int):Nat = {
        if (n <= 0) Z
        else {
            S(apply(n-1))
        }
    }
}

// // Naloga 2.
trait Tree
case class Node(left: Tree, right:Tree) extends Tree
case object Empty extends Tree

object Tree{

 def numnodes(t:Tree):Int = {
    t match {
        case Empty => 0
        // case Node(x, Empty) => numnodes(x)+1
        // case Node(Empty, x) => numnodes(x)+1
        case Node(x, y) => numnodes(x) + numnodes(y) + 1
    }

 }

  def genTrees(n:Int) : List[Tree] = {
    n match {
        case 0 => List()
        case 1 => List( Node(Empty, Empty), Empty )
        case _ => {
            val a = genTrees(n-1)
            // val b = genTrees(n-2)
            (a ++ a.map(x => Node(Empty, x)) ++ a.map(x => Node(x, Empty)) ++ a.map(x => Node(x, x)))
        }
    }
  }
  def wrapper(n:Int):List[Tree] = {
    genTrees(n).distinct.filter(x=>numnodes(x)==n)
  }
}

// res2: List[Tree] =
// List(
//     Node(Empty,Node(Empty,Node(Empty,Empty))),
//     Node(Empty,Node(Node(Empty,Empty),Empty)),
//     Node(Node(Empty,Node(Empty,Empty)),Empty),
//     Node(Node(Node(Empty,Empty),Empty),Empty))
// //Naloga 3.

// case class C(real: Double, img:Double)

// object Mandelbrot{

//   def cSeq(c:Double):Stream[Complex] = ???

//   def isDivergent(seq:Stream[C]):Boolean = ???

//   def draw = ???
// }
