import org.specs2.mutable._

class Week4Tests extends Specification {

  //testirajte različno ustvarjanje BST-ja
  "test bst creation" should {
    "is empty" in {
        val e = EmptyT
        e.isEmpty mustEqual (true)
        val t = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
        t.isEmpty mustEqual (false)
    }
    "& add element in tree" in {
        val e = EmptyT
        val t = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
        val t1 = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
        (e & 5) mustEqual (new Node(5, EmptyT, EmptyT))
        (t & 5) mustEqual (new Node(3, new Node(2,EmptyT,EmptyT), new Node(5, EmptyT, EmptyT)))
        (t1 & 1) mustEqual (new Node(3, new Node(2, new Node(1, EmptyT,EmptyT), EmptyT), EmptyT))

    }

    "size" in {
        val e = EmptyT
        val t = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
        val t1 = new Node(3, new Node(2,EmptyT,EmptyT), new Node(5, EmptyT, EmptyT))

        e.size mustEqual 0
        t.size mustEqual 2
        t1.size mustEqual 3
    }

    "apply" in {
        val e = EmptyT
        val t = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
        val t1 = new Node(3, new Node(2,EmptyT,EmptyT), new Node(5, EmptyT, EmptyT))

        // e.apply(0) mustEqual (new NoSuchElementException)
        t.apply(0) mustEqual 2
        t.apply(1) mustEqual 3
        t1.apply(0) mustEqual 2
        t1.apply(1) mustEqual 3
        t1.apply(2) mustEqual 5
    }

    "toSortedList" in {
        val e = EmptyT
        val t = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
        val t1 = new Node(3, new Node(2,EmptyT,EmptyT), new Node(5, EmptyT, EmptyT))

        e.toSortedList mustEqual (List())
        t.toSortedList mustEqual (List(2,3))
        t1.toSortedList mustEqual (List(2,3,5))
    }


    "toSortedList" in {
        val e = EmptyT
        val t = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
        val t1 = new Node(3, new Node(2,EmptyT,EmptyT), new Node(5, EmptyT, EmptyT))

        e.toList mustEqual (List())
        t.toList mustEqual (List(2,3))
        t1.toList mustEqual (List(2,3,5))
    }

    "equals" in {
        val e = EmptyT
        val e1 = EmptyT
        val t = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
        val t1 = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
        val t2 = new Node(3, EmptyT, EmptyT)

        t.equals(EmptyT) mustEqual (false)
        EmptyT.equals(t) mustEqual (false)
        t.equals(t1) mustEqual (true)
        t1.equals(t2) mustEqual (false)

        e.equals(e1) mustEqual (true)
    }


    "merge" in {
        val e = EmptyT
        val t = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
        val t1 = new Node(3, new Node(2,EmptyT,EmptyT), new Node(5, EmptyT, EmptyT))

        e.merge(e) mustEqual (e)
        e.merge(t) mustEqual (t)
        t.merge(e) mustEqual (t)
        t.merge(t1).toSortedList mustEqual (List(2,2,3,3,5))
    }

   "map" in {
        val e = EmptyT
        val t = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
        val t1 = new Node(3, new Node(2,EmptyT,EmptyT), new Node(5, EmptyT, EmptyT))

        e.map((x=>x+1)) mustEqual (e)
        t.map((x=>x+1)) mustEqual (new Node(4, new Node(3,EmptyT,EmptyT), EmptyT))
        t1.map((x=>x+1)) mustEqual new Node(4, new Node(3,EmptyT,EmptyT), new Node(6, EmptyT, EmptyT))
    }


   "foldInOrder" in {
        val e = EmptyT
        val t = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
        val t1 = new Node(3, new Node(2,EmptyT,EmptyT), new Node(5, EmptyT, EmptyT))

        e.foldInOrder(1)(((x,y)=>x+y)) mustEqual (1)
        t.foldInOrder(1)(((x,y)=>x+y)) mustEqual (6)
        t1.foldInOrder(1)(((x,y)=>x+y)) mustEqual 11
    }

    "filter" in {
        val e = EmptyT
        val t = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
        val t4 = new Node(3, EmptyT, new Node(2,EmptyT,EmptyT))
        val t1 = new Node(3, new Node(2,EmptyT,EmptyT), new Node(5, EmptyT, EmptyT))
        val t3 = new Node(3, EmptyT, EmptyT)

        e.filter((x => x<3)) mustEqual EmptyT
        t.filter((x => x==3)) mustEqual new Node(3,EmptyT,EmptyT)
        t.filter((x => x!=3)) mustEqual new Node(2,EmptyT,EmptyT)
        t4.filter((x => x!=3)) mustEqual new Node(2,EmptyT,EmptyT)
        t1.filter((x => x==5 )) mustEqual new Node(5,EmptyT,EmptyT)
        t3.filter((x => x!=3)) mustEqual EmptyT
        t3.filter((x => x<=3)) mustEqual new Node(3,EmptyT,EmptyT)
        t1.filter((x => x==2 )) mustEqual new Node(2,EmptyT,EmptyT)
    }
}

"create bst from list" should {
    "list" in {
        val d:BST = BST(3,2,5)
        d mustEqual (new Node(3, new Node(2,EmptyT,EmptyT), new Node(5, EmptyT, EmptyT)))
    }
}

"print " should {
    "get coords " in {
        val a = BST(5,2,8,5,3,6,2)
        a.getCoords(0,0).sorted mustEqual List((1,2,2), (3,1,2), (4,3,3), (5,2,5), (7,0,5), (9,2,6), (11,1,8))
    }

    //testirajte lep izris drevesa
    "to string" in {
        val a = BST(5,2,8,5,3,6,2)
        a.toString mustEqual (".......5\n" +
         "...2........8\n" +
         ".2....5....6\n" +
         "....3")

    }

  }


}
