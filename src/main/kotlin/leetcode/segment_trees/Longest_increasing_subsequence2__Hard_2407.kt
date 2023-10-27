package leetcode.segment_trees

fun main(args:Array<String>) {
    Hard_2407_LongestIncreasingSubsequence2().apply {
//        lengthOfLIS(intArrayOf(4,2,1,4,3,4,5,8,15), 3)
    }
}


class Hard_2407_LongestIncreasingSubsequence2 {

//    fun lengthOfLIS(nums: IntArray, k: Int): Int {
//        if (nums.size <= 1) return nums.size
//        // Find max value in array
//        // +1 is required to have this max element in array.
//        val maxValue = (nums.maxOrNull() ?: 0) + 1
//
//        val tree = MaxSubSeqSegTree(maxValue)
//        var max = 0
//        nums.forEach { value ->
//            var maxfromPrevSq = 0
//            for(i in value-k..value-1) {
//                if (i > 0 && countArray[i] > maxfromPrevSq) maxfromPrevSq = countArray[i]
//            }
//            if (countArray[value] <= maxfromPrevSq) {
//                countArray[value] = maxfromPrevSq+1
//                if (countArray[value] > max)
//                    max = countArray[value]
//            }
//        }
//        return max
//    }
//
//    inner class MaxSubSeqSegTree(val size: Int) {
//        val countArray = IntArray(size) {0}
//
//        fun getMax(num: Int) {
//
//        }
//    }
}


class Solution {
    fun lengthOfLIS(nums: IntArray, k: Int): Int {
        val segmentTree = SegmentTree(nums.max()!! + 1)
        var ans = 0
        for (x in nums) {
            val l = (x - k).coerceAtLeast(0)
            val r = x
            val result = segmentTree.getMax(l, r) + 1
            ans = maxOf(ans, result)
            segmentTree.update(x, result)
        }
        return ans
    }
}

class SegmentTree(_n: Int) {
    private val n = roundToPowerOf2(_n)
    private val t = IntArray(2 * n)

    fun getMax(l: Int, r: Int): Int {
        return getMax(1, 0, n, l, r)
    }
    private fun getMax(v: Int, tl: Int, tr: Int, l: Int, r: Int): Int {
        if (r <= tl || l >= tr) return 0
        if (l <= tl && tr <= r) return t[v]
        val tm = (tl + tr) / 2
        return maxOf(getMax(v * 2, tl, tm, l, r), getMax(2 * v + 1, tm, tr, l, r))
    }

    fun update(pos: Int, value: Int) {
        var pos = pos + n
        t[pos] = value
        pos /= 2
        while (pos > 0) {
            t[pos] = maxOf(t[pos * 2], t[pos * 2 + 1])
            pos /= 2
        }
    }

    private fun roundToPowerOf2(n: Int): Int {
        var n = n
        while (n.and(n-1) != 0) {
            n += -n and n
        }
        return n
    }
}