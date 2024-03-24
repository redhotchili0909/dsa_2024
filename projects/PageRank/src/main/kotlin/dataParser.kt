import java.io.File

/**
 * Constructs a PageGraph from a dataset file.*
 * @param filePath The path to the dataset.
 * @return A PageGraph object representing the defined dataset graph.
 */
fun makeGraph(filePath: String): PageGraph<Int> {
    val graph = PageGraph<Int>()

    File(filePath).useLines { lines ->
        lines.forEach { line ->
            val parts = line.split("\\s+".toRegex()) // Splitting by whitespace
            if (parts.size == 2) {
                try {
                    val from = parts[0].toInt()
                    val to = parts[1].toInt()
                    graph.addEdge(from, to)
                } catch (e: NumberFormatException) {
                    println("Skipping invalid line: $line")
                }
            }
        }
    }

    return graph
}

/**
 * Writes the total number of nodes, the computation time, and
 * the top N PageRank values to the specified CSV file.
 *
 * @param ranks The map of nodes to their PageRank values.
 * @param outputFilePath The file path where the CSV will be saved.
 * @param topCount The number of top ranks to include in the CSV.
 * @param totalNodes The total number of nodes in the graph.
 * @param computationTimeMillis The computation time of the PageRank algorithm in milliseconds.
 */
fun pageRankToCSV(ranks: Map<Int, Double>, outputFilePath: String, topCount: Int, totalNodes: Int, computationTimeMillis: Long) {
    File(outputFilePath).bufferedWriter().use { writer ->
        writer.write("Total Nodes,$totalNodes\n")
        writer.write("Computation Time (ms),$computationTimeMillis\n")
        writer.write("Node,PageRank\n")
        ranks.entries.sortedByDescending { it.value }.take(topCount).forEach { (node, rank) ->
            writer.write("$node,$rank\n")
        }
    }
}

/**
 * Exports the given graph to a GEXF format file for visualization.
 *
 * The GEXF file can be used with graph visualization tools like Gephi
 * to generate a visual representation of the graph.
 *
 * @param graph The graph to be exported, which must be an instance of PageGraph<Int>.
 * @param filename The name of the file to which the graph will be exported.
 *                  This file will be created or overwritten in the current directory.
 */
fun exportGraph(graph: PageGraph<Int>, filename: String) {
    File(filename).bufferedWriter().use { writer ->
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
        writer.write("<gexf xmlns=\"http://www.gexf.net/1.2draft\" version=\"1.2\">\n")
        writer.write("  <graph mode=\"static\" defaultedgetype=\"directed\">\n")

        writer.write("    <nodes>\n")
        graph.getVertices().forEach { vertex ->
            writer.write("      <node id=\"$vertex\" label=\"$vertex\" />\n")
        }
        writer.write("    </nodes>\n")

        writer.write("    <edges>\n")
        var edgeId = 0
        graph.getVertices().forEach { vertex ->
            graph.getEdges(vertex).forEach { (to, _) ->
                writer.write("      <edge id=\"${edgeId++}\" source=\"$vertex\" target=\"$to\" />\n")
            }
        }
        writer.write("    </edges>\n")

        writer.write("  </graph>\n")
        writer.write("</gexf>\n")
    }
}
