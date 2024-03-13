/**
 * A class that represents a square matrix and supports basic operations such as setting and getting values.
 *
 * @param size The size of the matrix.
 * @constructor Creates a square matrix of the given size, initialized with zeros.
 * @throws IllegalArgumentException if the size is less than 1.
 */
class Matrix(size: Int) {
    val matrix: Array<Array<Int>> = Array(size) { Array(size) { 0 } }

    init {
        if (size < 1) throw IllegalArgumentException("Size must be at least 1.")
    }

    /**
     * Sets the value at the specified row and column indices.
     *
     * @param row The row index.
     * @param col The column index.
     * @param value The value to set.
     * @throws IndexOutOfBoundsException if the row or column index is out of bounds.
     */
    fun set(row: Int, col: Int, value: Int) {
        if (row !in matrix.indices || col !in matrix.indices) {
            throw IndexOutOfBoundsException("Row or column index out of bounds.")
        }
        matrix[row][col] = value
    }

    /**
     * Gets the value at the specified row and column indices.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The value at the specified indices.
     * @throws IndexOutOfBoundsException if the row or column index is out of bounds.
     */
    fun get(row: Int, col: Int): Int {
        if (row !in matrix.indices || col !in matrix.indices) {
            throw IndexOutOfBoundsException("Row or column index out of bounds.")
        }
        return matrix[row][col]
    }

    /**
     * Multiplies this matrix by another matrix using traditional matrix multiplication.
     * @param other The matrix to multiply by.
     * @return The result of the multiplication, or null if dimensions are incompatible.
     */
    fun just_multiply(other: Matrix): Matrix? {
        if (matrix.size != other.matrix.size) return null

        val result = Matrix(matrix.size)
        for (i in matrix.indices) {
            for (j in matrix.indices) {
                for (k in matrix.indices) {
                    result.matrix[i][j] += this.matrix[i][k] * other.matrix[k][j]
                }
            }
        }
        return result
    }

    companion object {
        /**
         * Adds two matrices.
         *
         * @param a The first matrix.
         * @param b The second matrix.
         * @return A new matrix representing the sum of the input matrices.
         */
        fun add(a: Matrix, b: Matrix): Matrix {
            val result = Matrix(a.matrix.size)
            for (i in a.matrix.indices) {
                for (j in a.matrix.indices) {
                    result.matrix[i][j] = a.matrix[i][j] + b.matrix[i][j]
                }
            }
            return result
        }

        /**
         * Subtracts the second matrix from the first.
         *
         * @param a The matrix to be subtracted from.
         * @param b The matrix to subtract.
         * @return A new matrix representing the difference of the input matrices.
         */
        fun subtract(a: Matrix, b: Matrix): Matrix {
            val result = Matrix(a.matrix.size)
            for (i in a.matrix.indices) {
                for (j in a.matrix.indices) {
                    result.matrix[i][j] = a.matrix[i][j] - b.matrix[i][j]
                }
            }
            return result
        }
    }

    /**
     * Splits the matrix into four equal quadrants.
     *
     * @return A list containing the four submatrices.
     */
    fun split(): List<Matrix> {
        val newsize = matrix.size / 2
        var blocks = listOf(Matrix(newsize), Matrix(newsize), Matrix(newsize), Matrix(newsize))
        for (i in matrix.indices) {
            for (j in matrix.indices) {
                val blockIndex = (if (i < newsize) 0 else 2) + if (j < newsize) 0 else 1
                blocks[blockIndex].matrix[i % newsize][j % newsize] = this.matrix[i][j]
            }
        }
        return blocks
    }

    /**
     * Combines four quadrants into a single matrix.
     *
     * @param a11 The top-left quadrant.
     * @param a12 The top-right quadrant.
     * @param a21 The bottom-left quadrant.
     * @param a22 The bottom-right quadrant.
     * @return A new matrix resulting from the combination of the four quadrants.
     */
    fun combine(a11: Matrix, a12: Matrix, a21: Matrix, a22: Matrix): Matrix {
        val result = Matrix(a11.matrix.size * 2)
        val newSize = a11.matrix.size
        for (i in 0 until newSize) {
            for (j in 0 until newSize) {
                result.matrix[i][j] = a11.matrix[i][j]
                result.matrix[i][j + newSize] = a12.matrix[i][j]
                result.matrix[i + newSize][j] = a21.matrix[i][j]
                result.matrix[i + newSize][j + newSize] = a22.matrix[i][j]
            }
        }
        return result
    }

    /**
     * Performs Strassen Multiplication on two matrices
     *
     * @param other The matrix to Strassen-multiply by
     */
    fun strassenMultiply(other: Matrix): Matrix? {
        if (this.matrix.size != other.matrix.size) return null

        if (matrix.size == 1) {
            return Matrix(1).also { it.set(0, 0, this.get(0, 0) * other.get(0, 0)) }
        }

        val (a11, a12, a21, a22) = this.split()
        val (b11, b12, b21, b22) = other.split()

        val m1 = add(a11, a22).strassenMultiply(add(b11, b22))!!
        val m2 = add(a21, a22).strassenMultiply(b11)!!
        val m3 = subtract(b12, b22).strassenMultiply(a11)!!
        val m4 = subtract(b21, b11).strassenMultiply(a22)!!
        val m5 = add(a11, a12).strassenMultiply(b22)!!
        val m6 = subtract(a21, a11).strassenMultiply(add(b11, b12))!!
        val m7 = subtract(a12, a22).strassenMultiply(add(b21, b22))!!

        val c11 = add(subtract(add(m1, m4), m5), m7)
        val c12 = add(m3, m5)
        val c21 = add(m2, m4)
        val c22 = add(subtract(add(m1, m3), m2), m6)

        return combine(c11, c12, c21, c22)
    }
}
