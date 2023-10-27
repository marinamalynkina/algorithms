package leetcode.design

/**
 * Discards the least recently used items first.
 * This algorithm requires keeping track of what was used when, which is expensive
 * if one wants to make sure the algorithm always discards the least recently used item.
 *
 *
 * 1. Store a key-value pair
 * 2. Update a key-value pair
 * 3. Given a key, determine if it exists in the data structure:
 * - if it does -> return the value
 * - if it doesn't -> return -1
 * 4. When the new key-value pair is added, create a new linked list node and put it at the back
 * 5. When a new key-value pair is added and the of data structure exceeds `capacity`, remove the head of linked list.
 */
class LRU(capacity: Int) {

    data class ListNode(
        var key: Int = -1,
        var value: Int = -1
    ) {
        var prev: ListNode? = null
        var next: ListNode? = null
    }

    val cache = HashMap<Int, ListNode>()
    // head and tail are not part of nodes with values
    var head = ListNode()
    var tail = ListNode()
    val capacity: Int

    init {
        this.capacity = capacity
        head.next = tail
        tail.prev = head
    }

    // basic operations
    private fun addInTail(node: ListNode) {
        val lastNode = tail.prev!!
        lastNode.next = node
        node.prev = lastNode
        node.next = tail
        tail.prev = node
    }

    private fun removeNode(node: ListNode) {
        node.prev!!.next = node.next
        node.next!!.prev = node.prev
    }

    fun get(key: Int): Int {
        if (!cache.containsKey(key)) return -1

        return cache.get(key)!!.apply {
            removeNode(this)
            addInTail(this)
        }.value
    }

    fun put(key: Int, value: Int) {
        // if key already in cache
        if (cache.containsKey(key)) {
            // remove from cache
            removeNode(cache.get(key)!!)
        }

        // if key is new or was just removed from list
        // - add in tail and in cache
        // - check on capacity
        val newNode = ListNode(key, value)
        cache.put(key, newNode)
        addInTail(newNode)

        if (cache.size > capacity) {
            // remove head
            head.next!!.run {
                removeNode(this)
                cache.remove(this.key)
            }
        }
    }
}