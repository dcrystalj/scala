# Naloge v šestem tednu

V šestem tednu bomo spoznali podatkovne strukture v funkcijskih programskih jezikih.
Osredotočili se bomo predvsem na nekaj prijemov, ki bistveno pohitrijo dve podatkovni strukturi, obenem pa ohranijo učinkovito (tako prostorsko kot časovno) persistentnost teh podatkovnih struktur.

## Naloga 1.

Implementirajte podatkovno strukturo vrsta na dva načina. Najprej implementirajte naiven način dela z vrsto (kot linearni seznam), nato pa še izboljšano obliko z dvema seznamoma - prvi naj predstavlja prednji del vrste, drugi pa zadnji del vrste.

```scala
trait Q1
case class QC1(el: List[Int], next: Q1) extends Q1
case object QEmpty1 extends Q1
```

```scala
trait Q2
case class QC2(front: List[List[Int]], end: List[List[Int]]) extends Q2
case object QEmpty2 extends Q2
```

V obeh traitih implementirajte metode:
```scala
def enQ(el: List[Int]): Q1 [Q2]
def deQ:(List[Int], Q1 [Q2])
```
Prva metoda naj vrne novo vrsto, kjer je en element dodan na konec te vrste. Druga metoda pa naj vrne element iz začetka vrste ter novo vrsto kjer tega elementa ni več na začetku. Pri drugi vrsti seveda uporabite trik, ki smo si ga ogledali na predavanjih.

Nato napišite funcijo (eno v spremljevalnem objektu Q1, drugo v spremljevalnem objektu Q2), ki bo za podano zaporedje operacij vrnila število dostopov do elementov.
```scala
def countAcc(seq: List[Option[List[Int]]]): Int
```
Primer zaporedja zahtevkov za vrsto:
```scala
val seq = List(Some(List(1)), Some(List(2)), None, None )
```
To zaporedje predstavlja najprej dve vstavljanji v vrsto, nato pa dve odvzemanji iz vrste.

## Naloga 2.

Zgornjo vrsto (Q2) uporabite za implementacijo iskanja poti v labirintih.
Labirint naj bo podan kot niz, kjer bo znak * predstavljal zid, presledek pa prosti prehod. Vrstice labirinta so ločene z znakom za novo vrstico. Vrstice naj bodo oštevilčene od 0 do n, stolpci pa od 0 do m. Vhod v labirint naj bo vedno na poziciji (1,0), t.j. vrstica 1 in stolpec 0, izhod iz labirinta pa na poziciji (n-1, m).

```
****************
            *  *
****  ****  *  *
*     *        *
*  *************
*     *        *
****  *  ****  *
*     *  *     *
*  *******  ****
*
****************
```

Vaša naloga bo, da poiščete pot v tem labirintu od vhoda do izhoda, z iskanjem v širino.

Implementirajte razred
```scala
class Maze(nRows:Int, nCols:Int, cells:List[Boolean])
```
V seznamu `cells` je shranjeno stanje labirinta - če je vrednost na neki poziciji `true`, potem naj bo to prehodna celica, če pa je `false` pa naj bo to neprehodna celica. Dvodimenzionalno koordinato celice (r,c) enostavno preslikajte v enodimenzionalno, da dobite njeno pozicijo v seznamu `cells`.

Zapišite tudi spremljevalni objekt `Maze` v katerem napišite funkcijo
```scala
def apply(m:String):Maze
```
ki iz podanega niza ustvari ustrezen Maze objekt.


Napišite funkcijo, ki izračuna rešitev tega labirinta in jo vrne kot seznam pozicij v seznamu `cells`.
```scala
def solution:List[Int]
```

Ta funkcija naj preišče labirint tako, da začne na celici (1,0) in vsakič doda v vrsto sosednje celice, ki so prehodne in se na trenutni poti še niso pojavile. Za vsako celico morate torej hraniti tudi pot, po kateri ste do te celice prišli.

