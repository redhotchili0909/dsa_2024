import kotlin.random.Random
import kotlin.system.measureNanoTime
import java.io.File

fun main() {
    val listSizes = listOf(1000, 10000, 50000, 100000)
    val numberOfRuns = 5
    val results = mutableListOf<String>()

    // Adding CSV headers
    results.add("Size,Heap Sort Average,Insertion Sort Average,Quick Sort Average,Selection Sort Average")

    listSizes.forEach { size ->
        val randomList = List(size) { Random.nextInt(0, size) }
        val resultLine = StringBuilder("$size")

        // Heap Sort
        val heapSortTimes = mutableListOf<Long>()
        repeat(numberOfRuns) {
            heapSortTimes.add(measureNanoTime { heapSort(randomList.toIntArray()) })
        }
        val heapSortAverage = heapSortTimes.average() / 1000000
        resultLine.append(",$heapSortAverage")

        // Insertion Sort
        val insertionSortTimes = mutableListOf<Long>()
        repeat(numberOfRuns) {
            insertionSortTimes.add(measureNanoTime { insertionSort(randomList.toIntArray()) })
        }
        val insertionSortAverage = insertionSortTimes.average() / 1000000
        resultLine.append(",$insertionSortAverage")

        // Quick Sort
        val quickSortTimes = mutableListOf<Long>()
        repeat(numberOfRuns) {
            quickSortTimes.add(measureNanoTime { quickSort(randomList.toIntArray(), 0, randomList.size - 1) })
        }
        val quickSortAverage = quickSortTimes.average() / 1000000
        resultLine.append(",$quickSortAverage")

        // Selection Sort
        val selectionSortTimes = mutableListOf<Long>()
        repeat(numberOfRuns) {
            selectionSortTimes.add(measureNanoTime { selectionSort(randomList.toIntArray()) })
        }
        val selectionSortAverage = selectionSortTimes.average() / 1000000
        resultLine.append(",$selectionSortAverage")

        // Adding the result for this size to the list
        results.add(resultLine.toString())
    }

    // Writing to CSV file
    File("./src/main/kotlin/sortResults.csv").bufferedWriter().use { out ->
        results.forEach { line ->
            out.write(line + "\n")
        }
    }
}
