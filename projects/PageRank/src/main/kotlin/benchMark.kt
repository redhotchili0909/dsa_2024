import java.io.File
import kotlin.random.Random
import kotlin.system.measureTimeMillis

/**
 * Generates a random graph with the specified number of nodes and edges.
 *
 * @param numNodes The number of nodes in the graph.
 * @param numEdges The number of edges to be added randomly between the nodes.
 * @return A PageGraph object representing the randomly generated graph.
 */
fun generateRandomGraph(numNodes: Int, numEdges: Int): PageGraph<Int> {
    val graph = PageGraph<Int>()
    repeat(numEdges) {
        val from = Random.nextInt(numNodes)
        val to = Random.nextInt(numNodes)
        if (from != to) {
            graph.addEdge(from, to)
        }
    }
    return graph
}

/**
 * Benchmarks the PageRank algorithm by generating random graphs of increasing size and computes their PageRanks.
 * The results are saved to a CSV file, including the number of nodes, edges, top 3 PageRank values, and computation time.
 *
 * @param nodeSteps The increment step for the number of nodes in each iteration of the benchmark.
 * @param maxNodes The maximum number of nodes to be tested in the benchmark.
 * @param edgesPerNode The factor to determine the number of edges based on the number of nodes.
 * @param outputFilePath The path to the CSV file where the benchmark results will be saved.
 */
fun benchmark(nodeSteps: Int, maxNodes: Int, edgesPerNode: Int, outputFilePath: String) {
    File(outputFilePath).bufferedWriter().use { writer ->
        writer.write("Nodes,Edges,Top 3 Ranks,Time(ms)\n")

        var numNodes = nodeSteps
        while (numNodes <= maxNodes) {
            val numEdges = numNodes * edgesPerNode
            val graph = generateRandomGraph(numNodes, numEdges)

            var topRanks: String
            val timeMillis = measureTimeMillis {
                val pageRankCalculator = PageRank(graph)
                val (ranks, _) = pageRankCalculator.compute()
                val sortedRanks = ranks.values.sortedDescending().take(3)
                topRanks = sortedRanks.joinToString(", ")
            }

            println("Nodes: $numNodes, Edges: $numEdges, Top 3 Ranks: $topRanks, Time: $timeMillis ms")
            writer.write("$numNodes,$numEdges,\"$topRanks\",$timeMillis\n")

            numNodes += nodeSteps
        }
    }
    println("Results saved to $outputFilePath")
}
