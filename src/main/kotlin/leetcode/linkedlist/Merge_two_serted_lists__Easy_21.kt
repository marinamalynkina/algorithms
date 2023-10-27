package leetcode.linkedlist

fun main(args: Array<String>) {
    Merge_two_serted_lists_Easy_21().apply {
        mergeTwoLists(
            getLinkedList(intArrayOf(1,2,4)),
            getLinkedList(intArrayOf(1,3,4))
        )
    }
}


class Merge_two_serted_lists_Easy_21 {

    fun mergeTwoListsImproved(list1: ListNode?, list2: ListNode?): ListNode? {
        var cur1: ListNode? = list1
        var cur2: ListNode? = list2

        val head: ListNode = ListNode(Int.MIN_VALUE) // assiging empty Node applow to skip checks on null
        var tail = head
        while (cur1 != null && cur2 != null) {
            if (cur1.`val` <= cur2.`val`) {
                tail.next = cur1
                cur1 = cur1.next
            } else {
                tail.next = cur2
                cur2 = cur2.next
            }
            tail = tail.next!!
        }

        // attach rest of list in case if one them is null.
        // it allow to skip iteration by this list, and just join tail of result list with head of rest list
        tail.next = if (cur1 != null) cur1 else cur2

        // return head.next, instaed of head, because we assign empty node to head.
        return head.next
    }

    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var cur1: ListNode? = list1
        var cur2: ListNode? = list2
        var prev: ListNode? = null
        var head: ListNode? = null
        while (cur1 != null || cur2 != null) {
            if ((cur1 != null && cur1.`val` <= (cur2?.`val` ?: Int.MAX_VALUE)) || cur2 == null) {
                if (prev == null) {
                    prev = cur1
                    head = prev
                } else {
                    prev.next = cur1
                    prev = prev.next
                }
                cur1 = cur1!!.next
            } else {
                if (prev == null) {
                    prev = cur2
                    head = prev
                } else {
                    prev.next = cur2
                    prev = prev.next
                }
                cur2 = cur2.next
            }

        }
        return head
    }
}