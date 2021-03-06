package object oracle {

  import scala.collection.immutable.HashMap
  import java.util.Scanner
  import java.io.File
  import scala.util.Random

  type WordMap = Map[WordMapKey, Set[String]]

  def buildWordMap(fileNames: List[String]): WordMap = {
    var wordMap: WordMap = new HashMap[WordMapKey, Set[String]]()
    for (fileName <- fileNames) {
      val scanner = new Scanner(new File(fileName))
      scanner.useDelimiter(System.lineSeparator() + "%" + System.lineSeparator())
      while (scanner.hasNext) {
        wordMap = appendToWordMap(wordMap, scanner.next)
      }
    }
    wordMap
  }

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
            case Some(set) =>
              newWordMap + (key -> (set + newWord))
          }
        }
        newWordMap
      }
    }
  }

  def tellFortune(wordMap: WordMap): String = {
    val seed = getRandomKey(wordMap)
    val word = seed.words.head
    val nextWord = seed.words.tail.head
    val fortune = new StringBuilder(word + " " + nextWord + " ")

    val key = new WordMapKey(List(word, nextWord))

    buildFortune(fortune, wordMap, key)
  }

  private def buildFortune(fortune: StringBuilder, wordMap: WordMap, key: WordMapKey): String = {

    if (fortune.length > 500) return fortune.toString()

    wordMap.get(key) match {
    /* Let's say we have a corpus ending "if you drive fast enough." If, as is
     * possible, "fast enough." isn't anywhere else in our word map, then
     * trying to get ("fast", "enough.") will fail - it's never been followed
     * by anything. In that case, we're done! */
      case None => fortune.toString()
      case words: Some[Set[String]] => {
        // pick a random word
        getRandom(words.get) match {
          // I'm not sure what's happened in this case. Let's just quit.
          case None => fortune.toString()
          case word: Some[String] => {
            // add our randomly chosen word to the fortune
            fortune ++= word.get + " "
            return buildFortune(fortune, wordMap, advanceKey(word.get, key))
          }
        }
      }
    }
  }

  private def advanceKey(newWord: String, key: WordMapKey): WordMapKey =
    new WordMapKey(key.words.tail ++ List(newWord))

  def getRandom[T](iterable: Iterable[T]): Option[T] =
    Random.shuffle(iterable).headOption

  /** Return a random key from the map.
    * There's something very funny going on here that I can't figure out.
    * getRandom(map.keys) ought to work, but it always returns the same thing.
    * I assume it's something to do with my overriding hashCode or equals on
    * WordMapKey? Curious... */
  def getRandomKey(map: WordMap): WordMapKey = {
    val index = Random.nextInt(map.size)
    val array = map.keys.toArray
    array(index)
  }
}

