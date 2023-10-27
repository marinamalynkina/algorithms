package leetcode.hash

import java.lang.Math.max
import java.util.*


fun main(args: Array<String>) {
//    val test1 = arrayOf(
//        intArrayOf(3,1,6),
//        intArrayOf(-9,5,7)
//    )
    val test1_2 = arrayOf(
        intArrayOf(3,1,6),
        intArrayOf(-9,5,6)
    )
//    val test2 = arrayOf(
//        intArrayOf(1),
//        intArrayOf(-4)
//    )
//    val test3 = arrayOf(
//        intArrayOf(1,-7,-8)
//    )
//    val test4 = arrayOf(
//        intArrayOf(-4,-5,-2,8)
//    )
    Hard_T2713_MaximumStrictlyIncreasingCellsInAMatrix2().run {
//        println(maxIncreasingCells(test1))
        println(maxIncreasingCells(test1_2))
//        println(maxIncreasingCells(test2))
//        println(maxIncreasingCells(test3))
//        println(maxIncreasingCells(test4))
    }

//    println(Solution().maxIncreasingCells(test1_2))
}

class Hard_T2713_MaximumStrictlyIncreasingCellsInAMatrix2 {
    fun maxIncreasingCells(mat: Array<IntArray>): Int {
        val rowsCount = mat.size
        val columsCount = mat[0].size
        val rows = Array<Status>(rowsCount) {Status()}
        val columns = Array<Status>(columsCount) {Status()}

        val orderedValues = PriorityQueue<Item> { a, b ->
            a.value - b.value
        }
        for (i in 0..rowsCount-1) {
            for (j in 0..columsCount-1) {
                orderedValues.add(Item(mat[i][j], i, j))
            }
        }

        var result = 0
        while (orderedValues.isNotEmpty()) {
            val item = orderedValues.poll()
            val rowStatus = rows[item.row]
            val columnStatus = columns[item.column]
            val max = max(
                rowStatus.getMax(item.value, 0),
                columnStatus.getMax(item.value, 0)
            )
            //update status
            rowStatus.update(item.value, max)
            columnStatus.update(item.value, max)
            result = max(result, max)
        }
        return result
    }



    data class Status(
        var lastCheckedValue: Int = Int.MIN_VALUE,
        var max: Int = 0,
        var previousMax: Int = 0
    ) {
        fun getMax(value: Int, newMax: Int): Int {
            return if (value == lastCheckedValue) {
                max(previousMax+1, newMax)
            } else {
                max(max+1, newMax)
            }
        }

        fun update(value: Int, newMax: Int) {
            if (value > lastCheckedValue) {
                previousMax = max
                lastCheckedValue = value
            }
            max = max(max, newMax)
        }
    }

    data class Item(
        val value: Int,
        val row: Int,
        val column: Int
    )
}

// Time Limit Exceeded on big test
class Hard_T2713_MaximumStrictlyIncreasingCellsInAMatrix {

    private var maxsteps = 0
    private var visitedCount = 0

    fun maxIncreasingCells(mat: Array<IntArray>): Int {
        if (mat.isEmpty()) return 0
        val maxMatrix = Array(mat.size) { IntArray(mat[0].size) { 0 } }

        val countOfElements = mat.size*mat[0].size

        for (i in mat.indices) {
            for (j in mat[i].indices) {
                if (visitedCount == countOfElements) break
                val steps = visit(i, j, mat, maxMatrix)
                maxsteps = max(steps, maxsteps)
            }
        }

        return maxsteps
    }

    private fun visit(i: Int, j: Int, mat: Array<IntArray>, maxMatrix: Array<IntArray>): Int {
        if (maxMatrix[i][j] > 0) return maxMatrix[i][j]

        var max = 1
        // find bigger in row
        for (index in mat[i].indices) {
            if (index != j && mat[i][index] > mat[i][j]) {
                val steps = visit(i, index, mat, maxMatrix) + 1
                max = max(steps, max)
                if (visitedCount == mat.size*mat[0].size) break
            }
        }
        // find bigger in column
        if (mat.size > 1) {
            for (index in mat.indices) {
                if (index != i && mat[index][j] > mat[i][j]) {
                    val steps = visit(index, j, mat, maxMatrix) + 1
                    max = max(steps, max)
                    if (visitedCount == mat.size*mat[0].size) break
                }
            }
        }

        maxMatrix[i][j] = max
        visitedCount++

        return max
    }



}


internal class Solution {
    fun maxIncreasingCells(mat: Array<IntArray>): Int {
        val m = mat.size
        val n = mat[0].size
        val rows = IntArray(m)
        val cols = IntArray(n)
        val rowMaxVals = IntArray(m)
        val colMaxVals = IntArray(n)
        val rowSubMaxVals = IntArray(m)
        val colSubMaxVals = IntArray(n)
        for (i in 0 until m) {
            rows[i] = Int.MIN_VALUE
        }
        for (i in 0 until n) {
            cols[i] = Int.MIN_VALUE
        }
        val queue = PriorityQueue { a: IntArray, b: IntArray ->
            a[0] - b[0]
        }
        for (i in 0 until m) {
            for (j in 0 until n) {
                queue.add(intArrayOf(mat[i][j], i, j))
            }
        }
        var res = 0
        while (!queue.isEmpty()) {
            val arr = queue.remove()
            val `val` = arr[0]
            val row = arr[1]
            val col = arr[2]
            var cur = 1
            cur = if (`val` == rows[row]) {
                max(rowSubMaxVals[row] + 1, cur)
            } else {
                max(rowMaxVals[row] + 1, cur)
            }
            cur = if (`val` == cols[col]) {
                max(colSubMaxVals[col] + 1, cur)
            } else {
                max(colMaxVals[col] + 1, cur)
            }
            if (`val` > rows[row]) {
                rowSubMaxVals[row] = rowMaxVals[row]
                rows[row] = `val`
            }
            if (`val` > cols[col]) {
                colSubMaxVals[col] = colMaxVals[col]
                cols[col] = `val`
            }
            rowMaxVals[row] = max(rowMaxVals[row], cur)
            colMaxVals[col] = max(colMaxVals[col], cur)
            res = max(res, cur)
        }
        return res
    }
}