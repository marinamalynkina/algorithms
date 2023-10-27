package leetcode.dp

class Climbing_stairs__Easy_70 {

    fun climbStairs(n: Int): Int {
        if (n == 1) {
            return 1
        }
        val dp = IntArray(n + 1)
        dp[1] = 1
        dp[2] = 2
        for (i in 3..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }

    val map = mutableMapOf<Int,Int>()
//=============================================
    fun climbStairs2(n: Int): Int {
        return f(n)
    }

    fun f(i: Int): Int {
        if (i <= 2) return i;
        var result = map[i]
        if (result == null) {
            result = f(i-1) + f(i-2)
            map[i] = result
        }
        return result!!
    }
}