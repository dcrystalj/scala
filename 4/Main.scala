 trait BST {
   def isEmpty:Boolean
   def &(n:Int):BST
   def head:Int
   def left:BST
   def right:BST
   def size:Int
   def apply(idx:Int):Int
   def toSortedList:List[Int]
   def toList:List[Int]
   def merge(other:BST):BST
   def map(f:Int=>Int):BST
   def foldInOrder(startVal:Int)(f:(Int, Int)=>Int):Int
   def filter(f:Int=>Boolean):BST
   def height:Int
   def getCoords(depth:Int, offset:Int):List[(Int,Int,Int)]
   def getCoord(depth:Int, prev:Int, offset:Int):List[(Int,Int,Int)]
 }

object EmptyT extends BST {
  def isEmpty:Boolean = true;
  def & (n:Int):BST = new Node(n, EmptyT, EmptyT)
  def head:Int = throw new NoSuchElementException
  def left:BST = throw new NoSuchElementException
  def right:BST = throw new NoSuchElementException
  def size:Int = 0
  def apply(idx:Int):Int = throw new NoSuchElementException
  def toSortedList:List[Int] = List()
  def toList:List[Int] = List()
  override def equals(other:Any):Boolean = other.asInstanceOf[BST].isEmpty
  def merge(other:BST):BST = other
  def map(f:Int=>Int):BST = EmptyT
  def foldInOrder(startVal:Int)(f:(Int, Int)=>Int):Int = startVal
  def filter(f:Int=>Boolean):BST = EmptyT
  override def toString = ""
  def height:Int = 0
  def getCoords(depth:Int, offset:Int):List[(Int,Int,Int)] = List()
  def getCoord(depth:Int, prev:Int, offset:Int):List[(Int,Int,Int)] = List()
}


class Node(element:Int, l:BST, r:BST) extends BST {
  def isEmpty:Boolean = false
  def &(n:Int):BST = {
    if (l.isEmpty && r.isEmpty) {
      if (n > element) {
        new Node(element, l, new Node(n, EmptyT, EmptyT))
      }
      else {
        new Node(element, new Node(n, EmptyT, EmptyT), r)
      }
    }
    else if (n <= element) {
      new Node(element, l.&(n), r)
    }
    else {
      new Node(element, l, r.&(n))
    }
  }

  def head:Int = element
  def left:BST = l
  def right:BST = r
  def size:Int = 1 + l.size + r.size
  def apply (idx:Int) = toSortedList(idx)
  def toSortedList:List[Int] = {
      l.toSortedList ++ List(element) ++ r.toSortedList
  }
  def toList = this.toSortedList
  override def equals(other:Any):Boolean = {
    if (other.asInstanceOf[BST].isEmpty) {
      false
    }
    else if (element == other.asInstanceOf[BST].head) {
      l.equals(other.asInstanceOf[BST].left) && r.equals(other.asInstanceOf[BST].right)
    }
    else {
      false
    }
  }

  def merge(other:BST):BST = {
    if (other.isEmpty) {
      this
    }
    else {
      ((this & other.head).merge(other.left).merge(other.right))
    }
  }

  def map (f:Int=>Int):BST = {
    new Node(f(element), l.map(f), r.map(f))
  }

  def foldInOrder(startVal:Int)(f:(Int, Int)=>Int):Int = {
    val l = toSortedList
    l.foldLeft(startVal)(f)
  }

  def filter(f:Int=>Boolean):BST = {
    if (!f(element)) {
      if (l.isEmpty && r.isEmpty) {
        EmptyT
      }
      else if (l.isEmpty) {
        r.filter(f)
      }
      else if (r.isEmpty) {
        l.filter(f)
      }
      else {
        l.merge(r).filter(f)
      }
    }
    else {
     new Node(element, l.filter(f), r.filter(f))
    }
  }

  override def toString = {
    val coords = getCoords(0,0)

    def stringLine(a:List[(Int,Int,Int)]):String = {

      val s = a.sortWith((a,b) => a._1 < b._1)
      def buildStr(b:List[(Int,Int,Int)], offset:Int):String = {
        if (b.isEmpty) {
          ""
        }
        else {
          val off:Int = b(0)._1 - offset
          "." * (off) + b(0)._3.toString + buildStr(b.tail, b(0)._1)
        }
      }
      buildStr(s,0)
    }

    def loop(depth:Int): String = {
      val fil = coords.filter((a)=>a._2==depth)
      if (fil.isEmpty)
        ""
      else
        stringLine(fil) + "\n" + loop(depth + 1)
    }
    loop(0).dropRight(1)
  }

  def height = {
    1 + math.max(l.height, r.height)
  }


  def getCoord(depth:Int, offset:Int, division:Int):List[(Int,Int,Int)] = {
      List((offset-1, depth, element)) ++ l.getCoord(depth + 1, offset-division/2, division/2) ++ r.getCoord(depth + 1, offset + division/2, division/2)
  }

  def getCoords(depth:Int, offset:Int):List[(Int,Int,Int)] = {
    val h = height-1
    val x = math.pow(2, h).toInt
    getCoord(0, x, x)
  }
}

//spremljevalni objekt razreda BST
object BST {
  def apply(a:Int*):BST = {
    a.foldLeft(EmptyT:BST)((a, b)=>a & b)
  }
}