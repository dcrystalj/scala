# Naloge v četrtem tednu

V četrtem tednu se bomo spoznali z osnovnimi konstrukti objektno usmerjenega programiranja v Scali.
Na vajah bomo implementirali enostavno podatkovno strukturo, pri tem pa pazili tudi na eno ključnih lastnosi podatkovnih struktur v funkcijskih podatkovnih strukturah (**nespremenljivost**).


## Naloga 1.
Napišite ustrezne razrede, ki definirajo podatkovno strukturo binarno iskalno
drevo - angl. Binary Search Tree (BST). Seveda morajo vse operacije pustiti podatkovno strukturo nedotaknjeno, struktura naj bo **nespremenljiva**. Drevo naj hrani zgolj cela števila.

Spoznajmo to strukturo skozi primere.

###Ustvarjanje drevesa
Ustvarjanje novega drevesa
```scala
val a:BST = EmptyT  //prazno drevo
val b:BST = new Node(3, new Node(2,EmptyT,EmptyT), EmptyT)
 ```

Dodajanje novega elementa
```scala
val c = a & 5 // vrne Node(5, EmptyT, EmptyT)
```

Spremljajoči objekt BST in ustvarjanje novega drevesa s pomočjo tega objekta
```scala
val d:BST = BST(4,7,2,8,5,3,9)
```

###Informacije o drevesu

```scala
b.isEmpty //vrne false
b.head // vrne 3
b.left // vrne Node(2,EmptyT,EmptyT)
b.size // vrne 2
```
### BST kot urejena tabela
Implementirajte ustrezne funkcije, ki bodo omogočale uporabo BST-ja na sledeči način:
```scala
d(0)  //vrne najmanjši element v drevesu, t.j. 2
d(1)  //drugi najmanjši, t.j. 3
d(6)  // vrne sedmi element po velikosti, t.j. 9
d.toSortedList //vrne urejen seznam elementov List(2,3,4,5,7,8,9)
```

### Operaciji nad dvemi drevesi
```scala
def merge(other:BST):BST
override def equals(other:BST):Boolean
```
Prva funkcija naj dve drevesi združi v enega, druga pa naj primerja, če sta dve drevesi popolnoma enaki. Implementacija funkcije equals omogoča, da drevesa primerjate neposredno z operatorjem ==.

### Višjenivojske funkcije
Implementirajte višjenivojske funkcije nad binarnim iskalnim drevesom
```scala
def map(f:Int=>Int):BST
def foldInOrder(startVal:Int)(f:(Int, Int)=>Int):Int
def filter(f:Int=>Boolean):BST
```
Prva funkcija se sprehodi po drevesu in vsak element preslika s funkcijo f.
Druga funkcija naj zbere vse vrednosti s pomočjo funkcije f in začetne vrednosti startVal - združevanje naj začne po velikosti elementov v drevesu (inorder).
Tretja funkcija naj vrne novo binarno binarno iskalno drevo, v katerem so zgolj elementi, ki ustrezajo podanemu predikatu f (vse elemente e za katere je f(e) = true)

## Naloga 2. :crown:
Napišite funkcijo, ki bo vrnila niz z lepo izrisanim dvojiškim drevesom:
```scala
override def toString:String
```
Obstaja zelo veliko strategij za izris binarnega drevesa. Strategija, ki jo morate implementirati v tej funkciji, pa je sledeča: predstavljate si, da imate polno drevo (do največje globine so prisotna čisto vsa vozlišča).
Vsakemu vozlišču lahko dodelimo koordinati x in y, po sledečem pravilu:
 ```
 x = zaporedna številka vozlišča i v inorder obhodu polnega drevesa
y = globina na kateri se nahaja vozlišče i v polnem drevesu.
 ```

 Najprej definirajte funkcijo (v strukturi BST):
 ```scala
 def getCoords(depth:Int, offset:Int):List[(Int,Int,Int)]
 ```
 ki vrne seznam trojk (x, y, el) - torej seznam koordinat (x, y), na katerih se celo število el nahaja. V tem seznamu naj se nahajajo zgolj vozlišča, ki so dejansko prisotna v drevesu.

 Ta seznam nato uporabite pri izrisu. Poskusite se izogniti uporabi zanke, vse implementirajte s filter, fold, map, ...

 Kot primer, za drevo
 ```scala
 val a = BST(5,2,8,5,3,6,2)
 ```
dobimo seznam koordinat:
```scala
List((1,2,2), (3,1,2), (4,3,3), (5,2,5), (7,0,5), (9,2,6), (11,1,8))
```
In primer izrisa je lahko:
```
......5
..2.......8
2...5...6
...3
```
Format izrisa seveda ni natanko določen, lahko ga tudi polepšate :smile:.
