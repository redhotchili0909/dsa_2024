import kotlin.random.Random
import NeedlemanWunsch.Companion.needlemanWunsch

fun main() {
    /**
     * Generates a square matrix of a given size filled with random integers in the range [0, 10).
     *
     * @param size The size of the matrix
     * @return A [Matrix] instance of the specified size, filled with random integers.
     */
    fun generateRandomMatrix(size: Int): Matrix {
        val matrix = Matrix(size)
        for (i in 0 until size) {
            for (j in 0 until size) {
                matrix.set(i, j, Random.nextInt(0, 10))
            }
        }
        return matrix
    }

    /**
     * Performs a limit test to compare the performance of traditional matrix multiplication
     * and Strassen's matrix multiplication algorithm.
     */
    fun limitTest() {
        var size = 2
        val maxSize = 1024
        while (size <= maxSize) {
            val matrixA = generateRandomMatrix(size)
            val matrixB = generateRandomMatrix(size)

            val traditionalStartTime = System.nanoTime()
            matrixA.just_multiply(matrixB)
            val traditionalEndTime = System.nanoTime()

            val strassenStartTime = System.nanoTime()
            matrixA.strassenMultiply(matrixB)
            val strassenEndTime = System.nanoTime()

            val traditionalDuration = traditionalEndTime - traditionalStartTime
            val strassenDuration = strassenEndTime - strassenStartTime

            println("Size: $size, Traditional: $traditionalDuration, Strassen: $strassenDuration")

            if (strassenDuration < traditionalDuration) {
                println("Strassen wins at size: $size")
                break
            }

            size *= 2 // Increment size (e.g., 2, 4, 8, 16...), adjust as needed for your test range
        }
    }

    /**
     * Code was running too long for me to actually verify at what size Strassen would be faster :(
     */
    limitTest()


    /**
     * Same here. Couldn't verify with the sample sequences, because the code ran too long :(
     */
    val targetGenome = "GGATCCGACAGGGAAAATCGTTGAGCGTGTTGTGATAAAAAAGATCGCGACTATGGCGATGCTTTCGTAATGCATTCATGGGATCC"
    val testAgainst = "TGGCCACCACGATAGCAGAATTTTTTCGCGATAATGGAAAGCGCATTGCC"

    val (alignment1, alignment2) = needlemanWunsch(targetGenome,testAgainst)

    println("Alignment:")
    println(alignment1)
    println(alignment2)

}
