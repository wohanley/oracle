package object oracle {

  type WordList = Map[WordListKey, Traversable[String]]

  def buildWordList(corpus: String) = {}

  private def appendToWordList(wordList: WordList, corpus: String): WordList = {
    val words = corpus.split(" ")
    words.length match {
      case n if n < 3 => wordList
      case _ => {
        /** I don't like this at all, but my brain is too soft to straighten it
          * out at the moment.
          */
        var newWordList = wordList
        for (index <- (0 until (words.length - 2))) {
          newWordList = newWordList + new Tuple2(new WordListKey(List("Hi")), List("Hi"))
        }
        newWordList
      }
    }
  }
}
