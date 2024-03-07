/**
 * Sorts an array using heap sort algorithm.
 *
 * @param arr The array of integers to be sorted.
 * @return A new sorted array of integers.
 */
fun heapSort(arr: IntArray): IntArray {
    val minHeap = MinHeap<Int>()

    arr.forEach { number ->
        minHeap.insert(number, number.toDouble())
    }

    val sortedArray = IntArray(arr.size)

    var index = 0
    while (!minHeap.isEmpty()) {
        sortedArray[index++] = minHeap.getMin() ?: break
    }

    return sortedArray
}


/**
 * Sorts an array in place using insertion sort algorithm.
 *
 * @param arr The array of integers to be sorted.
 */
fun insertionSort(arr: IntArray) {
    for (i in 1 until arr.size) {
        val key = arr[i]
        var j = i - 1

        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j -= 1
        }
        arr[j + 1] = key
    }
}


/**
 * Sorts an array using quick sort algorithm.
 *
 * @param arr The array of integers to be sorted.
 * @param min The starting index of the segment of the array to be sorted.
 * @param max The ending index of the segment of the array to be sorted.
 */
fun quickSort(arr: IntArray, min: Int, max: Int) {
    fun part(arr: IntArray, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = (low - 1)
        for (j in low until high) {
            if (arr[j] < pivot) {
                i++
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
        }

        val temp = arr[i + 1]
        arr[i + 1] = arr[high]
        arr[high] = temp

        return i + 1
    }
    if (min < max) {
        val partition = part(arr, min, max)
        quickSort(arr, min, partition - 1)
        quickSort(arr, partition + 1, max)
    }
}

/**
 * Sorts an array in place using selection sort algorithm.
 *
 * @param arr The array of integers to be sorted.
 */
fun selectionSort(arr: IntArray) {
    for (i in arr.indices) {
        var min = i
        for (j in i + 1 until arr.size) {
            if (arr[j] < arr[min]) {
                min = j
            }
        }
        val temp = arr[min]
        arr[min] = arr[i]
        arr[i] = temp
    }
}
