package leetcode.cyclicsort
fun main(args: Array<String>) {
    Easy_268_MissingNumber().apply {
        missingNumber(intArrayOf(3,0,1)) // 2
        missingNumber(intArrayOf(0,1)) // 2
        missingNumber(intArrayOf(9,6,4,2,3,5,7,0,1)) // 8
    }
}

class Easy_268_MissingNumber {
    fun missingNumber(nums: IntArray): Int {
        nums.cyclicSortON()
        nums.forEachIndexed { i, num ->
            if (num != i) return i
        }
        return nums.size
    }

    fun IntArray.cyclicSortON() {
        for (i in 0..lastIndex) {
            while (i != this[i] && this[i] < size && this[i] != this[this[i]]) {
                val num = this[i]
                val temp = this[num]
                this[num] = num
                this[i] = temp
            }
        }
    }


    fun missingNumber2(nums: IntArray): Int {
        val newArray = BooleanArray(nums.size+1) {false}
        nums.forEach { num ->
            newArray[num] = true
        }
        newArray.forEachIndexed { index, b ->
            if (b == false) return index
        }
        return -1
    }
}