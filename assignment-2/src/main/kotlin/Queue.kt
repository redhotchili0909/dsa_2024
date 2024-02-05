interface Queue<T> {
    /**
     * Add [data] to the end of the queue.
     */
    fun enqueue(data: T)
    /**
     * Remove the element at the front of the queue.  If the queue is empty, it remains unchanged.
     * @return the value at the front of the queue or nil if none exists
     */
    fun dequeue(): T?
    /**
     * @return the value at the front of the queue or nil if none exists
     */
    fun peek(): T?
    /**
     * @return true if the queue is empty and false otherwise
     */
    fun isEmpty(): Boolean
}

class MyQueue<T>: Queue<T> {
    private var linkedList: DoublyLinkedList<T> = DoublyLinkedList()
    override fun enqueue(data: T) {
        linkedList.pushFront(data)
    }
    override fun dequeue(): T? {
        return linkedList.popFront()
    }
    override fun peek(): T? {
        return linkedList. peekFront()
    }
    override fun isEmpty(): Boolean {
        return linkedList.isEmpty()
    }
}
