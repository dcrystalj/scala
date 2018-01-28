# Naloge v drugem tednu


Naloge za drugi teden tehničnega predmeta Scala na FRI.
V drugem tednu se bomo spoznali s funkcijami, ki so v Scali državljani prve sorte. Kar pomeni, da z njimi lahko delamo kot z navadnimi podatki in si jih podajamo okoli preko argumentov funkcij.



## Naloga 1.
V prvi nalogi boste spoznali, kako lahko funkcije predstavljajo podatkovno strukturo množica.
```scala
type Set = Int => Boolean
```
Definirali smo torej tip množica, ki pa je v bistvu funkcija, ki za podano celo število vrne ali pripada množici ali ne.

Kot prvo funkcijo implementirajte dve funkciji za gradnjo osnovnih množic
```scala
def singleton(n:Int): Set
def interval(a:Int, b:Int): Set
```
Prva funkcija za podano celo število vrne množico, ki vsebuje zgolj ta element. Druga pa za podani celi števili zgradi množico, ki vsebuje vsa števila iz intervala (vključno s podanimi števili).

Nato implementirajte unijo in presek funkcij:
```scala
def union(s1:Set, s2:Set): Set
def intersect(s1:Set, s2:Set): Set
```
Da boste malo preizkusili zgoraj implementirane funkcije, implementirajte funkcijo:
```scala
def dedup(l: List[Int]): List[Int]
```
ki iz podanega seznama odstrani vse duplikate (v novem seznamu naj bo vsak element v seznamu zastopan samo enkrat). Pri implementaciji obvezno uporabite predhodno definirane funkcije.

Pri implementaciji te funkcije se boste prvič srečali s seznami. Seznam je osnovna podatkovna struktura v funkcijskih programskih jezikih. Na tem mestu je dovolj, da poznate nekaj osnovnih funkcij.
```scala
val l = List(1,2,3,4,5) //ustvarjanje novega seznama
l1.isEmpty //preverjanje, če je seznam prazen
l1.head //prvi element seznama
l1.tail // seznam brez prvega elementa
0::l1 //dodajanje elementa na začetek seznama

```

## Naloga 2.** (dodatna naloga)
Pravokotnik v ravnini (s celoštevilskimi koordinatami) lahko predstavimo z dvema množicama (za vsako predpostavimo, da vsebuje en interval).

```scala
type Rectangle = (Set, Set)
```
Pravokotnik je podan kot par (angl. tuple) dveh množic. Tudi s pari se tukaj prvič srečamo, zato najprej nekaj funkcij za delo s pari:

```scala
val a = (1, 2) //ustvarjanje para
a._1 //dostop do prve komponente para
a._2 //dostop do druge komponente para
```

Uporabite množice definirane v predhodni nalogi in implementirajte:

```scala
def createRectangle(x1:Int, y1:Int, x2:Int, y2:Int ):Rectangle

def contains(x:Int, y:Int, r:Rectangle):Boolean

def intersect(r1:Rectangle, r2:Rectangle):Rectangle
```
Prva funkcija naj ustvari pravokotnik, ki je podan z dvema krajiščema, druga naj preveri, če je podana točka vsebovana v pravokotniku in tretja naj iz dveh pravokotnikov zgradi nov pravokotnik, ki je presek podanih dveh.

## Naloga 3.
V tej nalogi pa si bomo ogledali delo s funkcijami relne spremenljivke. Najprej definirajmo tip take fukcije.

```scala
type RealFunc = Double => Double
```
Nato implementirajte 4 funkcije, ki iz podanih funkcij konstruirajo nove realne funkcije:

```scala

def sum(f1:RealFunc, f2:RealFunc):RealFunc

def mult(f1:RealFunc, f2:RealFunc):RealFunc

def compose(f1:RealFunc, f2:RealFunc):RealFunc

def derive(f:RealFunc):RealFunc
```
Prva funkcija naj vrne novo fukcijo, ki predstavlja vsoto dveh podanih realnih funkcij, druga produkt, tretja pa kompozitum. Četrta funkcija naj vrne novo funkcijo, ki predstavlja odvod podane funkcije (implementirajte odvajanje enostavno numerično).

## Naloga 4.:crown:
Vsako funkcijo `Char=>Char` si lahko predstavljamo kot šifrirno funkcijo.

```scala
type Coder = Char => Char
```

 Napišite nekaj funkcij za delo s takimi šifrirnimi funkcijami.
 Najprej napišite funkcijo, ki uporabi poljubni kodirnik in zakodira podani niz (vrnjeni niz je zakodirani podani niz).
 ```scala
 def encode(s:String, f:Coder):String
 ```
Če želimo, da je kodirna funkcija uporabna, potem mora biti bijektivna. To namreč pomeni, da bo mogoče zakodirani niz sploh enolično odkodirati.
Napišite funkcijo, ki bo preverila, če je podani kodirnik bijektivna funkcija.
```scala
def isBijective(f:Coder):Boolean
```

Za odkodiranje podanega niza potrebujete inverzno funkcijo (ki pa je tudi kodirnik). Zapišite funkcijo, ki za podani kodirnik vrne njegov inverz.
```scala
def inverse(f:Coder):Coder
```

Nazadnje uporabite zgoraj definirane funkcije, da zapišete dekodirnik za podani niz (pri podanem kodirniku, ki je ta niz sproduciral),
```scala
def decode(s:String, f:Coder):String
```
