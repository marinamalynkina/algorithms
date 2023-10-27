package leetcode.linkedlist


fun main(args: Array<String>) {
    Reverse_Linked_List_Easy_206().apply {
        reverseList(getLinkedList(intArrayOf(1,2,3)))?.println()
        fn(getLinkedList(intArrayOf(1,2,3)))?.println()
    }
}

class Reverse_Linked_List_Easy_206 {
    fun reverseList(head: ListNode?): ListNode? {
        if (head == null) return head
        var prev: ListNode? = null
        var newHead = head
        var next = head.next
        //  prev -> newHead -> next
        //  null ->  1      ->  2      -> 3
        //          prev    -> newHead -> next
        while (next != null) {
            newHead!!.next = prev
            prev = newHead
            newHead = next
            next = next.next
        }
        newHead!!.next = prev

        return newHead
    }

    fun fn(head: ListNode?): ListNode? {
        var curr = head
        var prev: ListNode? = null
        while (curr != null) {
            val nextNode = curr.next
            curr.next = prev
            prev = curr
            curr = nextNode
        }
        return prev
    }
}