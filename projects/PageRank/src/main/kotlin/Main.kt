fun main() {
    /*
    Change when(___) to either "benchmark" or "pagerank" to run to options:
    - benchmark: Benchmarks the PageRank algorithm by generating random graphs of increasing size and computes their PageRanks.
    - pagerank: Run PageRank algorithm on a defined dataset. Make sure to define the correct file path to the dataset.
     */
    when ("pagerank") {
        "benchmark" -> {
            val nodeSteps = 1000
            val maxNodes = 10000
            val edgesPerNode = 5
            val outputFilePath = "src/results/pagerank_computation_results.csv"

            benchmark(nodeSteps, maxNodes, edgesPerNode, outputFilePath)
        }
        "pagerank" -> {
            /*
            Define Input File Path
             */
            val filePath = "src/assets/datasets/email-Eu-core.txt"
            val graph = makeGraph(filePath)
            /*
            Define Output File Path
             */
            val outputFilePath = "src/results/pagerank_results_email-Eu-core-v2.csv"

            val pageRankCalculator = PageRank(graph)
            val (ranks, computationTime) = pageRankCalculator.compute()
            exportGraph(graph, "src/results/visualizations/eu-v2-graph.gexf")
            pageRankToCSV(ranks, outputFilePath, 10,graph.getVertices().size, computationTime)

            println("PageRank computation time: $computationTime ms")
            println("PageRank results saved to $outputFilePath")
        }
    }
}
