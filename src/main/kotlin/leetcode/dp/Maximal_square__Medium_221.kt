package leetcode.dp

class Maximal_square__Medium_221 {

    fun maximalSquare(matrix: Array<CharArray>): Int {
        if (matrix.size == 0) return 0
        val dp = Array(matrix.size){IntArray(matrix[0].size){0}}
        var max = 0
        for(i in 0..matrix.size-1) {
            for (j in 0..matrix[i].size-1) {
                if (matrix[i][j] == '1') {
                    val left = dp(i,j-1, dp)
                    val top = dp(i-1,j, dp)
                    val lefttop = dp(i-1, j-1, dp)
                    dp[i][j] = java.lang.Math.min(java.lang.Math.min(left, top),lefttop) + 1
                    max = java.lang.Math.max(max, dp[i][j])
                }
            }
        }
        return max*max
    }

    fun dp(i:Int, j: Int, dp: Array<IntArray>): Int {
        if (i in 0..dp.size-1 && j in 0..dp[0].size-1) return dp[i][j]
        else return 0
    }
}