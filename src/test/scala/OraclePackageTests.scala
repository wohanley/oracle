import org.specs2.mutable._
import oracle._

class OraclePackageSpec extends Specification {

  "appendToWordList" should {
    "not change the word list for input shorter than chain length" in {
      val wordMap = Map(new WordListKey(List("hello", "hi")) -> Set("Hi"))
      appendToWordList(wordMap, "It was") == wordMap
    }
    "add new mappings" in {
      val wordMap = Map(new WordListKey(List("hello", "hi")) -> Set("Hi"))
      val resultMap = appendToWordList(wordMap, "The quick brown fox jumps over the brown fox who is slow jumps over the brown fox who is dead.")

      resultMap == Map(
        new WordListKey(List("hello", "hi")) -> Set("Hi"),
        new WordListKey(List("The", "quick")) -> Set("brown"),
        new WordListKey(List("brown", "fox")) -> Set("jumps", "who", "who"),
        new WordListKey(List("fox", "jumps")) -> Set("over"),
        new WordListKey(List("fox", "who")) -> Set("is", "is"),
        new WordListKey(List("is", "slow")) -> Set("jumps"),
        new WordListKey(List("jumps", "over")) -> Set("the", "the"),
        new WordListKey(List("over", "the")) -> Set("brown", "brown"),
        new WordListKey(List("quick", "brown")) -> Set("fox"),
        new WordListKey(List("slow", "jumps")) -> Set("over"),
        new WordListKey(List("the", "brown")) -> Set("fox", "fox"),
        new WordListKey(List("who", "is")) -> Set("slow", "dead."))
    }
  }
}
