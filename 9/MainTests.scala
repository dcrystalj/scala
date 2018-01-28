import org.specs2.mutable._

class Week9Tests extends Specification {

    "tweeter " should {
        "check if tweet is in english" in {
            val e = AdvancedManipulator.isEnglish(List("a","nje","se","hecat"))
            e mustEqual false
            val f = AdvancedManipulator.isEnglish(List("a","none","reality","computer"))
            f mustEqual true
        }

        //  "check if tweet is in english and get frequent words" in {
        //     val e = FrequencyManipulator
        //     e.process("ne se hecat ejga stari")
        //     e.process("bla bla bla ejga stari")
        //     // e.outputFinal mustEqual nothing
        //     val f = FrequencyManipulator
        //     f.process("weirdos commandos has gone")
        //     f.process("we are one united scala nation")
        //     // f mustEqual
        // }
    }
}
