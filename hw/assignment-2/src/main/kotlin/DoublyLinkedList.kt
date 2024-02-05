class DoublyLinkedList<T> {
    class Node<T>(var data: T, var prev: Node<T>? = null, var next: Node<T>? = null)

    var head: Node<T>? = null
    var tail: Node<T>? = null

    /**
     * Adds the element [data] to the front of the linked list.
     */
    fun pushFront(data: T) {
        val newNode = Node(data)
        newNode.next = head
        head?.prev = newNode
        head = newNode
        if (tail == null) {
            tail = head
        }
    }
    /**
     * Adds the element [data] to the back of the linked list.
     */
    fun pushBack(data: T) {
        val newNode = Node(data)
        newNode.prev = tail
        tail?.next = newNode
        tail = newNode
        if (head == null) {
            head = tail
        }
    }
    /**
     * Removes an element from the front of the list. If the list is empty, it is unchanged.
     * @return the value at the front of the list or nil if none exists
     */
    fun popFront(): T? {
        if (isEmpty()) return null
        val data = head?.data
        head = head?.next
        head?.prev = null
        if (head == null) {
            tail = null
        }
        return data
    }
    /**
     * Removes an element from the back of the list. If the list is empty, it is unchanged.
     * @return the value at the back of the list or nil if none exists
     */
    fun popBack(): T? {
        if (isEmpty()) return null
        val data = tail?.data
        tail = tail?.prev
        tail?.next = null
        if (tail == null) {
            head = null
        }
        return data
    }
    /**
     * @return the value at the front of the list or nil if none exists
     */    fun peekFront(): T? {
        return head?.data
    }
    /**
     * @return the value at the back of the list or nil if none exists
     */
    fun peekBack(): T? {
        return tail?.data
    }
    /**
     * @return true if the list is empty and false otherwise
     */
    fun isEmpty(): Boolean {
        return head == null
    }
}