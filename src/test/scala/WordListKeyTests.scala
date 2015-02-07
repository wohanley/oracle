import org.specs2.mutable._
import oracle.WordListKey

class WorldListKeySpec extends Specification {

  "Empty keys" should {
    "be equal" in {
      new WordListKey(List()) == new WordListKey(List())
    }
    "have equal hashes" in {
      new WordListKey(List()).hashCode == new WordListKey(List()).hashCode
    }
  }

  "Pairwise equal keys" should {
    "be equal" in {
      new WordListKey(List("hi", "hello")) == new WordListKey(List("hi", "hello"))
    }
    "have equal hashes" in {
      new WordListKey(List("hi", "hello")).hashCode == new WordListKey(List("hi", "hello")).hashCode
    }
  }

  "Keys differing only by case" should {
    "be equal" in {
      new WordListKey(List("Hi", "HeLLO")) == new WordListKey(List("hi", "hEllo"))
    }
    "have equal hashes" in {
      new WordListKey(List("Hi", "HeLLO")).hashCode == new WordListKey(List("hi", "hEllo")).hashCode()
    }
  }

  "Disordered keys" should {
    "not be equal" in {
      new WordListKey(List("hi", "hello")) != new WordListKey(List("hello", "hi"))
    }
    "have different hashes" in {
      new WordListKey(List("hi", "hello")).hashCode != new WordListKey(List("hello", "hi")).hashCode
    }
  }
}
