//main
class Main{
  // ------------ Naloga 1. ---------------
  //  Napiši funkcijo pyramid(n:Int):String, ki vrne piramido z n vrsticami
  //  Npr. pyramid(5) vrne niz:
  //      *
  //     ***
  //    *****
  //   *******
  //  *********

  def pyramid(n:Int): String = {
    def printer(i:Int, c:Char, s:String): String = {
      if(i > 0) {
        printer(i-1, c, s + c);
      }
      else {
        s;
      }
    }

    def loop(i:Int, s:String): String = {
      if (i>0) {
        val p1 = printer(i-1, ' ', s);
        val p2 = printer(2*(n-i) + 1, '*', p1);
        loop(i-1, p2 + "\n");
      }
      else {
        s;
      }
    }

    loop(n, "");
  }

  // ------------ Naloga 2. ---------------
  //  Napišite funkcijo, ki za podani niz vrne isti niz, kjer v zaporednem nizu
  // istih znakov ohrani zgolj enega - npr. pri vhodu aabbba,  dobimo rezultat aba.
  def uniq(s:String): String = {
    def whileLoop(str:String, previous:Char): String = {
      if (!str.isEmpty()) {
        if (str.head == previous) {
          whileLoop(str.tail, previous);
        }
        else {
          previous + whileLoop(str.tail, str.head);
        }
      }
      else {
        previous + ""
      }
    }

    whileLoop(s.tail, s.head);
  }


  // ------------ Naloga 3. ---------------
  //  Napišite funkcijo, ki za podano število ugotovi, ali je to število
  //  (verjetno) Lycherelovo število.
  // https://projecteuler.net/problem=55
  def isLycherel(n: BigInt): Boolean = {
    def lycherel(depth: Int, num: BigInt): Boolean = {
      if (depth > 0) {
        val s = num.toString
        val r = s.reverse
        if (s == r) {
          false
        }
        else {
          lycherel(depth-1, BigInt(s) + BigInt(r))
        }
      }
      else {
        true
      }
    }

    lycherel(70, n);
  }

}
