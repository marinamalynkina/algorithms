package leetcode.heap

import java.util.PriorityQueue

class Kth_Largest_Element_in_a_Stream__Easy_703(val k: Int, nums: IntArray) {

    val priorityQ = PriorityQueue<Int>(nums.size) { num1, num2 -> num1 - num2}

    init {
        nums.forEach { num ->
            add(num)
        }
    }

    fun add(`val`: Int): Int {
        priorityQ.offer(`val`)
        if (priorityQ.size > k) priorityQ.poll()
        return priorityQ.peek()
    }

}