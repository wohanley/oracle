package object oracle {

  type WordMap = Map[WordMapKey, Set[String]]

  def buildWordList(corpus: String) = {}

  /** Returns a WordMap updated to include words from corpus. */
  def appendToWordMap(wordMap: WordMap, corpus: String): WordMap = {
    val words = corpus.split(" ")
    words.length match {
      case n if n < 3 => wordMap
      case _ => {
        /* I don't like this at all, but my brain is too soft to straighten it
         * out at the moment. */
        var newWordMap = wordMap
        for (index <- (0 until (words.length - 2))) {

          val key = new WordMapKey(words.slice(index, index + 2))
          val newWord = words(index + 2)

          newWordMap = newWordMap.get(key) match {
            case None => newWordMap + (key -> Set(newWord))
            case existingMapping:Some[Set[String]] =>
              newWordMap + (key -> (existingMapping.get + newWord))
          }
        }
        newWordMap
      }
    }
  }
}
