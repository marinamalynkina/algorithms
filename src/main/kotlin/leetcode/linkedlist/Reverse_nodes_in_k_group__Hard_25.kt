package leetcode.linkedlist

fun main(args: Array<String>) {
    Reverse_Nodes_in_K_Group__Hard_25().apply {
//        reverseKGroup(getLinkedList(intArrayOf(1,2,3,4,5)), 2)?.println()
//        println("====")
        reverseKGroup(getLinkedList(intArrayOf(1,2,3,4,5)), 3)?.println()
    }
}

class Reverse_Nodes_in_K_Group__Hard_25 {

    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (k == 1 || head?.next == null) return head
        var i = 1
        val newHead = ListNode(-1)
        var prev:ListNode? = newHead
        prev?.next = head
        var enterIntoGroup = prev?.next
        var hasGroups = true
        while (enterIntoGroup != null && hasGroups) {
            reverseKElements(enterIntoGroup, k)?.let { pair ->
                val endOfGroup: ListNode? = pair.first
                val next: ListNode? = pair.second

                prev?.next = endOfGroup
                prev = enterIntoGroup
                enterIntoGroup = next
                i += k
            } ?: run {
                prev?.next = enterIntoGroup
                hasGroups = false
            }
        }
        return newHead.next
    }

    fun reverseKElements(head: ListNode?, k: Int): Pair<ListNode?, ListNode?>? {
        if (!hasKElements(head, k)) return null
        var i = 0
        var current = head
        var prev:ListNode? = null
        while (i < k && current != null) {
            val next = current.next
            current.next = prev
            prev = current
            current = next
            i++
        }
        return if (i < k) return null else prev to current
    }

    fun hasKElements(head: ListNode?, k: Int): Boolean {
        var current = head
        var i = 0
        while (i < k && current != null) {
            current = current.next
            i++
        }
        return i == k
    }
}