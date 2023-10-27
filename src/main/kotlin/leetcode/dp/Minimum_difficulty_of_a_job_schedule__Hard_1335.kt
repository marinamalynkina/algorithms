package leetcode.dp

fun main(args: Array<String>) {
    println(Hard_1335_MinimumDifficultyOfAJobSchedule().minDifficulty(intArrayOf(1,1,1),3))
}

class Hard_1335_MinimumDifficultyOfAJobSchedule {

    lateinit var harderJobAfterI: IntArray
    lateinit var jobDifficulty: IntArray
    lateinit var memo: Array<IntArray>
    var D = 0

    fun minDifficulty(jobDifficulty: IntArray, d: Int): Int {
        if (jobDifficulty.size < d) return -1
        D = d
        this.jobDifficulty = jobDifficulty
        harderJobAfterI = IntArray(jobDifficulty.size)
        var hardestJob = -1
        for(i in jobDifficulty.size-1 downTo 0) {
            hardestJob = java.lang.Math.max(jobDifficulty[i], hardestJob)
            harderJobAfterI[i] = hardestJob
        }

        if (d == 1) return hardestJob
        memo = Array(jobDifficulty.size){ IntArray(d+1) {-1} }

        return dp(0,1)
    }

    fun dp(i: Int, d: Int): Int {
        if (d == D) return harderJobAfterI[i]

        val maxJobsForThisDay = harderJobAfterI.size - (D - d)
        var maxDifForDay = -1
        var minOption = Integer.MAX_VALUE
        if (memo[i][d] == -1) {
            for (j in i..maxJobsForThisDay-1) {
                maxDifForDay = java.lang.Math.max(jobDifficulty[j], maxDifForDay)
                val nextDay = dp(j+1, d+1)
                minOption = java.lang.Math.min(minOption, maxDifForDay + nextDay)
            }
            memo[i][d] = minOption
        }
        return memo[i][d]

    }
}