package leetcode.dp

class MaximumScoreFromPerformingMultiplicationOperations_Hard_T1770 {
    lateinit var memo: Array<IntArray>
    lateinit var nums: IntArray
    lateinit var multipliers: IntArray
    var m: Int = 0
    var n: Int = 0

    fun maximumScore(nums: IntArray, multipliers: IntArray): Int {
        this.nums = nums
        n = nums.size
        this.multipliers = multipliers
        m = multipliers.size
        memo = Array(multipliers.size) { IntArray(multipliers.size){-1} }
        return dp(0, 0)
    }

    fun dp(i: Int, left: Int): Int {
        if(i == m) return 0

        val right = n-1-(i-left)
        var result = memo[i][left]
        if (result == -1) {
            result = java.lang.Math.max(
                multipliers[i]*nums[left] + dp(i+1,left+1),
                multipliers[i]*nums[right] + dp(i+1,left)
            )
            memo[i][left] = result
        }

        return result
    }
}