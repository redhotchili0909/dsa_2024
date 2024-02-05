class MutableList {
    private var array = intArrayOf()
    private var next = 0

    /**
     * Grow the array by a multiplicative factor
     */
    private fun growArray() {
        val temp = if (array.isEmpty()) {
            IntArray(1)
        } else {
            IntArray(array.size*2)
        }
        for (i in 0..<next) {
            temp[i] = array[i]
        }
        array = temp
    }

    /**
     * Add [element] to the end of the list
     */
    fun add(element: Int) {
        if (next >= array.size) {
            growArray()
        }
        array[next] = element
        next++
    }

    /**
     * Remove all elements from the list
     */
    fun clear() {
        next = 0
    }

    /**
     * @return the size of the list
     */
    fun size(): Int {
        return next
    }

    /**
     * @parameter index the index to return
     * @return the element at [index]
     */
    operator fun get(index: Int):Int {
        if (index >= size()){
            throw IndexOutOfBoundsException()
        }
        return array[index]
    }

    /**
     * Store [value] at position [index]
     * @parameter index the index to set
     * @parameter value to store at [index]
     */
    operator fun set(index: Int, value: Int) {
        array.set(index, value)
    }
}