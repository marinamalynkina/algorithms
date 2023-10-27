package Recursion

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 * Constraints:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */

/**
 *
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 */
data class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

/**
 * Runtime 200 ms Beats 97.95%
 * Memory 41.4 MB Beats 68.55%
 */
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    return sumRecursion(l1, l2)
}

fun sumRecursion(l1: ListNode?, l2: ListNode?, extra: Int = 0): ListNode? {
    val sum = (l1?.`val` ?: 0) + (l2?.`val` ?: 0) + extra

    if (l1 != null || l2 != null || extra != 0) {
        val nextNode = sumRecursion(l1?.next, l2?.next, sum / 10)
        val newNode = ListNode(sum % 10)
        newNode.next = nextNode
        return newNode
    } else return null
}


fun main(args: Array<String>) {
    val l1 = ListNode(2, ListNode(4, ListNode(3)))
    val l2 = ListNode(5, ListNode(6, ListNode(4)))
    val l3 = addTwoNumbersWhile(l1,l2)
    println(l3?.`val`)
}

fun addTwoNumbersWhile(l1: ListNode?, l2: ListNode?): ListNode? {
    var curL1 = l1
    var curL2 = l2
    var carry = 0
    var rootNode: ListNode? = null
    var currectNode: ListNode? = null
    while(curL1 != null || curL2 != null || carry != 0) {
        val valL1 = curL1?.`val` ?: 0
        val valL2 = curL2?.`val` ?: 0
        val sum = valL1 + valL2 + carry
        val newNode = ListNode(sum % 10)
        if (currectNode == null) {
            currectNode = newNode
            rootNode = currectNode
        } else {
            currectNode.next = newNode
            currectNode = newNode
        }
        carry = sum / 10
        curL1 = curL1?.next
        curL2 = curL2?.next
    }
    return rootNode
}