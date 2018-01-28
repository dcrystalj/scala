# Naloge v tretjem tednu


Naloge za tretji teden tehničnega predmeta Scala na FRI.


## Naloga 1. (Seznami)
Pri prvi nalogi boste implementirali nekatere funkcije za delo z linearnimi seznami. Večina teh funkcij je seveda že implementiranih v Scalini standardni knjižnici, vendar je implementacija teh funkcij dovolj poučna vaja, da se bolje spoznate z delovanjem najpomembnejših struktur v funkcijskih programskih jezikih.

Iz standardne knjižnice uporabite zgolj funkcije `head`, `tail` in `isEmpty`, ter seveda konstruktorje za izgradnjo seznama `Nil`, `List()` in `::`.

Prva skupina funkcij bo osnovno delo s seznami.
```scala
def get(i:Int, l:List[Int]):Int

def find(e:Int, l:List[Int]):Int

def delete(i:Int, l:List[Int]):List[Int]
```
  1. Prva funkcija naj vrne i-ti element iz seznama. Če je i večji kot dolžina seznama, potem naj vrne zadnji element v seznamu.
  2. Druga funkcija naj poišče indeks elementa e v seznamu. Če elementa ni v seznamu naj vrne -1.
  3. Tretja funkcija pa naj izbriše i-ti element iz seznama (vrne naj nov seznam, v katerem i-tega elementa ni).

Druga skupina funkcij so funkcije za združevanje seznamov.

```scala
def concat(l1:List[Int], l2:List[Int]):List[Int]

def zipperSum(l1:List[Int], l2:List[Int]):List[Int]

def zipper(l1:List[Int], l2:List[Int]):List[Int]
```
  1. Funkcija `concat` na vrne nov seznam, ki je konkatenacija dveh podanih seznamov.
  2. Funkcija `zipperSum` naj združi dva seznama v enega tako, da elementa na istih indeksih sešteje. Če je kateri izmed seznamov daljši, naj ostanek seznama ignorira. Primer: `zipperSum(List(1,2,3),List(1,2,3,4)) = List(2,4,6)`
  3. Zadnja funkcija pa naj združi dva seznama v vzorcu "zadrge". Prvi element novega seznama naj bo prvi element `l1`, drugi element naj bo prvi element `l2`, itd. Če je kateri seznam daljši naj ga enostavno prilepi na konec novega seznama. Primer: `zipperSum(List(1,3,5),List(2,4,6,8,10)) = List(1,2,3,4,5,6,8,10)`.

Zadnja skupina funkcij sprejme funkcijo in z njo transformira podani seznam.
```scala
def map(l: List[Int], f:Int=>Int):List[Int]

def foldLeft(iVal:Int, l:List[Int], f:(Int,Int)=>Int):Int
```
  1. Prva funkcija sprejme seznam in poljubno funkcijo `Int=>Int`. Vrne naj nov seznam z preslikanimi elementi seznama `l`.
  2.Druga funkcija naj združi vse vrednosti v eno s pomočjo `f`. Prvi argument funkcije je začetna vrednost s katero začnemo sprehod po seznamu. Nato v vsakem koraku preslikamo glavo seznama in začetno vrednost v novo vrednost. Preslikana vrednost pa postane nova začetna vrednost za preostanek seznama.


## Naloga 2. (MTF kodiranje)
 Napišite funkcijo, ki bo zakodirala niz znakov s tako imenovanim LU/MTF kodiranjem.
 LU/MTF (List Update/ Move To Front) kodiranje izgleda tako, da vsak znak zakodiramo z njegovo pozicijo v nekem seznamu.
Ta seznam ni statičen, ampak ga vsak dostop do elementa spremeni  (zato je to List Update).
 Seznam pa spremenimo tako, da vsak element, ki smo ga našli premaknemo na začetek (zato je to Move To Front).
 Denimo, da bodo nizi podani kot zaporedje ASCII znakov, zato lahko na začetku zgradimo seznam lEnc, v katerem so vsi ASCII znaki od 0 do 255. Za vsak znak (iz zaporedja, ki ga kodiramo) poiščemo njegov indeks v lEnc in lEnc ustrezno popravimo.

To kodiranje bomo implementirali postopoma. Najprej implementirajte funkcijo, ki bo zgradila seznam dolžine 256, na vsaki poziciji pa bo en ASCII znak.
```scala
def createStartList:List[Char]
```

```scala
def findAndMTF(c:Char, l:List[Char]):(Int, List[Char])
```
Funkcija naj poišče podani znak v seznamu, vrne pa njegov indeks in nov seznam v katerem je najdeni element premaknjen na začetek seznama.


S pomočjo zgoraj definiranih funkcij implementirajte kodiranje in dekodiranje.
Pri kodiranju začnete s seznamom, kjer so vsi znaki na svoji ASCII pozicijah v seznamu.
```scala
def encodeMTF(s:String):List[Int]

def decodeMTF(l:List[Int]):String
```


## Naloga 3. (MTF kodiranje ++) :crown:
Spremenite funkcije iz prejšnje naloge, da v seznamu za kodiranje (lEnc) ne bodo samo znaki,
ampak poljubni pari znakov (torej nizi dveh znakov). Niz, ki se kodira se najprej pogleda dva znaka vnaprej. Če je ta par znakov že v lEnc, potem se ga zakodira z indeksom, sicer se zakodira oba znaka, par pa se doda v seznam na začetek.
```scala
def createStartList2:List[String]
def findAndMTF2(s:String, l:List[String]):(Int, List[String])
def encodeMTF2(s:String):List[Int]
def decodeMTF2(l:List[Int]):String
```
