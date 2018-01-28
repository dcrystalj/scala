

object Main{
    val abc = ('a' to 'z').toList
    def rotate(ls: List[Char], n: Int): List[Char] = {
        val nBounded = if (ls.isEmpty) 0 else n % ls.length
        if (nBounded < 0) rotate(ls, nBounded + ls.length)
        else (ls drop nBounded) ++ (ls take nBounded)
    }

    def encode(s:String, move:Int) = {
        s.map(x => rotate(abc, move)(x-'a'))
    }

    def decode(s:String, move:Int) = {
        s.map(x => rotate(abc, -move)(x-'a'))
    }

    def duplicate(l:List[Int]):List[Int] = {
        l.map(x => List(x,x)).flatten
    }

    def isPalindrome(s:String):Boolean = {
        s == s.reverse
    }

    def sort(l:List[Int]):List[Int] = {
        def remove(num: Int, list: List[Int]) = list diff List(num)

        if (l.isEmpty)
            l
        else
            l.min :: sort(remove(l.min, l))
    }


    def gcd(a:Int, b:Int):Int = {
        if (b == 0) a
        else gcd (b, a%b)
    }

    def slice(l:List[Int], k:Int):(List[Int],List[Int]) = {
        (l.take(k), l.drop(k))
    }

    def powerset(l:List[Int]):List[List[Int]] = {
        def loop(n:Int):List[List[Int]] = {
            if (n == 0) List(List())
            else {
                val pair = slice(l, n)
                powerset(pair._1) ++ powerset(pair._2) ++ loop(n-1)
            }
        }
        if (l.length > 0) l::loop(l.length-1).distinct
        else List(l)
    }


}
