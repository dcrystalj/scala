import org.specs2.mutable._

class Week6Tests extends Specification {
   // Testi za Q1
    "naloga 1" should {
        "q1 enque" in {
            QEmpty1.enQ(List(1,2,3)) mustEqual QNode1(List(1,2,3), QEmpty1)
            QNode1(List(1,2,3), QEmpty1).enQ(List(1,2,3,4)) mustEqual QNode1(List(1,2,3,4), QNode1(List(1,2,3), QEmpty1))
        }
        "q1 deque" in {
            QEmpty1.deQ must throwA[NoSuchElementException]
            QNode1(List(1,2,3), QEmpty1).deQ mustEqual (QEmpty1, List(1,2,3))
            QNode1(List(1,2,3,4), QNode1(List(1,2,3), QEmpty1)).deQ mustEqual (QNode1(List(1,2,3,4), QEmpty1), List(1,2,3))
        }
        "count ACC q1 " in {
            val seq = List(Some(List(1)), Some(List(2)), None, None )
            Q1.countAcc(seq) mustEqual 5
        }

        "q2 enque" in {
            QEmpty2.enQ(List(1,2,3)) mustEqual QNode2(List(), List(List(1,2,3)))
            QNode2(List(), List(List(1,2,3))).enQ(List(1,2,3,4)) mustEqual QNode2(List(), List(List(1,2,3,4), List(1,2,3)))
        }
        "q2 deque" in {
            QEmpty2.deQ must throwA[NoSuchElementException]
            QNode2(List(), List(List(1,2,3,4))).deQ mustEqual (QEmpty2, List(1,2,3,4))
            QNode2(List(), List(List(1,2,3,4), List(1,2,3))).deQ mustEqual (QNode2(List(List(1,2,3,4)), List()), List(1,2,3))
            QNode2(List(List(1,2,3,4)), List()).deQ mustEqual (QNode2(List(), List()), List(1,2,3,4))
        }
        "count ACC q2 " in {
            val seq = List(Some(List(1)), Some(List(2)), None, None )
            Q2.countAcc(seq) mustEqual 6
        }
    }

    "maze " should {
        "apply " in {
            val maze = "****************\n            *  *\n****  ****  *  *\n*     *        *\n*  *************\n*     *        *\n****  *  ****  *\n*     *  *     *\n*  *******  ****\n*               \n****************"
            val m = Maze(maze)
            m.solution mustEqual List(159, 158, 157, 156, 155, 154, 153, 152, 151, 150, 149, 148, 147, 146, 130, 114, 115, 116, 100, 84, 83, 82, 66, 50, 51, 52, 36, 20, 19, 18, 17, 16)
            m.solutionView mustEqual "****************\nooooo       *  *\n****o ****  *  *\n* ooo *        *\n* o*************\n* ooo *        *\n****o *  ****  *\n* ooo *  *     *\n* o*******  ****\n* oooooooooooooo\n****************"
        }
    }

}
