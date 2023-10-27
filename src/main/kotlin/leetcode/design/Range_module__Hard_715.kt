package leetcode.design

import java.lang.Math.max
import java.util.*

class Hard_T715_RangeModule {

//    data class Range(
//        val left: Int,
//        val right: Int
//    )
//
//    val ranges = TreeMap<Int,Int>()
//
//    fun addRange(left: Int, right: Int) {
//        val (newLeft, newRight) = addEntry(left, right, ranges.lowerEntry(left))
//        addEntry(newLeft, newRight, ranges.higherEntry(newLeft))
//    }
//
//    private fun addEntry(left: Int, right: Int, entry: Map.Entry<Int, Int>?): Pair<Int, Int> {
//        var newLeft = left
//        var newRight = right
//        entry?.let {
//            if (it.key == newLeft && it.value == newRight) return@let
//            if (newLeft in it.key..it.value
//                    || newRight in it.key..it.value
//                    || ( newLeft < it.key && newRight > it.value)) {
//                newLeft = min(newLeft, it.key)
//                if(newLeft < it.key) ranges.remove(it.key)
//                newRight = max(newRight, it.value)
//            }
//        }
//        ranges[newLeft] = newRight
//
//        return newLeft to newRight
//    }
//
//    fun queryRange(left: Int, right: Int): Boolean {
//
//    }
//
//    fun removeRange(left: Int, right: Int) {
//        val closestLeft = ranges.lowerEntry(left)
//
//        closestLeft?.let {
//            if ((it.key in left..right && it.value in left..right) || left < it.key) {
//                ranges.remove(it.key)
//            } else if (left in it.key..it.value)
//        }
//
//        val closestRight = ranges.higherEntry(left)
//    }
}

fun main(args: Array<String>) {
    // ["RangeModule","addRange","removeRange","queryRange","queryRange","queryRange"]
    Hard_T715_RangeModule2().apply {
        addRange(10,20)
        addRange(15,25)
        addRange(40,50)
        addRange(20,45)
        removeRange(14,16)
        println(queryRange(10,14))
        println(queryRange(13,15))
        println(queryRange(16,17))
    }
    // [[],[10,20],[14,16],[10,14],[13,15],[16,17]]


    /**
     * ["RangeModule","removeRange","addRange","queryRange","addRange","removeRange","queryRange","queryRange","addRange","removeRange"]
    [[],[4,8],[1,10],[1,7],[2,3],[2,3],[8,9],[6,9],[2,3],[1,8]]

    Use Testcase
    Output
    [null,null,null,true,null,null,false,false,null,null]
    Expected
    [null,null,null,true,null,null,true,true,null,null]
     */
}

class Hard_T715_RangeModule2 {

    private var map: TreeMap<Int?, Int?>

    init {
        map = TreeMap()
    }

    fun addRange(left: Int, right: Int) {
        // assume the given range [left, right), we want to find [l1, r1) and [l2, r2) such that l1 is the floor key of left, l2 is the floor key of right. Like this:
        //             [left, right)
        //     [l1, r1)        [l2, r2)
        // Note: l2 could be the same as l1, so they are either both null or both non-null
        val l1 = map.floorKey(left) // return closest key which less then left
        val l2 = map.floorKey(right) // return closest key which less then right

        // try to visualize each case, and what to do based on r1
        if (l1 == null && l2 == null) {
            map[left] = right
        } else if (l1 != null && map[l1]!! >= left) {
            map[l1] = max(right, map[l2]!!) // r2 will always be greater than r1, so no need to check r1
        } else {
            map[left] = max(right, map[l2]!!)
        }

        // we don't want to remove the range starts at left, so left should be exclusive.
        // but we want to remove the one starts at right, so right should be inclusive.
        map.subMap(left, false, right, true).clear()
    }

    fun queryRange(left: Int, right: Int): Boolean {
        val l = map.floorKey(left)
        return if (l != null && map[l]!! >= right) {
            true
        } else false
    }

    fun removeRange(left: Int, right: Int) {
        val l1 = map.lowerKey(left) // I used lowerKey here, since we don't care about the range starting at left, as it should be removed
        val l2 = map.lowerKey(right) // same, we don't care about the range starting at right

        // do this first, in case l1 == l2, the later one will change r1(or r2 in this case)
        if (l2 != null && map[l2]!! > right) {
            map[right] = map[l2]
        }
        if (l1 != null && map[l1]!! > left) {
            map[l1] = left
        }

        // range that starts at left should be removed, so left is inclusive
        // range that starts at right should be kept, so right is exclusive
        map.subMap(left, true, right, false).clear()
    }
}

class RangeModule() {

    val ranges = TreeMap<Int,Int>()

    fun addRange(left: Int, right: Int) {
        val closeToLeft = ranges.floorKey(left) // <= left
        val closeToRight = ranges.floorKey(right) // <= right

        if (closeToLeft == null && closeToRight == null) {
            ranges[left] = right
        } else if (closeToLeft != null && ranges[closeToLeft]!!>= left) {
            ranges[closeToLeft] = right
        } else {
            ranges[left] = java.lang.Math.max(right, ranges[closeToRight]?:-1)
        }
        ranges.subMap(left, false, right, true).clear()
    }

    fun queryRange(left: Int, right: Int): Boolean {
        val closeToLeft = ranges.floorKey(left)
        return closeToLeft != null && ranges[closeToLeft]!! >= right
    }

    fun removeRange(left: Int, right: Int) {
        val closeToLeft = ranges.lowerKey(left) // < left
        val closeToRight = ranges.lowerKey(right) // < right

        if (closeToRight != null && ranges[closeToRight]!! > right) {
            ranges[right] = ranges[closeToRight]!! // because all from left of right value will be removed
        }
        if (closeToLeft != null && ranges[closeToLeft]!! > left) {
            ranges[closeToLeft] = left
        }

        ranges.subMap(left, true, right, false).clear()
    }

}