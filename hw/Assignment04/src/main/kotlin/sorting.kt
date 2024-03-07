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