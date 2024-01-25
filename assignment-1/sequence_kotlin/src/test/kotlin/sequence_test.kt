import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SequenceTest {
    @Test
    /*
        Check that the submitted code correctly calculates the next number in the
        look-and-say sequence.
     */
    fun testNextNumber()
    {
        val expectedList = listOf(1, 11, 21, 1211, 111221, 312211, 13112221, 1113213211)
        assertEquals(nextNumber(expectedList[2]), expectedList[3])
    }

    @Test
    fun testLookAndSay()
    /*
       Check that the submitted code correctly prints the numbers of the look-and-
       say sequence.
    */
    {
        val expected = listOf(1, 11, 21, 1211, 111221, 312211, 13112221, 1113213211)
        assertEquals(expected, lookAndSay(8))
    }
}