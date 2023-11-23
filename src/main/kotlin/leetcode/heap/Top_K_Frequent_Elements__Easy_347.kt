package leetcode.heap

import java.util.PriorityQueue

/**
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
class Top_K_Frequent_Elements__Easy_347 {

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val hashMap = mutableMapOf<Int,Int>()
        nums.forEach {
            hashMap.put(it, hashMap.get(it)?.plus(1) ?: 1)
        }
        val priorityQueue = PriorityQueue<Pair<Int, Int>>{ pair1, pair2 -> pair2.second - pair1.second }
        hashMap.keys.forEach {
            priorityQueue.offer(Pair(it, hashMap.get(it)!!))
        }
        var i = 0
        val result = IntArray(k)
        while (priorityQueue.isNotEmpty() && i < k) {
            result[i] = priorityQueue.poll().first
            i++
        }
        return result
    }
}