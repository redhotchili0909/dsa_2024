enum class Color {
    RED, BLACK
}

/**
 * Represents a node in the Red-Black Tree.
 *
 * @param data the value stored in the node.
 * @param color the color of the node, either RED or BLACK.
 * @param left reference to the left child node.
 * @param right reference to the right child node.
 * @param parent reference to the parent node.
 */
class Node(var data: Int, var color: Color, var left: Node? = null, var right: Node? = null, var parent: Node? = null)

/**
 * Implements the operations and maintenance of a Red-Black Tree.
 */
class RBTree {
    private val nil: Node = Node(0, Color.BLACK)
    private var root: Node = nil

    /**
     * Inserts a new node with the specified data into the Red-Black Tree.
     *
     * @param data the integer to insert into the tree.
     */
    fun insert(data: Int) {
        var node = Node(data, Color.RED)
        var parent: Node? = null
        var current = root

        while (current != nil) {
            parent = current
            current = if (node.data < current.data) {
                current.left!!
            } else {
                current.right!!
            }
        }

        node.parent = parent
        if (parent == null) {
            root = node
        } else if (node.data < parent.data) {
            parent.left = node
        } else {
            parent.right = node
        }

        node.left = nil
        node.right = nil
        fixInsert(node)
    }

    /**
     * Restores the Red-Black Tree properties after an insertion operation.
     *
     * @param node the newly inserted node around which the tree may need fixing.
     */
    private fun fixInsert(node: Node) {
        var current = node
        while (current.parent?.color == Color.RED) {
            if (current.parent == current.parent?.parent?.left) {
                val uncle = current.parent?.parent?.right
                if (uncle?.color == Color.RED) {
                    current.parent?.color = Color.BLACK
                    uncle.color = Color.BLACK
                    current.parent?.parent?.color = Color.RED
                    current = current.parent?.parent!!
                } else {
                    if (current == current.parent?.right) {
                        current = current.parent!!
                        leftRotate(current)
                    }
                    current.parent?.color = Color.BLACK
                    current.parent?.parent?.color = Color.RED
                    rightRotate(current.parent?.parent!!)
                }
            } else {
                val uncle = current.parent?.parent?.left
                if (uncle?.color == Color.RED) {
                    current.parent?.color = Color.BLACK
                    uncle.color = Color.BLACK
                    current.parent?.parent?.color = Color.RED
                    current = current.parent?.parent!!
                } else {
                    if (current == current.parent?.left) {
                        current = current.parent!!
                        rightRotate(current)
                    }
                    current.parent?.color = Color.BLACK
                    current.parent?.parent?.color = Color.RED
                    leftRotate(current.parent?.parent!!)
                }
            }
        }
        root.color = Color.BLACK
    }

    /**
     * Performs a left rotation on the subtree rooted at the given node.
     *
     * @param rotateNode the root of the subtree to be rotated.
     */
    private fun leftRotate(rotateNode: Node) {
        val childNode = rotateNode.right!!
        rotateNode.right = childNode.left
        if (childNode.left != nil) {
            childNode.left!!.parent = rotateNode
        }
        childNode.parent = rotateNode.parent
        if (rotateNode.parent == null) {
            root = childNode
        } else if (rotateNode == rotateNode.parent!!.left) {
            rotateNode.parent!!.left = childNode
        } else {
            rotateNode.parent!!.right = childNode
        }
        childNode.left = rotateNode
        rotateNode.parent = childNode
    }

    /**
     * Performs a right rotation on the subtree rooted at the given node.
     *
     * @param rotateNode the root of the subtree to be rotated.
     */
    private fun rightRotate(rotateNode: Node) {
        val childNode = rotateNode.left!!
        rotateNode.left = childNode.right
        if (childNode.right != nil) {
            childNode.right!!.parent = rotateNode
        }
        childNode.parent = rotateNode.parent
        if (rotateNode.parent == null) {
            root = childNode
        } else if (rotateNode == rotateNode.parent!!.right) {
            rotateNode.parent!!.right = childNode
        } else {
            rotateNode.parent!!.left = childNode
        }
        childNode.right = rotateNode
        rotateNode.parent = childNode
    }

    /**
     * Searches for a node containing the specified data within the tree.
     *
     * @param data the data value to search for.
     * @return the node containing the data, or null if not found.
     */
    fun lookup(data: Int): Node? = lookupNode(root, data)

    private fun lookupNode(node: Node?, data: Int): Node? {
        if (node?.data == data) {
            return node
        }
        else if (node == nil){
            return null
        }
        return if (data < node?.data!!) lookupNode(node.left, data) else lookupNode(node.right, data)
    }

    /**
     * Verifies the Red-Black Tree properties across the entire tree.
     */
    fun checkInvariants() = checkInvariants(root, null)

    private fun checkInvariants(node: Node?, blackCount: Int?, currentCount: Int = 0): Int {
        if (node == nil) return 1

        val count = currentCount + if (node?.color == Color.BLACK) 1 else 0
        val leftBlackCount = checkInvariants(node?.left, blackCount, count)
        val rightBlackCount = checkInvariants(node?.right, blackCount, count)

        if (blackCount != null) {
            check(blackCount == leftBlackCount) { "Violation!" }
        }

        check(leftBlackCount == rightBlackCount) { "Violation!" }
        return leftBlackCount
    }
}
