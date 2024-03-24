import java.util.concurrent.ConcurrentHashMap
import kotlin.system.measureTimeMillis

/**
 * A class that represents the PageRank algorithm for a given graph.
 *
 * @param VertexType The type of the vertices in the graph.
 * @param graph The graph for which PageRank is to be computed.
 * @param dampingFactor The damping factor used in the PageRank algorithm, default set to 0.85.
 * @param iterations The number of iterations to perform in the PageRank computation.
 */
class PageRank<VertexType>(
    private val graph: PageGraph<VertexType>,
    private val dampingFactor: Double = 0.85,
    private val iterations: Int = 50,
) {
    private val ranks = ConcurrentHashMap<VertexType, Double>()

    init {
        initializeRanks()
    }

    /**
     * Initializes the rank of each vertex in the graph to 1/N, where N is the total number of vertices.
     */
    private fun initializeRanks() {
        val initialRank = 1.0 / graph.getVertices().size
        graph.getVertices().forEach { vertex ->
            ranks[vertex] = initialRank
        }
    }

    /**
     * Computes the PageRank for each vertex in the graph over a specified number of iterations.
     *
     * @return A Pair containing the final ranks of each vertex and the total computation time in milliseconds.
     */
    fun compute(): Pair<Map<VertexType, Double>, Long> {
        val totalVertices = graph.getVertices().size

        val computationTimeMillis = measureTimeMillis {
            for (i in 0 until iterations) {
                val newRanks = ConcurrentHashMap<VertexType, Double>()

                graph.getVertices().parallelStream().forEach { vertex ->
                    var sum = 0.0

                    graph.getVertices().forEach { source ->
                        val edges = graph.getEdges(source)
                        if (vertex in edges) {
                            val sourceRank = ranks[source] ?: 0.0
                            val outDegree = edges.size
                            sum += sourceRank / outDegree
                        }
                    }

                    val sinkRank = ranks.filterKeys { it !in graph.getEdges(it).keys }.values.sum() / totalVertices
                    val newRank = (1 - dampingFactor) / totalVertices + dampingFactor * (sum + sinkRank)
                    newRanks[vertex] = newRank
                }

                ranks.putAll(newRanks)
            }
        }

        return Pair(ranks.toMap(), computationTimeMillis)
    }

    /**
     * Retrieves the rank of a specified vertex.
     *
     * @param vertex The vertex whose rank is to be retrieved.
     * @return The rank of the specified vertex, or null if the vertex is not in the graph.
     */
    fun getRank(vertex: VertexType): Double? = ranks[vertex]
}
