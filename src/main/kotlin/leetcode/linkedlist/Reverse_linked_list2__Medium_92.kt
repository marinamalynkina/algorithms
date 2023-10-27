package leetcode.linkedlist

fun main(args: Array<String>) {
    Reverse_Linked_List2_Medium_92().apply {
//        reverseBetween(getLinkedList(intArrayOf(1,2,3,4,5)), 2, 4)?.println()
//        println("=====")
//        reverseBetween(getLinkedList(intArrayOf(5)), 1, 1)?.println()
//        println("=====")
//        reverseBetween(getLinkedList(intArrayOf(3,5,6)), 1, 2)?.println()
        reverseBetween(getLinkedList(intArrayOf(3,5)), 1, 2)?.println()
    }
}

class Reverse_Linked_List2_Medium_92 {
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        if (head?.next == null) return head
        var current: ListNode? = head
        var newHead: ListNode? = null
        // var reverseHead: ListNode? = null // for final list
        var reverceTail: ListNode? = null // for final list
        var prev: ListNode? = null
        var i = 1
        while (current != null && i <= right) {
            if (i == left) {
                reverceTail = current
                newHead = prev
            }

            val next = current.next
            if (i >= left) {
                //reverse
                current.next = prev
            }
            prev = current
            current = next
            i++
        }

        newHead?.next = prev
        reverceTail?.next = current

        return if (left > 1) head else prev
    }
}