Zato ste v prejšnji nalogi implementirali vrsto, ki hrani sezname celih števil. V vrsto boste torej postavljali poti v labirintu, po katerih ste prišli do trenutno aktualnih celic. Vrsta pa bo poskrbela, da boste labirint preiskovali v širino.

Nazadnje implementirajte še funkcijo

```scala
def solutionView:String
```
ki vrne niz s predstavljeno rešitvijo. Primer take rešitve si lahko ogledate spodaj.

```
****************
ooooo       *  *
****o ****  *  *
* ooo *        *
* o*************
* ooo *        *
****o *  ****  *
* ooo *  *     *
* o*******  ****
* oooooooooooooo
****************
```

## Naloga 3.:crown:
V predhodni nalogi ste za predstavitev labirinta uporabili linearni seznam, za katerega pa veste, da dostop da poljubnega elementa traja *O(n)* časa.

 Obstaja pa elegantna podatkovna struktura, ki se imenuje seznam z naključnim dostopom. Ta struktura še vedno omogoča učinkovito nespremenljivost, hiter dostop do glave in repa seznama, poleg tega pa omogoča tudi hiter dostop do poljubnega elementa v seznamu (*O(log(n))*).

### Globalen pogled na to strukturo
Struktura je implementirana kot seznam polnih dvojiških dreves, ki so urejena po velikosti, od najmanjšega do največjega, pri čemer sta lahko samo prvo in drugo drevo iste velikost, ostali so striktno različni.

Primer take strukture je podan na spodnji sliki.

![RAList](img/RAList.png)


Elementi so v seznamu v zaporedju kot so urejene oznake po abecedi.

### Dodajanje na začetek seznam
Element dodamo na začetek seznama tako, da preprosto ustvarimo drevo z enim elementom, ki ga pripnemo na začetek seznama dreves. Potem preverimo, če imata naslednji dve drevesi (imenujmo ju T1 in T2) enako velikost. Če je temu tako, potem ju združimo v novo drevo, kjer za koren postavimo ravnokar vstavljeni element, levo poddrevo je drevo T1, desno poddrevo pa je drevo T2.

Spodnja slika prikazuje primer situacije po vstavljanju elementa v glavo, ko detektiramo, da sta drugo in tretje poddrevo enake velikosti, česar pa ne dovolimo.
![RAList](img/add_1.png)

Zato združimo vstavljeni element in drugo ter tretje drevo, da nastane sledeče zaporedje dreves:
![RAList](img/add_2.png)


## Iskanje elementa s podanim indeksom

Ideja za dostop do elementa s podanim indeksom je seveda rekurzivna:
  1. Če iščem indeks 0, potem vrnem kar trenutni element
  2. Če je indeks (i) večji ali enak kot je število vozlišč v trenutnem drevesu (n), potem vrnem element z indeksom i-n iz naslednjega drevesa.
  3. Če je indeks (i) manjši kot je število elementov (n_l) v levem poddrevesu, potem vrnem element z indeksom i-1 iz levega poddrevesa, sicer pa element z indeksom in_l-1 z desnega poddrevesa.

Spodnja slika prikazuje pot do elementa z indeksom 9.

![RAList](img/find.png)

### Spreminjanje elementa s podanim indeksom
V tej strukturi lahko učinkovito implementiramo tudi spreminjanje elementa. Samo spreminjanje je zelo podobno iskanju elementa s podanim indeksom, zato vam to prepuščamo za nalogo.


### Implementacija

Implementirajte metode v traitu RList:

```scala
trait RList {
  def add(e: Int): RList
  def apply(i: Int): Int
  def update(idx: Int, newVal: Int):RList
  val length: Int
  def head: Int
  def tail: RList
  override def toString: String
}
```
Dejanski implementaciji tega traita pa naj bosta:
```scala
case class LNode(e: Int, next: RList, l: RList, r: RList, se: Int) extends RList {
case object LEnd extends RList
```
