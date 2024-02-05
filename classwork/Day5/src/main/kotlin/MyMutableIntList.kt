class MyMutableIntList {
    private var array: IntArray = intArrayOf(1)
    private var size: Int = 1
    /**
     * Add [element] to the end of the list
     */
    fun add(element: kotlin.Int)
    {
        if(array.size >= size) {
            var sampleArray = intArrayOf(size*2)
            for (i in array.indices)
            {
                sampleArray[i] = array[i]
            }
            for (i in array.size..sampleArray.size)
            {
                sampleArray[i] = 0
            }
            array = sampleArray
            array[size] = element
            size ++
        }
        else{
            array[size] = element
            size ++
        }
    }
    /**
     * Remove all elements from the list
     */
    fun clear()
    {
        array = IntArray(size)
    }

    /*
     * @return the size of the list
     */
    fun size(): kotlin.Int
    {
        return size
    }

    /**
     * @param index the index to return
     * @return the element at [index]
     */
    operator fun get(index: kotlin.Int): kotlin.Int
    {
        return array[index]
    }

    /**
     * Store [value] at position [index]
     * @param index the index to set
     * @param value to store at [index]
     */
    operator fun set(index: kotlin.Int, value: Int)
    {
        array.set(index,value)
    }

}