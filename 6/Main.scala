

// Naloga 1. Implementacija neučinkovite in učinkovite vrste

trait Q1{
  def enQ(e:List[Int]):Q1 = {
    QNode1(e, this)
  }
  def deQ:(Q1, List[Int]) = {
    this match {
      case QEmpty1 => throw new NoSuchElementException
      case QNode1(e, QEmpty1) => (QEmpty1, e)
      case QNode1(l, QNode1(e, QEmpty1)) => (QNode1(l, QEmpty1), e)
      case QNode1(e, next) => next.deQ
    }
  }
}

case object QEmpty1 extends Q1
case class QNode1(el:List[Int], next:Q1) extends Q1

object Q1{
  def countAcc(ops: List[Option[List[Int]]]):Int = {
    def countAccAcc(l: List[Option[List[Int]]], acc:Int):Int = {
      if (l.isEmpty) {
        0
      }
      else {
        l.head match {
          case Some(_) => 1 + countAccAcc(l.tail, acc+1)
          case None => acc + countAccAcc(l.tail, acc-1)
        }
      }
    }
    countAccAcc(ops, 0)
  }
}

trait Q2{
  // v back se dodaja nove elemente
  def enQ(e:List[Int]):Q2 = {
    this match {
      case QEmpty2 => QNode2(List(), List(e))
      case QNode2(f, b) => QNode2(f, e::b)
    }
  }
  def deQ:(Q2, List[Int]) = {
    this match {
      case QEmpty2 => throw new NoSuchElementException
      case QNode2(List(), List(b)) => (QEmpty2, b)
      case QNode2(List(), b) => {
        val rev = b.reverse
        (QNode2(rev.tail, List()), rev.head)
      }
      case QNode2(f, b) => (QNode2(f.tail, b), f.head)
    }
  }
}

case object QEmpty2 extends Q2
case class QNode2(front:List[List[Int]], back:List[List[Int]]) extends Q2

object Q2{
  def countAcc(ops: List[Option[List[Int]]]):Int = {
    def countAccAcc(l: List[Option[List[Int]]], acc:Int):Int = {
      if (l.isEmpty) {
        0
      }
      else {
        l.head match {
          case Some(_) => 1 + countAccAcc(l.tail, acc+1)
          case None    => 1 + acc + countAccAcc(l.tail, 0)
        }
      }
    }
    countAccAcc(ops, 0)
  }
}


// Naloga 2. preiskovanje labirinta

class Maze(nRows:Int, nCols:Int, cells:List[Boolean]){
  def to1D(y:Int, x:Int):Int = {
    if (y == 0 || y == nRows || x == -1 || x == nCols+1)
      0 //we are sure this is wall
    else
      y*(nCols+1)+x
  }
  def to2D(a: Int):(Int, Int) = {
    val y = a/(nCols+1)
    val x = a - y*(nCols+1)
    (y, x)
  }

  def solution:List[Int] = {
    val start = QEmpty2.enQ(List(to1D(1, 0)))
    // val start = QNode2(List(), List(List(to1D(1, 0))))
    val end = to1D(nRows-1, nCols)
    def solve(q:Q2, visited:List[(Int, Int)]): List[Int] = {
      q match {
        case QEmpty2 => List()
        case _ => {
          val h = q.deQ
          if (h._2.head == end){
            h._2
          }
          else {
            val q2 = h._1
            val (y, x) = to2D(h._2.head)
            val q3 = List((y+1, x),(y-1, x), (y, x+1), (y, x-1)).foldRight(q2)(
              (a:(Int,Int), b:(Q2)) =>
                if (!visited.contains(a) && cells(to1D(a._1, a._2)))
                  b.enQ(to1D(a._1, a._2)::h._2)
                else {
                  b
                }
            )
            solve(q3, (y,x)::visited)
          }
        }
      }
    }
    solve(start, List())
  }

  def solutionView:String = {
    val s = this.solution
    def loopPrint(pos:Int, sol:String, mod:Int, seen:Boolean):String = {
      if (pos == (1+nCols) * (nRows+1))
        sol
      else {
          if (cells(pos)) {
            if (s.contains(pos))
              if (pos > 0 && seen && (pos)%(nCols) == mod)
                loopPrint(pos+1, sol + "o\n", mod+1, false)
              else
                loopPrint(pos+1, sol + "o", mod, true)
            else
              if (pos > 0 && seen && (pos)%(nCols) == mod)
                loopPrint(pos+1, sol +  " \n", mod+1, false)
              else
                loopPrint(pos+1, sol +  " ", mod, true)
          }
          else {
            if (pos > 0 && seen && (pos)%(nCols) == mod)
              loopPrint(pos+1, sol +  "*\n", mod+1, false)
            else
              loopPrint(pos+1, sol +  "*", mod, true)
          }
      }
    }
    val r = loopPrint(0, "", 0, false)
    r.take(r.length-1)
  }
}

object Maze{
  def apply(m: String):Maze = {
    def widthM(s: String):Int = {
      if (s.head == '\n'){
        0
      }
      else{
        1+widthM(s.tail)
      }
    }
    val w:Int = widthM(m)-1
    val l:List[Boolean] = List()
    val boolList = m.foldRight(l)((a, b) => if(a == '*') false::b else if (a == ' ') true::b else b)
    print("height " + (boolList.length/w) + " width "+ w + " len " + boolList.length)
    new Maze((boolList.length/w)-1, w, boolList)
  }
}


// // Naloga 3. Implementacija seznama z naključnim dostopom.


// trait RList {
//   def add(e: Int): RList
//   def apply(i: Int): Int
//   def update(idx: Int, newVal: Int):RList
//   val length: Int
//   def head: Int
//   def tail: RList
//   override def toString: String
// }

// /*
// * e je element, ki ga vozlišče hrani, next je naslednje drevo v seznamu, l (r) sta levo (desno) poddrevo
// * se pa je število elementov v tem drevesu (vključno s korenom).
// */
// case class LNode(e: Int, next: RList, l: RList, r: RList, se: Int) extends RList
// case object LEnd extends RList

// object RList {
//   def apply(e: Int*): RList = ???
// }
