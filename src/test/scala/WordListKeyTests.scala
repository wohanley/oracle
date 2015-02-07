import org.specs2.mutable._
import oracle.WordListKey

class WorldListKeySpec extends Specification {

  "Empty keys" should {
    "be equal" in {
      new WordListKey(List()) == new WordListKey(List())
    }
  }

  "Pairwise equal keys" should {
    "be equal" in {
      new WordListKey(List("hi", "hello")) == new WordListKey(List("hi", "hello"))
    }
  }

  "Keys differing only by case" should {
    "be equal" in {
      new WordListKey(List("Hi", "HeLLO")) == new WordListKey(List("hi", "hEllo"))
    }
  }

  "Disordered keys" should {
    "not be equal" in {
      new WordListKey(List("hi", "hello")) != new WordListKey(List("hello", "hi"))
    }
  }
}
