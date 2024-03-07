import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class HeapSortTest {

    /**
     * Tests that heapSort correctly sorts an array containing a single element.
     */
    @Test
    fun `sorts an array of single element`() {
        val arr = intArrayOf(1)
        assertContentEquals(intArrayOf(1), heapSort(arr))
    }

    /**
     * Tests that heapSort correctly sorts an array containing multiple elements.
     */
    @Test
    fun `sorts an array of multiple elements`() {
        val arr = intArrayOf(4, 2, 5, 3, 1)
        assertContentEquals(intArrayOf(1, 2, 3, 4, 5), heapSort(arr))
    }

    /**
     * Tests that heapSort correctly sorts an array containing negative numbers.
     */
    @Test
    fun `sorts an array with negative numbers`() {
        val arr = intArrayOf(-3, -1, -2)
        assertContentEquals(intArrayOf(-3, -2, -1), heapSort(arr))
    }

    /**
     * Tests that heapSort correctly handles and maintains an already sorted array.
     */
    @Test
    fun `sorts an already sorted array`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        assertContentEquals(intArrayOf(1, 2, 3, 4, 5), heapSort(arr))
    }

    /**
     * Tests that heapSort correctly sorts an array that is sorted in reverse order.
     */
    @Test
    fun `sorts a reverse sorted array`() {
        val arr = intArrayOf(5, 4, 3, 2, 1)
        assertContentEquals(intArrayOf(1, 2, 3, 4, 5), heapSort(arr))
    }
}


class InsertionSortTest {

    /**
     * Tests that insertionSort correctly sorts an array containing a single element.
     */
    @Test
    fun `sorts an array of single element`() {
        val arr = intArrayOf(1)
        insertionSort(arr)
        assertContentEquals(intArrayOf(1), arr)
    }

    /**
     * Tests that insertionSort correctly sorts an array containing multiple elements.
     */
    @Test
    fun `sorts an array of multiple elements`() {
        val arr = intArrayOf(9, 4, 7, 2, 5)
        insertionSort(arr)
        assertContentEquals(intArrayOf(2, 4, 5, 7, 9), arr)
    }

    /**
     * Tests that insertionSort correctly sorts an array containing negative numbers.
     */
    @Test
    fun `sorts an array with negative numbers`() {
        val arr = intArrayOf(-1, -3, -2)
        insertionSort(arr)
        assertContentEquals(intArrayOf(-3, -2, -1), arr)
    }

    /**
     * Tests that insertionSort correctly handles and maintains an already sorted array.
     */
    @Test
    fun `sorts an already sorted array`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        insertionSort(arr)
        assertContentEquals(intArrayOf(1, 2, 3, 4, 5), arr)
    }

    /**
     * Tests that insertionSort correctly sorts an array that is sorted in reverse order.
     */
    @Test
    fun `sorts a reverse sorted array`() {
        val arr = intArrayOf(5, 4, 3, 2, 1)
        insertionSort(arr)
        assertContentEquals(intArrayOf(1, 2, 3, 4, 5), arr)
    }
}


class QuickSortTest {

    /**
     * Tests that quickSort correctly sorts an array containing a single element.
     */
    @Test
    fun `sorts an array of single element`() {
        val arr = intArrayOf(1)
        quickSort(arr, 0, arr.lastIndex)
        assertContentEquals(intArrayOf(1), arr)
    }

    /**
     * Tests that quickSort correctly sorts an array containing multiple elements.
     */
    @Test
    fun `sorts an array of multiple elements`() {
        val arr = intArrayOf(8, 4, 3, 7, 6)
        quickSort(arr, 0, arr.lastIndex)
        assertContentEquals(intArrayOf(3, 4, 6, 7, 8), arr)
    }

    /**
     * Tests that quickSort correctly sorts an array containing negative numbers.
     */
    @Test
    fun `sorts an array with negative numbers`() {
        val arr = intArrayOf(-5, -1, -4, -2)
        quickSort(arr, 0, arr.lastIndex)
        assertContentEquals(intArrayOf(-5, -4, -2, -1), arr)
    }

    /**
     * Tests that quickSort correctly handles and maintains an already sorted array.
     */
    @Test
    fun `sorts an already sorted array`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        quickSort(arr, 0, arr.lastIndex)
        assertContentEquals(intArrayOf(1, 2, 3, 4, 5), arr)
    }

    /**
     * Tests that quickSort correctly sorts an array that is sorted in reverse order.
     */
    @Test
    fun `sorts a reverse sorted array`() {
        val arr = intArrayOf(5, 4, 3, 2, 1)
        quickSort(arr, 0, arr.lastIndex)
        assertContentEquals(intArrayOf(1, 2, 3, 4, 5), arr)
    }
}


class SelectionSortTest {

    /**
     * Tests that selectionSort correctly sorts an array containing a single element.
     */
    @Test
    fun `sorts an array of single element`() {
        val arr = intArrayOf(1)
        selectionSort(arr)
        assertContentEquals(intArrayOf(1), arr)
    }

    /**
     * Tests that selectionSort correctly sorts an array containing multiple elements.
     */
    @Test
    fun `sorts an array of multiple elements`() {
        val arr = intArrayOf(5, 3, 8, 4, 2)
        selectionSort(arr)
        assertContentEquals(intArrayOf(2, 3, 4, 5, 8), arr)
    }

    /**
     * Tests that selectionSort correctly sorts an array containing negative numbers.
     */
    @Test
    fun `sorts an array with negative numbers`() {
        val arr = intArrayOf(-4, -1, -3, -2)
        selectionSort(arr)
        assertContentEquals(intArrayOf(-4, -3, -2, -1), arr)
    }

    /**
     * Tests that selectionSort correctly handles and maintains an already sorted array.
     */
    @Test
    fun `sorts an already sorted array`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        selectionSort(arr)
        assertContentEquals(intArrayOf(1, 2, 3, 4, 5), arr)
    }

    /**
     * Tests that selectionSort correctly sorts an array that is sorted in reverse order.
     */
    @Test
    fun `sorts a reverse sorted array`() {
        val arr = intArrayOf(5, 4, 3, 2, 1)
        selectionSort(arr)
        assertContentEquals(intArrayOf(1, 2, 3, 4, 5), arr)
    }
}

