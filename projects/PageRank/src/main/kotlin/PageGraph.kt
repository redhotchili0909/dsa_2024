/**
 * Represents a Graph with vertices and edges.
 *
 * @param VertexType The type of the vertices in the graph.
 */
interface Graph<VertexType> {
    /**
     * Retrieves all the vertices in the graph.
     *
     * @return A set of all vertices in the graph.
     */
    fun getVertices(): Set<VertexType>

    /**
     * Adds an edge between two vertices in the graph.
     *
     * @param from The starting vertex of the edge.
     * @param to The ending vertex of the edge.
     * @param cost The cost or weight of the edge. Default is 1.0.
     */
    fun addEdge(from: VertexType, to: VertexType, cost: Double = 1.0)

    /**
     * Retrieves the edges originating from a given vertex.
     *
     * @param from The vertex from which the edges originate.
     * @return A map of connected vertices and the edge costs.
     */
    fun getEdges(from: VertexType): Map<VertexType, Double>

    /**
     * Returns the total number of edges in the graph.
     *
     * @return The count of all edges in the graph.
     */
    fun getEdgesCount(): Int

    /**
     * Removes all edges and vertices from the graph, clearing it completely.
     */
    fun clear()
}

class PageGraph<VertexType> : Graph<VertexType> {
    private val vertices: MutableSet<VertexType> = mutableSetOf()
    private val edges: MutableMap<VertexType, MutableMap<VertexType, Double>> = mutableMapOf()

    override fun getVertices(): Set<VertexType> = vertices.toSet()

    override fun addEdge(from: VertexType, to: VertexType, cost: Double) {
        if (from != to) {  // This ensures no self-loops
            if (!vertices.contains(from)) vertices.add(from)
            if (!vertices.contains(to)) vertices.add(to)

            edges.getOrPut(from) { mutableMapOf() }[to] = cost
        }
    }

    override fun getEdges(from: VertexType): Map<VertexType, Double> =
        edges[from] ?: emptyMap()

    override fun getEdgesCount(): Int =
        edges.values.sumOf { it.size }

    override fun clear() {
        vertices.clear()
        edges.clear()
    }
}