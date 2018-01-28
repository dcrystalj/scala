class Main {
  /* Naloga 1. Množice.
 * Funkcije tipa Int => Boolean lahko gledamo tudi kot predstavitev množic. Pri tej nalogi morate zapisati nekaj funkcij, ki omogočajo delo s tako predstavitvijo množic.
 */

  type Set = Int => Boolean

  // Zapiši funkcijo, ki za podano število vrne množico, ki vsebuje samo to število.
  def singleton(n: Int): Set =
  {
    (k: Int) => k == n
  }

  //  Zapišite funkcijo, ki iz dveh množic zgradi njuno unijo.
  def union(s1: Set, s2: Set): Set =
  {
    (k: Int) => s1(k) || s2(k)
  }

  //  Zapišite funkcijo, ki iz dveh množic zgradi njun presek.
  def intersect(s1: Set, s2: Set): Set =
  {
    (k: Int) => s1(k) && s2(k)
  }

  //  Zapišite funkcijo, ki iz podanih števil a in b (a<b) zgradi funkcijo, ki vsebuje vsa cela števila na intervalu [a,b].
  def interval(a: Int, b: Int): Set =
    (k: Int) => k >= a && k <= b

  //???  Zapišite funkcijo, ki iz podanega seznama odstrani vse duplikate. Uporabite množice definirane zgoraj.
  def dedup(l: List[Int]): List[Int] =
  {

    def filter(k: List[Int], s: Set, o: List[Int]): List[Int] = {
      if (! k.isEmpty) {
        val h = k.head
        if (! s(h)) {
          val s1 = union(s, singleton(h))
          filter(k.tail, s1, o ++ List(h))
        }
        else
          filter(k.tail, s, o)
      }
      else
        o
    }
    if (! l.isEmpty)
      filter(l, (singleton(l.head)), List(l.head))
    else
      l
  }

  /* Naloga 2. Pravokotniki.
   * Pravokotnik v ravnini (s celoštevilskimi koordinatami) lahko predstavimo z dvema množicama (za vsako predpostavimo, da vsebuje en interval).
   *
   */

  type Rectangle = (Set, Set)

  def createRectangle(x1: Int, y1: Int, x2: Int, y2: Int): Rectangle = ???

  def contains(x: Int, y: Int, r: Rectangle): Boolean = ???

  def intersect(r1: Rectangle, r2: Rectangle): Rectangle = ???

  /* Naloga 3. Funkcije realne spremenljivke
  */
  type RealFunc = Double => Double

  val eps = 0.00001
  //kompozitum dveh funkcij
  def compose(f1: RealFunc, f2: RealFunc): RealFunc =
    (k: Double) => f1(f2(k))

  //vsota dveh funkcij
  def sum(f1: RealFunc, f2: RealFunc): RealFunc =
    (k: Double) => f1(k) + f2(k)

  //produkt dveh funkcij
  def mult(f1: RealFunc, f2: RealFunc): RealFunc =
    (k: Double) => f1(k) * f2(k)

  //odvod funkcije - naredite to numerično
  def derive(f: RealFunc): RealFunc =
    (k: Double) =>  (f(k+eps) - f(k)) / eps

  /* Naloga 4. Kodiranje
  *  Vsako funkcijo Char=>Char si lahko predstavljamo kot šifrirno funkcijo. Napišite nekaj funkcij za delo s takimi šifrirnimi funkcijami.
  */
  type Coder = Char => Char
  //Podani niz zašifrirajte s podano funkcijo
  def encode(s: String, f: Coder): String = {
    def loop(str: String, newStr: String): String = {
      if (str.isEmpty)
        newStr
      else {
        loop(str.tail, newStr + f(str.head))
      }
    }

    loop(s, "")
  }

  //Za podano šifrirno funkcijo ugotovite, če je bijektivna
  def isBijective(f: Coder): Boolean = {
    def isBijective2(a: Int, b: Int): Boolean = {
      if (a >= 254)
        false
      else {
        if (a != b && f(a.toChar) == f(b.toChar)) {
          true
        }
        else {
          if (b >= 254)
            isBijective2(a+1, 0)
          else
            isBijective2(a, b+1)
        }
      }
    }

    isBijective2(0, 1)
  }

  //Za podano šifrirno funkcijo vrnite njen inverz
  def inverse(f: Coder): Coder = {
    def inverse2(a: Int, x: Char): Char = {
      if (f(a.toChar) == x || a > 255) {
        a.toChar
      }
      else {
          inverse2(a+1, x)
      }
    }

    ((x: Char) => inverse2(0, x))
  }

  //Podani niz, ki je bil zakodiran s podano šifrirno funkcijo, odšifrirajte
  def decode(s: String, f: Coder): String = {
    def inv: Coder = inverse(f)

    def loop(str: String, newStr: String): String = {
      if (str.isEmpty)
        newStr
      else
        loop(str.tail, newStr+inv(str.charAt(0)))
    }

    loop(s, "")
  }

}
