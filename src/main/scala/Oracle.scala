package oracle

object Oracle {

  import scala.util.Random
  import java.util.Date

  def main(args: Array[String]) = {
    Random.setSeed(new Date().getTime())
    println(tellFortune(buildWordMap(List("fortunes", "wisdom", "goedel"))))
  }
}
