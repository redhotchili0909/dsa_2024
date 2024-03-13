import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import NeedlemanWunsch.Companion.needlemanWunsch

class NeedlemanWunschTest {

    /**
     * Tests the Needleman-Wunsch function with identical sequences.
     */
    @Test
    fun testIdenticalSequences() {
        val seq = "GATTACA"
        val (align1, align2) = needlemanWunsch(seq, seq)
        assertEquals(seq, align1)
        assertEquals(seq, align2)
    }

    /**
     * Tests the Needleman-Wunsch function with completely different sequences of the same length.
     */
    @Test
    fun testCompletelyDifferentSequences() {
        val seq1 = "AAAAAA"
        val seq2 = "CCCCCC"
        val (align1, align2) = needlemanWunsch(seq1, seq2)
        println(align1)
        println(align2)
        assertEquals(0, align1.filter { it == '-' }.count())
        assertEquals(0, align2.filter { it == '-' }.count())
    }

    /**
     * Tests the Needleman-Wunsch function with empty sequences.
     * Expects the aligned sequences to also be empty.
     */
    @Test
    fun testEmptySequences() {
        val seq1 = ""
        val seq2 = ""
        val (align1, align2) = needlemanWunsch(seq1, seq2)
        assertEquals("", align1)
        assertEquals("", align2)
    }
}
