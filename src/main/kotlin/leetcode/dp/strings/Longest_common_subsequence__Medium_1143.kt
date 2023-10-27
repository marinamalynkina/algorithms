package leetcode.dp.strings

class Medium_T1143_LongestCommonSubsequence {
    private lateinit var memo: Array<IntArray>
    private var text1: String? = null
    private var text2: String? = null

    fun longestCommonSubsequence(text1: String, text2: String): Int {
        // Make the memo big enough to hold the cases where the pointers
        // go over the edges of the strings.
        memo = Array(text1.length + 1) { IntArray(text2.length + 1) }
        // We need to initialise the memo array to -1's so that we know
        // whether or not a value has been filled in. Keep the base cases
        // as 0's to simplify the later code a bit.
        for (i in 0 until text1.length) {
            for (j in 0 until text2.length) {
                memo[i][j] = -1
            }
        }
        this.text1 = text1
        this.text2 = text2
        return memoSolve(0, 0)
    }

    private fun memoSolve(p1: Int, p2: Int): Int {
        // Check whether or not we've already solved this subproblem.
        // This also covers the base cases where p1 == text1.length
        // or p2 == text2.length.
        if (memo[p1][p2] != -1) {
            return memo[p1][p2]
        }

        // Option 1: we don't include text1[p1] in the solution.
        val option1 = memoSolve(p1 + 1, p2)

        // Option 2: We include text1[p1] in the solution, as long as
        // a match for it in text2 at or after p2 exists.
        val firstOccurence = text2!!.indexOf(text1!![p1], p2)
        var option2 = 0
        if (firstOccurence != -1) {
            option2 = 1 + memoSolve(p1 + 1, firstOccurence + 1)
        }

        // Add the best answer to the memo before returning it.
        memo[p1][p2] = Math.max(option1, option2)
        return memo[p1][p2]
    }
}


class Solution {
    fun longestCommonSubsequence(text1: String, text2: String): Int {

        val n = text1.length
        val m = text2.length
        val dp = Array(n+1) { IntArray(m+1) {0} }
        dp[n][m] = 0

        for(i in n-1 downTo 0) {
            for(j in m-1 downTo 0) {

                if (text1[i] == text2[j]) {
                    dp[i][j] = 1+dp[i+1][j+1]
                } else {
                    dp[i][j] = java.lang.Math.max(dp[i+1][j], dp[i][j+1])
                }
            }
        }

        return dp[0][0]
    }
}