package leetcode.heap

import java.util.PriorityQueue

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Can you solve it without sorting?
 */
class Kth_Largest_Element_in_an_Array__Medium_215 {

    fun findKthLargest(nums: IntArray, k: Int): Int {
        val n = nums.size
        if (n == 1) return nums[0]
        val pq = PriorityQueue<Int> { a, b -> b - a }
        nums.forEach { pq.offer(it) }

        var i = 1
        var result: Int = 0
        while (i <= k) {
            result = pq.poll()
            i++
        }
        return result
    }
}