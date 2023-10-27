package leetcode.arrays

class Longest_increasing_subsequence2__Hard_2407 {

    fun lengthOfLIS(nums: IntArray, k: Int): Int {
        if (nums.size <= 1) return nums.size
        val maxValue = nums.maxOrNull() ?: 0
        val countArray = IntArray(maxValue+1) {0}
        var max = 0
        nums.forEach { value ->
            var maxfromPrevSq = 0
            for(i in value-k..value-1) {
                if (i > 0 && countArray[i] > maxfromPrevSq) maxfromPrevSq = countArray[i]
            }
            if (countArray[value] <= maxfromPrevSq) {
                countArray[value] = maxfromPrevSq+1
                if (countArray[value] > max)
                    max = countArray[value]
            }
        }
        return max
    }
}