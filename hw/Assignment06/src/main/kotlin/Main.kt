fun main() {
    val text1 = """
        the quick brown fox jumps over the lazy dog. the quick brown fox is quick, 
        and the lazy dog is not quick but very lazy. the fox is very quick and jumps 
        over the lazy dog frequently.
    """.trimIndent()
    val markovMaker1 = MakeMarkov(text1)
    val markovText1 = markovMaker1.generateText(5)
    println("\nMarkov Paragraph:")
    println(markovText1)

    val text2 = """
        In a hole in the ground there lived a hobbit. Not a nasty, dirty, wet hole, filled with the ends of worms and an oozy smell,
         nor yet a dry, bare, sandy hole with nothing in it to sit down on or to eat: it was a hobbit-hole, and that means comfort.
    """.trimIndent()
    val markovMaker2 = MakeMarkov(text2)
    val markovText2 = markovMaker2.generateText(5)
    println("\nMarkov Paragraph:")
    println(markovText2)

    val text3 = """
        Call me Ishmael. Some years ago—never mind how long precisely—having little or no money in my purse, 
        and nothing particular to interest me on shore, I thought I would sail about a little and see the watery part of the world. 
        It is a way I have of driving off the spleen and regulating the circulation.
    """.trimIndent()
    val markovMaker3 = MakeMarkov(text3)
    val markovText3 = markovMaker3.generateText(5)
    println("\nMarkov Paragraph:")
    println(markovText3)
}
