import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PageGraphTest {

    private lateinit var graph: PageGraph<String>

    @BeforeEach
    fun setUp() {
        // Initialize a new graph before each test
        graph = PageGraph()
    }

    /**
     * Test that a new graph is empty upon initialization.
     */
    @Test
    fun graphEmpty() {
        assertTrue(graph.getVertices().isEmpty())
    }

    /**
     * Test adding edges to the graph and verifying the vertices and edges are added correctly.
     */
    @Test
    fun addTest() {
        graph.addEdge("A", "B", 1.0)
        assertTrue(graph.getVertices().containsAll(listOf("A", "B")))
        assertEquals(1.0, graph.getEdges("A")["B"])
    }

    /**
     * Test retrieving edges from a vertex to ensure correct edges and weights are returned.
     */
    @Test
    fun getEdgeTest() {
        graph.addEdge("A", "B", 1.0)
        graph.addEdge("A", "C", 2.0)
        val edges = graph.getEdges("A")
        assertEquals(2, edges.size)
        assertEquals(1.0, edges["B"])
        assertEquals(2.0, edges["C"])
    }

    /**
     * Test clearing the graph to ensure all vertices and edges are removed.
     */
    @Test
    fun clearTest() {
        graph.addEdge("A", "B", 1.0)
        graph.clear()
        assertTrue(graph.getVertices().isEmpty())
    }
}

class GraphUtilsTest {
    /**
     * Test generating a random graph with a specified number of nodes and edges.
     */
    @Test
    fun generateRandomGraphTest() {
        val numNodes = 10
        val numEdges = 15
        val graph = generateRandomGraph(numNodes, numEdges)

        assertEquals(numNodes, graph.getVertices().size)
        assertTrue(graph.getVertices().all { vertex -> graph.getEdges(vertex).size <= numNodes })
    }
}

class PageRankTest {
    /**
     * Test initializing PageRank to ensure all vertices have equal initial rank.
     */
    @Test
    fun initializePageGraphTest() {
        val graph = PageGraph<Int>()
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)

        val pageRank = PageRank(graph)
        val vertices = graph.getVertices()

        assertTrue(vertices.all { vertex -> pageRank.getRank(vertex) == 1.0 / vertices.size })
    }

    /**
     * Test computing PageRank to ensure it produces valid ranks and captures computation time.
     */
    @Test
    fun pageRankComputeTest() {
        val graph = PageGraph<Int>()
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)
        graph.addEdge(3, 1)

        val pageRank = PageRank(graph)
        val (ranks, computationTime) = pageRank.compute()

        assertFalse(ranks.isEmpty())
        assertTrue(ranks.all { (_, rank) -> rank > 0 })
        assertTrue(computationTime > 0)
        assertNotNull(pageRank.getRank(1))
        assertTrue(pageRank.getRank(1)!! > 0)
    }
}
