package leetcode.dp


fun main(args: Array<String>) {
//    println(Medium_300_LongestIncreaingSubsequence().lengthOfLIS(intArrayOf(0,1,0,3,2,3)))
    println(Solution_300_NlogN().lengthOfLIS(intArrayOf(1,2,3,0)))
//    println(Solution_300_NlogN().lengthOfLIS(intArrayOf(1,2,3,0,1,2,3,4)))
}

class Medium_300_LongestIncreaingSubsequence {
    fun lengthOfLIS(nums: IntArray): Int {
        val memo = IntArray(nums.size){0}
        var max = 0
        for (i in nums.lastIndex downTo 0) {
            var j = i+1
            var biggerValueIndex = i
            var maxSeq = -1
            while (j < nums.size) {
                if (nums[j] > nums[i] && memo[j] > maxSeq) {
                    biggerValueIndex = j
                    maxSeq = memo[j]
                }
                j++

            }
            memo[i] = memo[biggerValueIndex] + 1
            if (memo[i] > max) max = memo[i]
        }
        return max
    }
}


internal class Solution_300_NlogN {
    fun lengthOfLIS(nums: IntArray): Int {
        val sub = ArrayList<Int>()
        sub.add(nums[0])
        for (i in 1 until nums.size) {
            val num = nums[i]
            if (num > sub[sub.size - 1]) {
                sub.add(num)
            } else {
                val j = binarySearch(sub, num)
                sub[j] = num
            }
        }
        return sub.size
    }

    private fun binarySearch(sub: ArrayList<Int>, num: Int): Int {
        var left = 0
        var right = sub.size - 1
        var mid = (left + right) / 2
        while (left < right) {
            mid = (left + right) / 2
            if (sub[mid] == num) {
                return mid
            }
            if (sub[mid] < num) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}