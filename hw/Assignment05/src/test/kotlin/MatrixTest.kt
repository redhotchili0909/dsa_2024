import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class MatrixTest {

    /**
     * Tests the initialization of the matrix to ensure that all elements are set to zero by default.
     */
    @Test
    fun testMatrixInitialization() {
        val matrix = Matrix(2)
        assertEquals(0, matrix.get(0, 0))
        assertEquals(0, matrix.get(1, 1))
    }

    /**
     * Tests that an IllegalArgumentException is thrown when attempting to create a matrix with an invalid size.
     */
    @Test
    fun testInvalidSize() {
        assertThrows(IllegalArgumentException::class.java) {
            Matrix(0)
        }
    }

    /**
     * Tests the functionality of setting a value in the matrix and ensuring the value is updated correctly.
     */
    @Test
    fun testSet() {
        val matrix = Matrix(3)
        matrix.set(0, 0, 5)
        assertEquals(5, matrix.get(0, 0))
    }

    /**
     * Tests that an IndexOutOfBoundsException is thrown when attempting to access an element outside the bounds of the matrix.
     */
    @Test
    fun testGetOutOfBounds() {
        val matrix = Matrix(2)
        assertThrows(IndexOutOfBoundsException::class.java) {
            matrix.get(2, 2)
        }
    }

    /**
     * Tests the multiply function of the Matrix class.
     */
    @Test
    fun testMultiply() {
        val matrix1 = Matrix(2).apply {
            set(0, 0, 1)
            set(0, 1, 2)
            set(1, 0, 3)
            set(1, 1, 4)
        }
        val matrix2 = Matrix(2).apply {
            set(0, 0, 2)
            set(0, 1, 0)
            set(1, 0, 1)
            set(1, 1, 2)
        }
        val result = matrix1.just_multiply(matrix2)
        assertNotNull(result)
        assertEquals(4, result!!.get(0, 0))
        assertEquals(4, result.get(0, 1))
        assertEquals(10, result.get(1, 0))
        assertEquals(8, result.get(1, 1))
    }

    /**
     * Tests the addition of two matrices.
     */
    @Test
    fun testAddition() {
        val matrixA = Matrix(2).apply {
            set(0, 0, 1)
            set(1, 1, 1)
        }
        val matrixB = Matrix(2).apply {
            set(0, 0, 2)
            set(1, 1, 2)
        }
        val expected = Matrix(2).apply {
            set(0, 0, 3)
            set(1, 1, 3)
        }
        val result = Matrix.add(matrixA, matrixB)
        for (i in 0 until 2) {
            for (j in 0 until 2) {
                assertEquals(expected.get(i, j), result.get(i, j))
            }
        }
    }

    /**
     * Tests the subtraction of two matrices.
     */
    @Test
    fun testSubtraction() {
        val matrixA = Matrix(2).apply {
            set(0, 0, 5)
            set(1, 1, 5)
        }
        val matrixB = Matrix(2).apply {
            set(0, 0, 2)
            set(1, 1, 2)
        }
        val expected = Matrix(2).apply {
            set(0, 0, 3)
            set(1, 1, 3)
        }
        val result = Matrix.subtract(matrixA, matrixB)
        for (i in 0 until 2) {
            for (j in 0 until 2) {
                assertEquals(expected.get(i, j), result.get(i, j))
            }
        }
    }

    /**
     * Tests Strassen's matrix multiplication algorithm.
     */
    @Test
    fun testStrassenMultiplication() {
        val matrixA = Matrix(2).apply {
            set(0, 0, 1)
            set(0, 1, 2)
            set(1, 0, 3)
            set(1, 1, 4)
        }
        val matrixB = Matrix(2).apply {
            set(0, 0, 2)
            set(0, 1, 0)
            set(1, 0, 1)
            set(1, 1, 2)
        }
        val expected = Matrix(2).apply {
            set(0, 0, 4)
            set(0, 1, 4)
            set(1, 0, 10)
            set(1, 1, 8)
        }
        val result = matrixA.strassenMultiply(matrixB)
        assertNotNull(result)
        result?.let {
            for (i in 0 until 2) {
                for (j in 0 until 2) {
                    assertEquals(expected.get(i, j), it.get(i, j))
                }
            }
        }
    }
}
