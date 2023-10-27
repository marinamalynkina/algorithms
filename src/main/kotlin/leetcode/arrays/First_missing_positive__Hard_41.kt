package leetcode.arrays

import kotlin.math.abs

fun main(args: Array<String>) {
    First_Missing_Positive_Hard_41().apply {
        firstMissingPositive(intArrayOf(1,2,0))
        firstMissingPositive(intArrayOf(3,4,-1,1))
        firstMissingPositive(intArrayOf(7,8,9,11,12))
    }
}

class First_Missing_Positive_Hard_41 {

    fun firstMissingPositive(nums: IntArray): Int {
        // find 1. If not exist then it's the first missing positive
        nums.find { it == 1 }.let {
            if (it == null) return 1
        }

        // replace non positive and numbers > nums.size
        nums.forEachIndexed { i, num ->
            if (num < 1 || num > nums.size) {
                nums[i] = 1
            }
        }

        // index a numbers
        nums.forEach { num ->
            val _num = abs(num)
            if (nums[_num-1] > 0) {
                nums[_num-1] *= -1
            }
        }

        // find missing
        val missingNumber = nums.size + 1
        nums.forEachIndexed { i, num ->
            if (num > 0) return i+1
        }
        return missingNumber
    }
}