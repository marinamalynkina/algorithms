package leetcode.dp

fun main(args: Array<String>) {
    println(Hard_2407_LongestIncreasingSubsequence2().run {
//        lengthOfLIS(intArrayOf(4,2,1,4,3,4,5,8,15), 3)
        lengthOfLIS(intArrayOf(7,4,5,1,8,12,4,7), 5)
    })
}

// Timeout
class Hard_2407_LongestIncreasingSubsequence2 {

    fun lengthOfLIS(nums: IntArray, k: Int): Int {
        if (nums.size <= 0) return nums.size
        val memo = IntArray(nums.size) {0}
        var max = 0

        for (i in 0..nums.lastIndex) {
            var j = i-1
            var biggerValueIndex = i
            var maxSeq = -1
            while (j in 0..nums.lastIndex) {
                val diff = nums[i]-nums[j]
                if (nums[i] > nums[j] && diff in -k..k && memo[j] > maxSeq) {
                    biggerValueIndex = j
                    maxSeq = memo[j]
                }
                j--
            }
            memo[i] = memo[biggerValueIndex]+1
            if (memo[i] > max) max = memo[i]
        }
        return max
    }
}