import kotlin.random.Random

class MakeMarkov(private val originalText: String) {
    private val nextWords = AssociativeArray<String, AssociativeArray<String, Int>>()

    /**
     * Initializes the object and builds the word list and next words map from the original text.
     */
    init {
        buildWordList()
        buildNextWords()
    }

    /**
     * Splits the original text into a list of words.
     *
     * @return A list of words from the original text.
     */
    private fun buildWordList(): List<String> {
        return originalText.split("\\s+".toRegex())
    }

    /**
     * Builds a map where each word is associated with another map containing the words that follow it
     * and their occurrence count.
     */
    private fun buildNextWords() {
        val wordList = buildWordList()

        var currentWord: String? = ""
        for (word in wordList) {
            val followers = nextWords[currentWord.toString()] ?: AssociativeArray()
            val count = followers[word] ?: 0
            followers[word] = count + 1
            nextWords[currentWord!!] = followers

            currentWord = if (word.endsWith('.') || word.endsWith('?') || word.endsWith('!')) {
                ""
            } else {
                word
            }
        }
    }

    /**
     * Generates a random sentence based on the Markov chain constructed from the text.
     *
     * @return A randomly generated sentence.
     */
    fun generateSentence(): String {
        var sentence = ""
        var word = ""
        while (true) {
            val followers = nextWords[word]
            if (followers == null || followers.size() == 0) {
                break
            }

            val total = followers.keyValuePairs().sumOf { it.second }
            var index = Random.nextInt(total)
            var nextWord = ""

            for ((key, value) in followers.keyValuePairs()) {
                index -= value
                if (index < 0) {
                    nextWord = key
                    break
                }
            }

            sentence += "$nextWord "
            if (nextWord.endsWith('.') || nextWord.endsWith('?') || nextWord.endsWith('!')) {
                break
            }

            word = nextWord
        }

        return sentence.trim()
    }

    /**
     * Generates text with the specified number of sentences using the Markov chain.
     *
     * @param numSentences The number of sentences to generate.
     * @return A string containing the generated sentences.
     */
    fun generateText(numSentences: Int): String {
        return (1..numSentences).joinToString(" ") { generateSentence() }
    }
}

