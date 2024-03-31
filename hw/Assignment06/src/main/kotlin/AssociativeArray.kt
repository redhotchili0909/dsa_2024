class Node<K, V>(val key: K, var value: V, var next: Node<K, V>? = null)

/**
 * Represents a mapping of keys to values.
 * @param K the type of the keys
 * @param V the type of the values
 */
class AssociativeArray<K, V>(private var max: Int = 32) {
    private var table: Array<Node<K, V>?> = arrayOfNulls(max)
    private var size = 0
    /**
     * Insert the mapping from the key, [k], to the value, [v].
     * If the key already maps to a value, replace the mapping.
     */
    operator fun set(k: K, v: V) {
        val index = hash(k)
        var head = table[index]
        while (head != null) {
            if (head.key == k) {
                head.value = v
                return
            }
            head = head.next
        }
        size++
        head = table[index]
        val newNode = Node(k, v, head)
        table[index] = newNode

        if ((1.0 * size) / max >= 0.75) {
            rehash()
        }
    }

    /**
     * @return true if [k] is a key in the associative array
     */
    operator fun contains(k: K): Boolean {
        val index = hash(k)
        var head = table[index]
        while (head != null) {
            if (head.key == k) {
                return true
            }
            head = head.next
        }
        return false
    }

    /**
     * @return the value associated with the key [k] or null if it doesn't exist
     */
    operator fun get(k: K): V? {
        val index = hash(k)
        var head = table[index]
        while (head != null) {
            if (head.key == k) {
                return head.value
            }
            head = head.next
        }
        return null
    }

    /**
     * Remove the key, [k], from the associative array
     * @param k the key to remove
     * @return true if the item was successfully removed and false if the element was not found
     */
    fun remove(k: K): Boolean {
        val index = hash(k)
        var head = table[index]
        var prev: Node<K, V>? = null
        while (head != null) {
            if (head.key == k) {
                if (prev != null) {
                    prev.next = head.next
                } else {
                    table[index] = head.next
                }
                size--
                return true
            }
            prev = head
            head = head.next
        }
        return false
    }

    /**
     * @return the number of elements stored in the hash table
     */
    fun size(): Int {
        return size
    }

    /**
     * @return the full list of key value pairs for the associative array
     */
    fun keyValuePairs(): List<Pair<K, V>> {
        val pairs = mutableListOf<Pair<K, V>>()
        for (head in table) {
            var current = head
            while (current != null) {
                pairs.add(Pair(current.key, current.value))
                current = current.next
            }
        }
        return pairs
    }

    /**
     * Computes the hash code for a given key and normalizes it to fit within the current table size.
     *
     * @param key The key for which to compute the hash.
     * @return A hash code that is an index in the hash table.
     */
    private fun hash(key: K): Int {
        return (key.hashCode() % max).let { if (it >= 0) it else it + max }
    }

    /**
     * Rehashes the current hash table to expand the size.
     * Creates a new table with double the size and re-inserts existing entries into the new table.
     */
    private fun rehash() {
        val oldTable = table
        max *= 2
        table = arrayOfNulls(max)
        size = 0
        for (head in oldTable) {
            var current = head
            while (current != null) {
                set(current.key, current.value)
                current = current.next
            }
        }
    }
}
