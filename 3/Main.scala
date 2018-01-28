class Main{

  /*
   * Naloga 1. Napišite nekaj enostavnih funkcij za delo s seznami.
   * Uporabljajte zgolj head, tail in Nil.
   */


   // Vrnite element na poziciji i v seznamu: get(2, List(1,2,3,4))=3
   def get(i:Int, l:List[Int]):Int = {
    l(i)
   }

   //Poišči dani element v seznamu in vrni njegov index, če ga ni vrni -1
   def find(e:Int, l:List[Int]):Int = {
    val len = l.length
    def loop(i: Int):Int = {
      if (i >= len) {
        -1
      }
      else if (get(i, l) == e) {
        i
      }
      else {
        loop(i + 1)
      }
    }
    loop(0)
   }

   // Izbriši element na poziciji i, tj. vrni nov seznam brez tega elementa
   def delete(i:Int, l:List[Int]):List[Int] = {
    val len = l.length
    def loop(j: Int):List[Int] = {
      if (j >= len) {
        List()
      }
      else if (j == i) {
        loop(j + 1)
      }
      else {
        get(j, l)::loop(j + 1)
      }
    }
    loop(0)
   }

   // zlepite dva seznama v enega: concat(List(1,2), List(3,4,5)) = List(1,2,3,4,5)
   def concat(l1:List[Int], l2:List[Int]):List[Int] = {
    if (l1.isEmpty && l2.isEmpty) {
      List()
    }
    else if(l1.isEmpty) {
      l2.head :: concat(l1, l2.tail)
    }
    else {
      l1.head :: concat(l1.tail, l2)
    }
   }

   // naj združi dva seznama v enega tako, da elementa na istih indeksih sešteje.
   //Če je kateri izmed seznamov daljši, naj ostanek seznama ignorira. Primer: zipperSum(List(1,2,3),List(1,2,3,4)) = List(2,4,6)
   def zipperSum(l1:List[Int], l2:List[Int]):List[Int] = {
     if (l1.isEmpty || l2.isEmpty) {
      List()
     }
     else {
      (l1.head + l2.head) :: zipperSum(l1.tail, l2.tail)
     }
   }
   // Združi dva seznama v vzorcu zadrge: zipper(List(1,3,5,7), List(2,4,6)) = List(1,2,3,4,5,6)
   // Če sta seznama različne dolžine naj se daljši seznam odreže.
   def zipper(l1:List[Int], l2:List[Int]):List[Int] = {
    if (l1.isEmpty || l2.isEmpty) {
      List()
    }
    else {
      l1.head :: l2.head :: zipper(l1.tail, l2.tail)
    }
   }

   //napišite funkcijo, ki elemente podanega senzama preslika s podano funkcijo f
   // npr. map(List(1,2,3), x=>x+1 ) = List(2,3,4)
   def map(l: List[Int], f:Int=>Int):List[Int] = {
    if (l.isEmpty) {
      List()
    }
    else {
      f(l.head) :: map(l.tail, f)
    }
   }
   //napišite funkcijo, ki iz seznama celih števil zgradi eno vrednost.
   // podana je začetna vrednost in funkcija, ki dve vrednosti preslika v eno.
   //
   def foldLeft(iVal:Int, l:List[Int], f:(Int,Int)=>Int):Int = {
    if (l.isEmpty) {
      iVal
    }
    else {
     foldLeft(f(iVal, l.head), l.tail, f)
    }
   }


  /*
   * Naloga 2. Napišite funkcijo, ki bo zakodirala niz znakov s tako imenovanim LU/MTF kodiranjem.
   * LU/MTF (List Update/ Move To Front) kodiranje izgleda tako, da vsak znak zakodiramo z njegovo pozicijo v nekem seznamu.
   * Ta seznam ni statičen, ampak ga vsak dostop do elementa spremeni - zato je to List Update.
   * Seznam pa spremenimo tako, da vsak element, ki smo ga našli premaknemo na začetek (zato je to Move To Front).
   * Denimo, da bodo nizi podani kot zaporedje ASCII znakov, zato lahko na začetku zgradimo seznam lEnc, v katerem so vsi ASCII
   * znaki od 0 do 255. Za vsak znak (iz zaporedja, ki ga kodiramo) poiščemo njegov indeks v lEnc in lEnc ustrezno popravimo.
   */
  //Ustvari začetni seznam ASCII znakov, ki bo služil za kodiranje.
  def createStartList:List[Char] = {
    def loop(i:Int):List[Char] = {
      if (i > 255) {
        List()
      }
      else {
        i.toChar :: loop(i + 1)
      }
    }
    loop(0)
  }

  def findChar(e:Char, l:List[Char]):Int = {
    val len = l.length
    def loop(i: Int):Int = {
      if (i >= len) {
        -1
      }
      else if (l.apply(i) == e) {
        i
      }
      else {
        loop(i + 1)
      }
    }
    loop(0)
   }

  //V seznamu l najdi znak c, vrni indeks tega elementa in nov seznam v katerem je c prestavljen na prvo mesto.
  def findAndMTF(c:Char, l:List[Char]):(Int, List[Char]) = {
    val index = findChar(c, l)
    (index, l(index) :: l.take(index) ++ l.drop(index + 1))
  }

  //Podani niz zakodiraj po zgoraj opisanem postopku
  def encodeMTF(s:String):List[Int] = {
    def encode(str:String, l:List[Char]):List[Int] = {
      if (str.isEmpty) {
        List()
      }
      else {
        val finder = findAndMTF(str.head, l)
        finder._1 :: encode(str.tail, finder._2)
      }
    }
    encode(s, createStartList)
  }

  //Podani seznam celih števil odkodiraj v niz.
  def decodeMTF(l:List[Int]):String = {
    def decode(in:List[Int], l:List[Char]):String = {
      if (in.isEmpty) {
        ""
      }
      else {
        val c = l.apply(in.head).toChar
        val finder = findAndMTF(c, l)
        c.toString ++ decode(in.tail, finder._2)
      }
    }
    decode(l, createStartList)
  }


  /*
   * Naloga 3. Spremenite funkcije iz prejšnje naloge, da v seznamu za kodiranje (lEnc) ne bodo samo znaki,
   * ampak poljubni pari znakov (torej nizi dveh znakov). Niz, ki se kodira se najprej pogleda dva znaka vnaprej.
   * Če je ta par znakov že v lEnc, potem se ga zakodira z indeksom, sicer se zakodira oba znaka,
   * par se pa doda v seznam na začetek.
   */
   //Ustvari začetni seznam ASCII znakov, ki bo služil za kodiranje.
   def createStartList2:List[String] = {
    def loop(i:Int):List[String] = {
      if (i > 255) {
        List()
      }
      else {
        i.toChar.toString :: loop(i + 1)
      }
    }
    loop(0)
   }

   def findString(e:String, l:List[String]):Int = {
    val len = l.length
    def loop(i: Int):Int = {
      if (i >= len) {
        -1
      }
      else if (l.apply(i) == e) {
        i
      }
      else {
        loop(i + 1)
      }
    }
    loop(0)
   }

   //V seznamu l najdi niz s, vrni indeks tega elementa in nov seznam v katerem je s prestavljen na prvo mesto.
   //Če niza ni v seznamu naj bo indeks -1.
   def findAndMTF2(s:String, l:List[String]):(Int, List[String]) = {
      val index = findString(s, l)
      if (index == -1) {
        (-1, List())
      }
      else {
        (index, l(index) :: l.take(index) ++ l.drop(index + 1))
      }
   }

   //Podani niz zakodiraj po zgoraj opisanem postopku
   def encodeMTF2(s:String):List[Int] = {
    def encode(str:String, l:List[String]):List[Int] = {
      if (str.isEmpty) {
        List()
      }
      else {
        val firstTwo = str.take(2)
        val finder = findAndMTF2(firstTwo, l)

        if (finder._1 == -1) {
          val i = findString(firstTwo.take(1), l)
          val tmp1 = l(i) :: l.take(i) ++ l.drop(i + 1)
          val j = findString(firstTwo.drop(1), tmp1)
          val tmp2 = tmp1(j) :: tmp1.take(j) ++ tmp1.drop(j + 1)
          i :: j :: encode(str.drop(2), firstTwo::tmp2)
        }
        else {
          finder._1 :: encode(str.drop(2), finder._2)
        }
      }
    }
    encode(s, createStartList2)
   }

   //Podani seznam celih števil odkodiraj v niz.
   def decodeMTF2(l:List[Int]):String = {
     def decode(in:List[Int], newl:List[String]):String = {
      if (in.isEmpty) {
        ""
      }
      else if (in.drop(1).isEmpty) {
        newl(in.head)
      }
      else {

        val i:Int = in.head
        if (newl(i).length == 1) {
          //try second one
          val j:Int = in.drop(1).head
          val finder = findAndMTF2(newl(i) + newl(j), newl)

          if (finder._1 == -1) {
            val tmp1:List[String] = newl(i) :: newl.take(i) ++ newl.drop(i + 1)
            val j:Int = in.drop(1).head
            val tmp2:List[String] = tmp1(j) :: tmp1.take(j) ++ tmp1.drop(j + 1)
            val newStr:String = (newl(i) + tmp1(j))
            newStr + decode(in.drop(2), newStr::tmp2)
          }
          else {
            newl(i) + decode(in.tail, finder._2)
          }


        }else {
            val finder = findAndMTF2(newl(i), newl)

          if (finder._1 == -1) {
            val tmp1:List[String] = newl(i) :: newl.take(i) ++ newl.drop(i + 1)
            val j:Int = in.drop(1).head
            val tmp2:List[String] = tmp1(j) :: tmp1.take(j) ++ tmp1.drop(j + 1)
            val newStr:String = (newl.apply(i) + tmp1.apply(j))
            newStr + decode(in.drop(2), newStr::tmp2)
          }
          else {
            newl(i) + decode(in.tail, finder._2)
          }
        }
      }
    }
    decode(l, createStartList2)
   }

}
