fun main() {
    val tree = RBTree()

    listOf(10, 15, 5, 20, 3, 25, 7, 17).forEach { value ->
        tree.insert(value)
    }

    try {
        tree.checkInvariants()
        println("No Violation")
    } catch (e: AssertionError) {
        println("Error: ${e.message}")
    }
}
