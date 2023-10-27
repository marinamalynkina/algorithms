package leetcode.twopointers

fun main(args: Array<String>) {
    println(Easy_283_Move_Zeroes().moveZeroes(intArrayOf(0,1,0)))
}

class Easy_283_Move_Zeroes {
    fun moveZeroes(nums: IntArray): Unit {
        val n = nums.size
        var countOFZeros = 0
        for (i in 0..n-1) {
            val num = nums[i]
            if (num == 0) countOFZeros++
            else {
                // move left on countOFZeros
                nums[i-countOFZeros] = num
                nums[i] = 0
            }

        }
    }

    // with copy
    // fun moveZeroes(nums: IntArray): Unit {
    //     val n = nums.size
    //     val newNums = IntArray(n)
    //     var start = 0
    //     var end = n-1
    //     for (i in 0 .. n-1) {
    //         if (nums[i] == 0) {
    //             // put in the end
    //             newNums[end--] = 0
    //         } else {
    //             newNums[start++] = nums[i]
    //         }
    //     }
    //     return
    // }
}