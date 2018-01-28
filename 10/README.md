# Naloge v desetem tednu
V desetem tednu se bomo spoprijeli s testiranjem lastnosti funkcij.
Spoznali bomo knjižnico ScalaCheck in napisali nekaj lastnosti za programe, ki ste jih že napisali, mogoče pa še kakšno enostavno funkcijo, ki je še niste.


## Naloga 1.

Napišite funkciji

```scala
def encode(s:String, move:Int)
def decode(s:String, move:Int)
```
prva naj zakodira podani niz (Cezarjev kod - zamik črk), druga pa ustrezno odkodira.

Zapišite lastnost(i) za ti dve funkciji.

Zapišite funkcijo:

```scala
def duplicate(l:List[Int]):List[Int]
```
ki vsak element seznama podvoji, npr:
```scala
duplicate(List(1,2,3)) == List(1,1,2,2,3,3)
```
Zapišite lastnosti za to funkcijo (preverite dolžine in če so vsebovani pravi elementi).

Napišite funkcijo:
```scala
def isPalindrome(s:String):Boolean
```
in zanjo zapišite dve lastnosti - najprej preverite pozitivne primere, nato pa še kakšnega negativnega.


Napišite svojo funkcijo za urejanje seznamov
```scala
def sort(l:List[Int]):List[Int]
```
in zanjo zapišite lastnost(i), ki morajo veljati za urejen seznam.


## Naloga 2.

Zapišite funkcijo za iskanje največjega skupnega delitelja dveh števil.
```scala
def gcd(a:Int, b:Int):Int
```
Zapišite lastnost za to funkcijo.
Ker je pri večjih številih težje preverjati pogoje, se omejite zgolj na majhna števila (torej uporabite omejen generator). Preverite, da je dobljeni rezultat res skupni delitelj in da je največji.


Zapišite funkcijo:
```scala
def slice(l:List[Int], k:Int):(Lis[Int],List[Int])
```
ta funkcija naj razreže seznam na dva dela, prvi del naj bo dolžine k, drugi pa naj bo ostanek seznama.
Zapišite dve lastnosti:
  1. prva naj velja za vse sezname in vsa števila - preverite, če konkatenacija rezultata da originalni seznam.
  2. druga naj velja za vse sezname, vendar zgolj za števila med 0 in dolžino seznama - preverite, če se dolžine ujemajo s podanim številom.


Zapišite funkcijo:
```scala
def powerset(l:List[Int]):List[List[Int]]
```
ki vrne seznam vseh možnih podseznamov (podmnožic) podanega seznama.
Zapišite lastnost te funkcije, pri tem pa uporabite omejen generator seznamov:
```scala
def genBoundedList(maxSize: Int, g: Gen[Int]): Gen[List[Int]] =
  Gen.choose(0, maxSize) flatMap { sz => Gen.listOfN(sz, g) }
```


## Naloga 3.:crown:

Pretestirajte vašo implementacijo binarnega iskalnega drevesa (BST - vaje 4) z zapisom ustreznih lastnosti.

Zapišite vsaj 3 netrivialne lastnosti funkcij.
