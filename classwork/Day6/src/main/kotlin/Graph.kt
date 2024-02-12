class Graph<VertexType> {
    class Node<T>(var data: T, var prev: Node<T>? = null, var next: Node<T>? = null)
    private var vertices: MutableSet<VertexType> = mutableSetOf()
    private var edges: MutableMap<VertexType, MutableSet<VertexType>> = mutableMapOf()
    private var neighbors: MutableSet<VertexType> = mutableSetOf()
    private var priorityStack: MyStack<VertexType> = MyStack()
    private var priorityQueue: MyQueue<VertexType> = MyQueue()


    /**
     * Add the vertex [v] to the graph
     * @param v the vertex to add
     * @return true if the vertex is successfully added, false if the vertex
     *   was already in the graph
     */
    fun addVertex(v: VertexType): Boolean {
        if (vertices.contains(v)) {
            return false
        }
        vertices.add(v)
        return true
    }

    /**
     * Add an edge between vertex [from] connecting to vertex [to]
     * @param from the vertex for the edge to originate from
     * @param to the vertex to connect the edge to
     * @return true if the edge is successfully added and false if the edge
     *     can't be added or already exists
     */
    fun addEdge(from: VertexType, to: VertexType): Boolean {
        if (!vertices.contains(from) || !vertices.contains(to)) {
            return false
        }
        edges[from]?.also { currentAdjacent ->
            if (currentAdjacent.contains(to)) {
                return false
            }
            currentAdjacent.add(to)
        } ?: run {
            edges[from] = mutableSetOf(to)
        }
        return true
    }

    fun addPriorityDFS(node: VertexType){
        priorityStack.push(node)
    }

    fun BDS(start: VertexType, target: VertexType): Boolean{
        priorityQueue.enqueue(start)
        neighbors.add(start)
        while(!priorityQueue.isEmpty()){
            var current = priorityQueue.dequeue()
            if(current == target){
                return true
            }
            for(m in edges[current]!!){
                if(m !in neighbors ){
                    priorityQueue.enqueue(m)
                    neighbors.add(m)
                }
            }
        }
        return false
    }

    fun DFS(start: VertexType, target: VertexType): Boolean{
        priorityStack.push(start)
        neighbors.add(start)
        while(!priorityQueue.isEmpty()){
            var current = priorityQueue.dequeue()
            if(current == target){
                return true
            }
            for(m in edges[current]!!){
                if(m !in neighbors ){
                    priorityQueue.enqueue(m)
                    neighbors.add(m)
                }
            }
        }
        return false
    }

    /**
     * Clear all vertices and edges
     */
    fun clear() {
        vertices = mutableSetOf()
        edges = mutableMapOf()
    }
}
