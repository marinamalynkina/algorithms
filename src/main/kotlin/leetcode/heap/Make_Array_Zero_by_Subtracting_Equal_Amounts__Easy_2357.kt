package leetcode.heap

import java.util.PriorityQueue
import kotlin.math.max

/**
 * You are given a non-negative integer array nums. In one operation, you must:
 *
 * Choose a positive integer x such that x is less than or equal to the smallest non-zero element in nums.
 * Subtract x from every positive element in nums.
 * Return the minimum number of operations to make every element in nums equal to 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,0,3,5]
 * Output: 3
 * Explanation:
 * In the first operation, choose x = 1. Now, nums = [0,4,0,2,4].
 * In the second operation, choose x = 2. Now, nums = [0,2,0,0,2].
 * In the third operation, choose x = 2. Now, nums = [0,0,0,0,0].
 * Example 2:
 *
 * Input: nums = [0]
 * Output: 0
 * Explanation: Each element in nums is already 0 so no operations are needed.
 */
class Make_Array_Zero_by_Subtracting_Equal_Amounts__Easy_2357 {

    fun minimumOperations(nums: IntArray): Int {
        if (nums.size == 0) return 0
        val minHeap = PriorityQueue<Int>(Comparator { o1, o2 ->
            o1 - o2
        })
        var maxValue = 0
        nums.forEach {
            if (it > 0) {
                minHeap.add(it)
                maxValue = max(it, maxValue)
            }
        }

        var countIterations = 0
        var previousValue = 0
        while(maxValue > 0 && minHeap.size > 0) {
            val reduceOn = minHeap.poll() - previousValue
            if (reduceOn > 0) {
                maxValue -= reduceOn
                previousValue += reduceOn
                countIterations++
            }
        }
        return countIterations
    }

    fun minimumOperations2(nums: IntArray): Int {
        return nums.toSet().let {
            it.size - (if (it.contains(0)) 1 else 0)
        }
    }
}

fun main() {
    Make_Array_Zero_by_Subtracting_Equal_Amounts__Easy_2357().apply {
//        println(minimumOperations(intArrayOf(1,5,0,3,5)))
//        println(minimumOperations(intArrayOf(1,3,2,2,3,4,1,1,4)))
//        println(minimumOperations(intArrayOf(1,1,1,2,2,2,3,3)))
//        println(minimumOperations(intArrayOf(30,58,36,65)))

        println(minimumOperations(intArrayOf(1,5,0,3,5)) == 3)
        println(minimumOperations(intArrayOf(1,3,2,2,3,4,1,1,4)) == 4)
        println(minimumOperations(intArrayOf(1,1,1,2,2,2,3,3)) == 3)
        println(minimumOperations(intArrayOf(30,58,36,65)) == 4)
    }
}
