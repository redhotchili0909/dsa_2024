import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MapTest {

    /**
     * Tests both insertion of a key-value pair and reading of a value using its key.
     */
    @Test
    fun testInsertionAndGet() {
        val associativeArray = AssociativeArray<String, Int>()
        associativeArray["key1"] = 1
        assertEquals(1, associativeArray["key1"])
    }

    /**
     * Tests updating the value associated with a key and ensures the update is reflected.
     */
    @Test
    fun testUpdate() {
        val associativeArray = AssociativeArray<String, Int>()
        associativeArray["key1"] = 1
        associativeArray["key1"] = 2
        assertEquals(2, associativeArray["key1"])
    }

    /**
     * Tests the existence of a key within the associative array and the absence of a non-existent key.
     */
    @Test
    fun testContains() {
        val associativeArray = AssociativeArray<String, Int>()
        associativeArray["key1"] = 1
        assertTrue("key1" in associativeArray)
        assertFalse("key2" in associativeArray)
    }

    /**
     * Tests the removal of a key-value pair from the associative array.
     */
    @Test
    fun testRemove() {
        val associativeArray = AssociativeArray<String, Int>()
        associativeArray["key1"] = 1
        assertTrue(associativeArray.remove("key1"))
        assertFalse("key1" in associativeArray)
    }

    /**
     * Tests the size method of the associative array, ensuring it increases with additions and decreases with removals.
     */
    @Test
    fun testSize() {
        val associativeArray = AssociativeArray<String, Int>()
        associativeArray["key1"] = 1
        associativeArray["key2"] = 2
        assertEquals(2, associativeArray.size())
        associativeArray.remove("key1")
        assertEquals(1, associativeArray.size())
    }

    /**
     * Tests the rehashing process of the associative array to ensure it still functions correctly with multiple items.
     */
    @Test
    fun testRehashing() {
        val associativeArray = AssociativeArray<String, Int>(4) // Small capacity to trigger rehashing quickly
        for (i in 1..10) {
            associativeArray["key$i"] = i
        }
        assertEquals(10, associativeArray.size())
        for (i in 1..10) {
            assertEquals(i, associativeArray["key$i"])
        }
    }
}

