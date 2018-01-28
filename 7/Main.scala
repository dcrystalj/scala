
//seznam k vrne vse pare   for(a<-s; b<-s) yield {(a,b)}
case class AlgebraStruct[A](s: List[A], op: (A, A) => A) {

  //enota te strukture, ce jo ta ima
  //val unit: Option[A] = ???

   // Ko boste imeli definirane spodnje metode, določite še te vrednosti.
  val isGrupoid: Boolean = this.isClosed
  val isSemigroup: Boolean = this.isClosed && this.isAssoc
  val isMonoid: Boolean = this.isSemigroup && this.hasUnit
  val isGroup: Boolean = this.isMonoid && s.foldRight(true)((a, b) => this.hasInverse(a) && b)
  val isAbelianGroup: Boolean = this.isGroup && this.isCommut


  def isClosed: Boolean = {
    val pairs = for(a<-s; b<-s) yield (a, b)
    pairs.foldRight(true)((a, b)=> s.contains(op(a._1, a._2)) && b)
  }

  def isAssoc: Boolean = {
    val triples = for(a<-s; b<-s; c<-s) yield (a, b, c)
    triples.foldRight(true)((a, b)=> (op(op(a._1, a._2), a._3) == op(op(a._3, a._2), a._1) && b))
  }

  def hasUnit: Boolean = {
    s.foldRight(false) ((e, d) =>
      (d || s.foldRight(true)((a, b)=> (op(a, e) == op(e, a) && op(a, e) == a && b) ))
    )
  }

  def hasInverse(e: A): Boolean = {
    s.foldRight(false) ((a, b) =>
      (b || s.foldRight(false) ((c, d) => op(c, a) == op(a, c) && op(c, a) == e || d)))
  }

  def isCommut: Boolean = {
    val pairs = for(a<-s; b<-s) yield (a, b)
    pairs.foldRight(true)((a, b)=> (op(a._1, a._2) == op(a._2, a._1) && b))
  }

  //Ce ta struktura ni grupoid, vrnite nov grupoid, ki ga dobite tako, da
  //razširite množico s, da postane op notranja operacija.
  def makeGrupoid: AlgebraStruct[A] = {
    if (this.isClosed) {
      this
    }
    else {
      val pairs = for(a<-s; b<-s) yield (a, b)
      pairs.foldRight(List())((a, b)=> op(a._1, a._2)::b)
    }
  }

}

// object RList {
//   def apply[A](e: A*): RList[A] = {
//     e.foldRight(LEnd: RList[A])((x, l) => l.add(x))
//   }
// }

// trait RList[+A] {
//   def add[BB >: A](e: BB): RList[BB]
//   def apply(i: Int): A
//   def update[BB >: A](idx: Int, newVal: BB): RList[BB]
//   val length: Int
//   def head: A
//   def tail: RList[A]
//   override def toString: String
// }

// /*
// * e je element, ki ga vozlišče hrani, next je naslednje drevo v seznamu, l (r) sta levo (desno) poddrevo
// * se pa je število elementov v tem drevesu (vključno s korenom).
// */
// case class LNode[+A](e: A, next: RList[A], l: RList[A], r: RList[A], se: Int) extends RList[A]

// case object LEnd extends RList[Nothing]
