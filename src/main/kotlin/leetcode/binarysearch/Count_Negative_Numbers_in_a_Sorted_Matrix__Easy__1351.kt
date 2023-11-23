package leetcode.binarysearch

/**
 * Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * Output: 8
 * Explanation: There are 8 negatives number in the matrix.
 * Example 2:
 *
 * Input: grid = [[3,2],[1,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * -100 <= grid[i][j] <= 100
 *
 *
 * Follow up: Could you find an O(n + m) solution?
 */
class Count_Negative_Numbers_in_a_Sorted_Matrix__Easy__1351 {

    fun countNegatives(grid: Array<IntArray>): Int {
        var countOfNegative = 0
        grid.forEach {
            countOfNegative += it.size - findFirstNegativeIndex(it)
        }
        return countOfNegative
    }

    private fun findFirstNegativeIndex(array: IntArray): Int {
        var left = 0
        var right = array.size - 1
        var middle = 0
        var firstNegativeIndex = array.size
        while (left <= right) {
            middle = left + (right - left)/2
            if (array[middle] >= 0) left = middle + 1
            else if ((middle - 1) >=0 && array[middle - 1] >= 0) {
                firstNegativeIndex = middle
                return firstNegativeIndex
            }
            else right = middle - 1
        }
        return firstNegativeIndex
    }
}

fun main() {
    Count_Negative_Numbers_in_a_Sorted_Matrix__Easy__1351().apply {
        println(
            countNegatives(
                arrayOf(
                    intArrayOf(4,3,2,-1),
                    intArrayOf(3,2,1,-1),
                    intArrayOf(1,1,-1,-2),
                    intArrayOf(-1,-1,-2,-3),
                )
            )
        )
    }
}