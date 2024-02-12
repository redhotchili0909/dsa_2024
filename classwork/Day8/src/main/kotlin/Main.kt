/**
 * Demonstrates finding the shortest path in a graph representing city connections.
 *
 * This function creates a graph with predefined edges representing distances between cities
 * and  a heuristic function, then computes and prints the shortest path from a start city to a destination city using
 * the A* algorithm.
 */
fun main() {
    val graph = MyGraph<String>().apply {
        addEdge("NY", "LA", 2800.0)
        addEdge("NY", "CH", 800.0)
        addEdge("CH", "LA", 2000.0)
        addEdge("CH", "HT", 1000.0)
        addEdge("LA", "HT", 1500.0)
        addEdge("NY", "MI", 1300.0)
        addEdge("MI", "HT", 1187.0)
        addEdge("NY", "DC", 227.0)
        addEdge("DC", "MI", 1052.0)
        addEdge("SF", "LA", 383.0)
        addEdge("LA", "DL", 1435.0)
        addEdge("DL", "HT", 239.0)
        addEdge("DL", "CH", 920.0)
        addEdge("SF", "DL", 1500.0)
    }

    val startCity = "NY"
    val destinationCity = "DL"
    val h = mutableMapOf("NY" to 2000.0, "LA" to 1200.0, "CH" to 509.0, "HT" to 410.0, "MI" to 1038.0, "DC" to 1500.0, "SF" to 601.0, "DL" to 1100.0)
    val shortestPath = aStar(graph, h, startCity, destinationCity)
    println("Shortest path from $startCity to $destinationCity: $shortestPath")
}

/**
 * Finds the shortest path from a start vertex to a destination vertex in a graph.
 *
 * Utilizes Dijkstra's algorithm to compute the shortest path in a graph represented by distances
 * between vertices. This function is generic and can work with any vertex type that is part of the graph.
 *
 * @param graph The graph within which to find the shortest path.
 * @param start The starting vertex for the path.
 * @param destination The destination vertex for the path.
 * @return A list representing the shortest path from start to destination, or null if no path exists.
 */
fun <VertexType> aStar(graph: MyGraph<VertexType>, h: Map<VertexType, Double>, start: VertexType, destination: VertexType): List<VertexType>? {
    if (!graph.getVertices().contains(start) || !graph.getVertices().contains(destination)) {
        return null
    }

    val distance = mutableMapOf<VertexType, Double>().withDefault { Double.POSITIVE_INFINITY }
    val previous = mutableMapOf<VertexType, VertexType?>()
    val visited = mutableSetOf<VertexType>()
    val priorityQueue = MyMinPriorityQueue<VertexType>()

    distance[start] = 0.0
    priorityQueue.addWithPriority(start, h.getValue(start))

    while (!priorityQueue.isEmpty()) {
        val current = priorityQueue.next() ?: break
        if (current == destination) {
            return reconstructPath(previous, destination)
        }

        if (visited.contains(current)) continue
        visited.add(current)

        val neighbors = graph.getEdges(current)
        for ((neighbor, weight) in neighbors) {
            if (visited.contains(neighbor)) continue

            val newDist = distance.getValue(current) + weight
            if (newDist < distance.getValue(neighbor)) {
                distance[neighbor] = newDist
                if (priorityQueue.contains(neighbor))
                {
                    priorityQueue.adjustPriority(neighbor, newDist + h.getValue(neighbor))
                }
                else
                {
                    priorityQueue.addWithPriority(neighbor, newDist + h.getValue((neighbor)))
                }
                previous[neighbor] = current
            }
        }
    }
    return null
}

/**
 * Reconstructs the shortest path from a destination vertex backwards to the start vertex.
 *
 * This function backtracks from the destination vertex to the start vertex using a map that
 * records each vertex's predecessor in the shortest path.
 *
 * @param previous A map from each vertex to its predecessor in the shortest path.
 * @param destination The destination vertex from which to backtrack to the start.
 * @return A list representing the vertices in the shortest path from start to destination, in order,
 *         or null if the path cannot be reconstructed.
 */
fun <VertexType> reconstructPath(previous: Map<VertexType, VertexType?>, destination: VertexType): List<VertexType>? {
    var current: VertexType? = destination
    val path = mutableListOf<VertexType>()
    while (current != null) {
        path.add(current)
        // Safely access the predecessor without forcing a non-null value
        current = previous[current] // Remove the !! operator
        if (path.size > 1 && current == path.last()) {
            // Prevents an infinite loop if for any reason the path loops back on itself
            return null
        }
    }
    // The path should at least have the start node; otherwise, it's invalid
    if (path.size < 2) return null
    return path.asReversed()
}