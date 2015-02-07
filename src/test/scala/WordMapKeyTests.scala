import org.specs2.mutable._
import oracle.WordMapKey

class WordMapKeySpec extends Specification {

  "Empty keys" should {
    "be equal" in {
      new WordMapKey(List()) == new WordMapKey(List())
    }
    "have equal hashes" in {
      new WordMapKey(List()).hashCode == new WordMapKey(List()).hashCode
    }
  }

  "Pairwise equal keys" should {
    "be equal" in {
      new WordMapKey(List("hi", "hello")) == new WordMapKey(List("hi", "hello"))
    }
    "have equal hashes" in {
      new WordMapKey(List("hi", "hello")).hashCode == new WordMapKey(List("hi", "hello")).hashCode
    }
  }

  "Keys differing only by case" should {
    "be equal" in {
      new WordMapKey(List("Hi", "HeLLO")) == new WordMapKey(List("hi", "hEllo"))
    }
    "have equal hashes" in {
      new WordMapKey(List("Hi", "HeLLO")).hashCode == new WordMapKey(List("hi", "hEllo")).hashCode()
    }
  }

  "Disordered keys" should {
    "not be equal" in {
      new WordMapKey(List("hi", "hello")) != new WordMapKey(List("hello", "hi"))
    }
    "have different hashes" in {
      new WordMapKey(List("hi", "hello")).hashCode != new WordMapKey(List("hello", "hi")).hashCode
    }
  }
}
