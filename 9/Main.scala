import twitter4j._
import scala.collection.mutable.HashMap
import scala.collection.JavaConversions._
import scala.io._
import scala.collection.mutable.Map
//Tega razreda ni potrebno spreminjati, samo dodajte svoje ključe iz Twitterja
class TweetStreamer(manipulator: TweetManipulator) {
  val config = new twitter4j.conf.ConfigurationBuilder()
    .setOAuthConsumerKey("") //sem vpišite 4 "ključe"
    .setOAuthConsumerSecret("")
    .setOAuthAccessToken("")
    .setOAuthAccessTokenSecret("")
    .build

  def simpleStatusListener = new StatusListener() {
    def onStatus(status: Status) {
      val tweet = status.getText
      manipulator.process(tweet)
    }
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) { ex.printStackTrace }
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: StallWarning) {}
  }

}

trait TweetManipulator {
  def process(tweet: String)
  def outputFinal
}

//Primer obdelovalca tweetov
object TrivialManipulator extends TweetManipulator {
  def process(tweet: String) = {
    println("-------------------")
    println(tweet)
  }
  def outputFinal = println("Finished streaming")
}

//Primer enostavne naloge
object NalogaDemo {
  def main(args: Array[String]) {
    val tS = new TweetStreamer(TrivialManipulator)
    val twitterStream = new TwitterStreamFactory(tS.config).getInstance
    twitterStream.addListener(tS.simpleStatusListener)
    twitterStream.sample
    Thread.sleep(2000)
    TrivialManipulator.outputFinal
    twitterStream.cleanUp
    twitterStream.shutdown
  }
}

object AdvancedManipulator extends TweetManipulator {
  val file = Source.fromFile("resources/wordsEn.txt" )
  val english = file.getLines.toList

  def process(tweet: String) = {
    val t = tweet.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase
    val splitted = t.split(" ").toList
    if (isEnglish(splitted)) println(t) else print("")
  }

  def outputFinal = println("Finished streaming")

  def isEnglish(tweet:List[String]):Boolean = {
    val cnt = tweet.map(x => if (english.contains(x)) 1 else 0)
    (cnt.sum.toDouble / cnt.length.toDouble > .5)
  }
}


object Naloga1 {
  def main(args: Array[String]) {
    val tS = new TweetStreamer(AdvancedManipulator)
    val twitterStream = new TwitterStreamFactory(tS.config).getInstance
    twitterStream.addListener(tS.simpleStatusListener)
    twitterStream.sample
    Thread.sleep(5000)
    AdvancedManipulator.outputFinal
    twitterStream.cleanUp
    twitterStream.shutdown
  }

}

object FrequencyManipulator extends TweetManipulator {
  val english = Source.fromFile("resources/wordsEn.txt").getLines.toList
  val frequent = Source.fromFile("resources/commonEng.txt").getLines.toList
  val m = Map[String, Int]()

  def process(tweet: String) = {
    val splitted = tweet.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase.split(" ").toList
    if (isEnglish(splitted)) {
      val words = splitted.filter(x => !frequent.contains(x) && x.length >= 3)
      words.foreach(x => m(x) = m.getOrElse(x, 0) + 1)
    }
  }


  def isEnglish(tweet:List[String]):Boolean = {
    val cnt = tweet.map(x => if (english.contains(x)) 1 else 0)
    (cnt.sum.toDouble / cnt.length.toDouble > .5)
  }

  def outputFinal = m.toSeq.sortWith(_._2 > _._2).take(100).map(x => x._1).foreach(println)
}

object Naloga2 {
  def main(args: Array[String]) {
    val tS = new TweetStreamer(FrequencyManipulator)
    val twitterStream = new TwitterStreamFactory(tS.config).getInstance
    twitterStream.addListener(tS.simpleStatusListener)
    twitterStream.sample
    Thread.sleep(300000)
    FrequencyManipulator.outputFinal
    twitterStream.cleanUp
    twitterStream.shutdown
  }
}


object Naloga3 {
  def main(args: Array[String]) {
    //TODO
  }
}
