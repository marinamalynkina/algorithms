package leetcode.linkedlist

import java.util.LinkedList

fun main(args: Array<String>) {
    Palindrome_Linked_List_Easy_234().apply {
        println(isPalindrome(getLinkedList(intArrayOf(1,2,3,4))))
        println(isPalindrome(getLinkedList(intArrayOf(1,2,2,1))))
        println(isPalindrome(getLinkedList(intArrayOf(1,2))))
        println(isPalindrome(getLinkedList(intArrayOf(1,0,1))))
    }
}

class Palindrome_Linked_List_Easy_234 {

    // T: O(N), S: O(1)
    fun isPalindrome(head: ListNode?): Boolean {
        if (head?.next == null) return true
        // - get to center using slow and fast pointer
        // - revert direction in first half
        // - after middle start moving in different directions and compare
        var slowPointer = head
        var fastPointer = head
        var previous: ListNode? = null

        // this truple condition allows to not add midle element in stack
        // in case if count of element is odd
        while(fastPointer?.next != null) {
            fastPointer = fastPointer.next?.next
            val cur = slowPointer
            slowPointer = slowPointer!!.next
            cur!!.next = previous
            previous = cur

        }

        if (fastPointer != null) {
            slowPointer =  slowPointer!!.next
        }

        while (previous != null && slowPointer != null) {
            if (slowPointer.`val` != previous.`val`) return false
            previous = previous.next
            slowPointer = slowPointer.next
        }

        return previous?.next == null
    }

    fun isPalindrome2(head: ListNode?): Boolean {
        if (head?.next == null) return true
        // - get to center using slow and fast pointer
        // - add slow steps in stack
        // - after middle start remove elements from stack
        var slowPointer = head
        var fastPointer = head
        val stack = LinkedList<ListNode>()
        stack.add(head)

        // this truple condition allows to not add midle element in stack
        // in case if count of element is odd
        while(fastPointer?.next?.next?.next != null) {
            slowPointer = slowPointer!!.next
            stack.add(slowPointer!!)
            fastPointer = fastPointer.next?.next
        }

        slowPointer = if (fastPointer?.next?.next != null) {
            slowPointer!!.next!!.next
        } else {
            slowPointer!!.next
        }

        while (slowPointer != null) {
            val last = stack.pollLast().`val`
            if (slowPointer.`val` != last) return false
            slowPointer = slowPointer.next
        }

        return stack.isEmpty()
    }
}