import org.specs2.mutable._
import oracle._

class OraclePackageSpec extends Specification {

  "appendToWordList" should {
    "not change the word list for input shorter than chain length" in {
      val wordMap = Map(new WordMapKey(List("hello", "hi")) -> Set("Hi"))
      appendToWordMap(wordMap, "It was") == wordMap
    }
    "add new mappings" in {
      val wordMap = Map(new WordMapKey(List("hello", "hi")) -> Set("Hi"))
      val resultMap = appendToWordMap(wordMap, "The quick brown fox jumps over the brown fox who is slow jumps over the brown fox who is dead.")

      resultMap == Map(
        new WordMapKey(List("hello", "hi")) -> Set("Hi"),
        new WordMapKey(List("The", "quick")) -> Set("brown"),
        new WordMapKey(List("brown", "fox")) -> Set("jumps", "who", "who"),
        new WordMapKey(List("fox", "jumps")) -> Set("over"),
        new WordMapKey(List("fox", "who")) -> Set("is", "is"),
        new WordMapKey(List("is", "slow")) -> Set("jumps"),
        new WordMapKey(List("jumps", "over")) -> Set("the", "the"),
        new WordMapKey(List("over", "the")) -> Set("brown", "brown"),
        new WordMapKey(List("quick", "brown")) -> Set("fox"),
        new WordMapKey(List("slow", "jumps")) -> Set("over"),
        new WordMapKey(List("the", "brown")) -> Set("fox", "fox"),
        new WordMapKey(List("who", "is")) -> Set("slow", "dead."))
    }
  }
}
