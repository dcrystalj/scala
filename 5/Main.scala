
import scala.collection.immutable.HashMap
import scala.math.pow


trait Expr{
	//implementirajte funkcijo value, ki vrne vrednost tega izraza npr. Some(3.5), če
	// pa za tega izraza ni mogoce ovrednotiti pa naj vrne None.
	// Funkcija ima opcijski parameter, v katerem so podane vrednosti spremenljivk
	// kot HashMap - ime spremenljivke preslika v neko vrednost.
	def value(h:HashMap[String, Double] = HashMap()):Option[Double] = {

		this match {
			case Num(n) => Some(n)
			case Var(v) => if (h.contains(v)) Some(h(v)) else None
			case Sum(a,b) => (a.value(h),b.value(h)) match{
				case (Some(a), Some(b)) => Some(a+b)
				case _ => None
			}
			case Prod(a,b) => (a.value(h),b.value(h)) match{
				case (Some(a), Some(b)) => Some(a*b)
				case _ => None
			}
			case Quot(a,b) => (a.value(h),b.value(h)) match{
				case (Some(a), Some(b)) => Some(a/b)
				case _ => None
			}
			case Pow(a, power) => (a.value(h)) match{
				case (Some(a)) => Some(pow(a,power))
				case _ => None
			}
		}
	}

	//v tem traitu implementirajte funkciji simplify in derive
	def derive(v:Var):Expr = {
		this match {
			case Num(n) => Num(0)
			case Var(a) => if (this == v) Num(1) else Num(0)
			case Sum(a,b) => Sum(a.derive(v),b.derive(v))
			case Prod(a,b) => Sum(Prod(a.derive(v),b),Prod(a, b.derive(v)))
			case Quot(a,b) => Quot(Sum(Prod(a.derive(v), b), Prod(Prod(a, b.derive(v)), Num(-1))), Pow(b,2))
			case Pow(a,p) => Prod(Num(p),Pow(a,p-1))
		}
	}
	//carska naloga
	def simplify:Expr = {

		this match{

			//vsota z 0, vsota dveh Numov vrne Num. 2/8
			case Sum(a, b) => (a,b) match{
				case (Num(0),b) => b.simplify
				case (a, Num(0)) => a.simplify
				case (Num(a), Num(b)) => Num(a+b)
				case _ => Sum(a.simplify, b.simplify)
			}

			//mnozenje z 1 ali z 0, mnozenje dveh Numov vrne Num. 3/8
			case Prod(a, b) => (a,b) match{
				case (Num(0.0),b) => Num(0.0)
				case (a, Num(0.0)) => Num(0.0)
				case (Num(1.0), b) => b.simplify
				case (a, Num(1.0)) => a.simplify
				case (Num(a), Num(b)) => Num(a*b)
				case _ => Prod(a.simplify, b.simplify)
			}
			
			//deljenje z 1. 1/8
			case Quot(a,b) => (a,b) match{
				case (a, Num(1)) => a.simplify
				case _ => Quot(a.simplify,b.simplify)
			}
			

			//potenciranje na 1 ali 0. 2/8
			case Pow(a,b) => (a,b) match{
				case (a,0) => Num(1)
				case (a,1) => a.simplify
				case _ => Pow(a.simplify,b)
			}
			//ostalo pusti na miru. 8/8
			case _ => this
		}

	}
}

object Expr{
	//v tem objektu implementirajte funkijo apply, ki iz niza, ki predstavlja izraz v postfiksni obliki zgradi Expr.

	def apply(s: String):Expr = {

		val l = s.split(" ").toList

		def stack(l: List[String], st:List[Expr]):Expr = {

			if(l.isEmpty){
				st.head
			}
			else{
				l.head match {
				case x if x.head.isDigit => stack(l.tail,Num(x.toInt)::st)
				case x if x.head.isLetter => stack(l.tail, Var(x)::st)
				case "+" => stack(l.tail, Sum(st.tail.head, st.head)::st.tail.tail)
				case "*" => stack(l.tail, Prod(st.tail.head, st.head)::st.tail.tail)
				case "/" => stack(l.tail, Quot(st.tail.head, st.head)::st.tail.tail)
				case x if x.startsWith("^") => stack(l.tail,Pow(st.head, x.substring(1).toInt)::st.tail)
			}
			}

		}
		stack(l,List())
	}
}

case class Var(n: String) extends Expr
case class Num(i: Double) extends Expr
case class Sum(e1:Expr, e2:Expr) extends Expr
case class Prod(e1:Expr, e2:Expr) extends Expr
case class Quot(e1:Expr, e2:Expr) extends Expr
case class Pow(e:Expr, k:Int) extends Expr


//zapišite implementacije case razredov:
//Var
//Num
//Sum
//Prod
//Quot
//Pow
