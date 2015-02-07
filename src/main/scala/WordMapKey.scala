package oracle

class WordMapKey(keyWords: Iterable[String]) {

  import java.util.Locale

  def words = keyWords

  override def equals(other: Any) = 
    other match {
      case otherWlk: WordMapKey => sameElements(otherWlk)
      case _ => false
    }

  private def sameElements(other: WordMapKey): Boolean =
    (words, other.words).zipped.forall(
      (word, otherWord) => word.equalsIgnoreCase(otherWord))

  override def hashCode: Int =
    (for ((word, index) <- keyWords.zipWithIndex)
    yield word.toLowerCase(Locale.ENGLISH).hashCode * index).sum

  override def toString = "Key(" + words.mkString(", ") + ")"
}
