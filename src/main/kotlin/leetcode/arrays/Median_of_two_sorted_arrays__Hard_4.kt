package leetcode.arrays

fun main(args: Array<String>) {
    Median_Of_Two_Sorted_Arrays__Hard_4().apply {
//        println(findMedianSortedArrays(
//            intArrayOf(1,3),
//            intArrayOf(2)
//        ))
        println(findMedianSortedArrays(
            intArrayOf(1,2),
            intArrayOf(3,4)
        ))
    }
}

class Median_Of_Two_Sorted_Arrays__Hard_4 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val finalSize = nums1.size + nums2.size
        val medianaCount = if (finalSize % 2 == 0) 2 else 1
        val medianaIndexStart = if (medianaCount == 2) finalSize/2 - 1 else finalSize/2
        var i = 0
        var inum1 = 0
        var inum2 = 0
        var sum = 0
        while (i <= (medianaIndexStart + medianaCount - 1)) {
            if (inum2 == nums2.size || (inum1 < nums1.size && nums1[inum1] <= nums2[inum2])) {
                if (i >= medianaIndexStart) sum += nums1[inum1]
                inum1++
            } else {
                if (i >= medianaIndexStart) sum += nums2[inum2]
                inum2++
            }
            i++
        }
        return sum/(medianaCount*1.0)
    }
}