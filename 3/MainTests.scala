import org.specs2.mutable._

class Week3Tests extends Specification {
  val solution = new Main

  //Napišite teste za osnovne funkcije za delo s seznami
  "test high order functions" should {
    "get n-th element" in {
        solution.get(0, List(3,4,5)) mustEqual (3)
        solution.get(1, List(3,4,5)) mustEqual (4)
        solution.get(2, List(3,4,5)) mustEqual (5)
    }

    "find element in list and return its indeks" in {
        solution.find(3, List(1,2,3)) mustEqual (2)
        solution.find(3, List(1,3,2)) mustEqual (1)
        solution.find(3, List(3,2,1)) mustEqual (0)
        solution.find(4, List(3,2,1)) mustEqual (-1)
    }

    "delete element from list" in {
        solution.delete(2, List(1,2,3)) mustEqual (List(1,2))
        solution.delete(1, List(1,2,3)) mustEqual (List(1,3))
        solution.delete(0, List(1,2,3)) mustEqual (List(2,3))
    }

    "concatenate 2 lists" in {
        solution.concat(List(1,2), List(3,4,5)) mustEqual (List(1,2,3,4,5))
    }

    "sum 2 homotopy lists" in {
        solution.zipperSum(List(1,2), List(3,4,5)) mustEqual (List(4,6))
    }

    "zip 2 lists" in {
      solution.zipper(List(1,2), List(3,4,5)) mustEqual (List(1,3,2,4))
    }

    "map function over each element" in {
        solution.map(List(1,3,4), (x => x+2)) mustEqual (List(3,5,6))
    }

    "foldLeft" in {
        solution.foldLeft(1, List(2,4,5), ((x,y) => x+y+1)) mustEqual (15)
    }
  }


  //Napišite teste za MTF kodiranje
  "mtf coding" should {
    "createStartList" in {
        solution.createStartList mustEqual (0.toChar to 255.toChar)
    }

    "find char in list and move it to front" in {
        solution.findAndMTF('a', List('b', 'c', 'a', 'd')) mustEqual ((2, List('a', 'b', 'c', 'd')))
        solution.findAndMTF('d', List('b', 'c', 'a', 'd')) mustEqual ((3, List('d', 'b', 'c', 'a')))
    }

    "encodeMtf" in {
        solution.encodeMTF("bcdb") mustEqual (List(98,99,100,2))
        solution.encodeMTF("abac") mustEqual (List(97,98,1,99))
    }

    "decodeMtf" in {
        solution.decodeMTF(List(98,99,100,2)) mustEqual ("bcdb")
        solution.decodeMTF(List(97,98,1,99)) mustEqual ("abac")
    }
  }

  //Napišite teste za MFT++ kodiranje
  "mtf crown encoding" in {
    "create start list" in {
        solution.createStartList2 mustEqual ((0.toChar to 255.toChar).map(x=>x.toString))
    }

    "find string in string array" in {
        solution.findString("ab", List("ab","bc","cd")) mustEqual (0)
        solution.findString("bc", List("ab","bc","cd")) mustEqual (1)
        solution.findString("cd", List("ab","bc","cd")) mustEqual (2)
        solution.findString("ef", List("ab","bc","cd")) mustEqual (-1)
        solution.findString("ab", solution.createStartList2) mustEqual (-1)
    }

    "findAndMTF2" in {
        solution.findAndMTF2("ab", List("ab","cd")) mustEqual ((0, List("ab", "cd")))
        solution.findAndMTF2("cd", List("ab","cd")) mustEqual ((1, List("cd", "ab")))
        solution.findAndMTF2("ab", List("a","b")) mustEqual ((-1, List()))
        solution.findAndMTF2("ab", List("ab", "b", "a")) mustEqual ((0, List("ab", "b", "a")))
    }

    "encodeMTf2" in {
        solution.encodeMTF2("abab") mustEqual (List(97,98,0))
        solution.encodeMTF2("aba") mustEqual (List(97,98,2))
        solution.encodeMTF2("abac") mustEqual (List(97,98,2,100))
        solution.encodeMTF2("abacab") mustEqual (List(97,98,2,100,3))
        solution.encodeMTF2("abaca") mustEqual (List(97,98,2,100,2))
        //todo liho
    }


    "decodeMTf2" in {
        solution.decodeMTF2(List(97,98,2)) mustEqual ("aba")
        solution.decodeMTF2(List(97,98,0)) mustEqual ("abab")
        solution.decodeMTF2(List(97,98,2,100)) mustEqual ("abac")
        solution.decodeMTF2(List(97,98,2,100,3)) mustEqual ("abacab")
        solution.decodeMTF2(List(97,98,2,100,2)) mustEqual ("abaca")
    }
  }
}
