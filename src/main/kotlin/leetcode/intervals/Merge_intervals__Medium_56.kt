package leetcode.intervals

fun main(args: Array<String>) {
    Merge_Intervals__Medium_56().apply {
        println(
            merge(arrayOf(
                intArrayOf(1,3),
                intArrayOf(2,6),
                intArrayOf(8,10),
                intArrayOf(15,18),
            ))
        )
    }
}

class Merge_Intervals__Medium_56 {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val TYPE_START = 0
        val TYPE_END = 1
        val countOfpoints = intervals.size*2
        val points = Array<IntArray>(countOfpoints){IntArray(2)}
        intervals.forEachIndexed { i, interval ->
            points[i*2] = intArrayOf (interval[0], TYPE_START)
            points[i*2+1] = intArrayOf (interval[1], TYPE_END)
        }
        points.sortWith {a,b -> a[0]-b[0]}
        val mergedIntervals = mutableListOf<IntArray>()
        var currentStart = -1
        var countOfOpened = 0
        var countOfClosed = 0
        var i = 0
        while (i < countOfpoints) {
            val point = points[i]
            when (point[1]) {
                TYPE_START -> {
                    if (countOfClosed == 0 && countOfOpened == 0) currentStart = point[0]
                    countOfOpened++
                }
                TYPE_END -> {
                    if ((i+1) < points.size
                        && points[i+1][1] == TYPE_START
                        && point[0] == points[i+1][0]) {
                        i += 2 // skip this and next, as they equal
                        continue
                    }
                    countOfClosed++
                }
            }
            if (countOfOpened == countOfClosed) {
                // can create new interval
                mergedIntervals.add(intArrayOf (currentStart, point[0]))
                countOfOpened = 0
                countOfClosed = 0
            }
            i++
        }
        return mergedIntervals.toTypedArray()
    }
}