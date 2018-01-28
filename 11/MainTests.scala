import org.specs2.mutable._

class Week11Tests extends Specification {
    "Nat " should {
        "do nat" in {
            Nat.apply(0) mustEqual Z
            Nat.apply(2) mustEqual S(S(Z))
        }

        "+" in {
            (Z)+(Z) mustEqual (Z)
            S(Z)+(Z) mustEqual (S(Z))
            S(Z)+(S(Z)) mustEqual (S(S(Z)))
        }

        "*" in {
            S(Z)*(Z) mustEqual Z
            Z*S(Z) mustEqual Z
            S(Z)*(S(Z)) mustEqual (S(Z))
            S(S(S(Z)))*(S(S(Z))) mustEqual S(S(S(S(S(S(Z))))))
        }


        "-" in {
            (Z)-(Z) mustEqual (Z)
            S(Z)-(Z) mustEqual (S(Z))
            S(Z)-(S(Z)) mustEqual (Z)
            S(S(S(Z))) - S(S(Z)) mustEqual S(Z)
            S(S(Z)) - S(S(S(Z))) mustEqual Z
        }
    }

    "tree " should {
        "3 " in {
            // Tree.numnodes(Node(Node(Empty,Empty), Node(Empty,Empty))) mustEqual 3
            // Tree.genTrees(3).length mustEqual 5
            Tree.wrapper(3).length mustEqual 5
            Tree.wrapper(4).length mustEqual 14
            // Tree.genTrees(4).length mustEqual 14
        }
    }


}
