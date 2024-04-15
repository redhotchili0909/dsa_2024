import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RBTreeTest {

    /**
     * Tests the insertion of a single value into the Red-Black Tree.
     * Verifies that the root node is correctly inserted and maintains the Black color property.
     */
    @Test
    fun testInsertSingleValue() {
        val tree = RBTree()
        tree.insert(10)

        val result = tree.lookup(10)
        assertNotNull(result)
        assertEquals(10, result?.data)
        assertEquals(Color.BLACK, result?.color)
    }

    /**
     * Tests the insertion of multiple values into the Red-Black Tree.
     * Checks whether all values can be found in the tree post-insertion.
     */
    @Test
    fun testInsertMultipleValues() {
        val tree = RBTree()
        val values = listOf(10, 15, 5, 20, 3)

        values.forEach { tree.insert(it) }

        values.forEach { value ->
            val result = tree.lookup(value)
            assertNotNull(result)
            assertEquals(value, result?.data)
        }
    }

    /**
     * Tests the lookup function for both existing and non-existing values.
     */
    @Test
    fun testLookup() {
        val tree = RBTree()
        tree.insert(10)
        tree.insert(20)
        tree.insert(30)

        assertNotNull(tree.lookup(10))
        assertNotNull(tree.lookup(20))

        assertNull(tree.lookup(25))
    }

    /**
     * Tests the maintenance of Red-Black Tree invariants after multiple insertions.
     * Invariants checked are proper coloring and black height uniformity across all paths.
     */
    @Test
    fun testCheckInvariants() {
        val tree = RBTree()
        val values = listOf(10, 15, 5, 20, 3, 25, 7, 17)

        values.forEach { tree.insert(it) }

        try {
            tree.checkInvariants()
        } catch (e: AssertionError) {
            fail("Tree invariants failed: ${e.message}")
        }
    }
}
