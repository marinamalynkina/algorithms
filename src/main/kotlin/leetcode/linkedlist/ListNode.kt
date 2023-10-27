package leetcode.linkedlist

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
fun getLinkedList(array: IntArray): ListNode? {
    if (array.isEmpty()) return null
    val head = ListNode(array[0])
    var prev: ListNode = head
    if (array.size > 1) {
        for (i in 1..array.lastIndex) {
            prev.next = ListNode(array[i])
            prev = prev.next!!
        }
    }
    return head
}

fun ListNode.println() {
    println(`val`)
    next?.println()
}