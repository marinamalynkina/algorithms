package leetcode.cyclicsort

fun main(args: Array<String>) {
    Find_All_Numbers_Disappeared_In_Array_Easy_448().apply {
        findDisappearedNumbers(intArrayOf(4,3,2,7,8,2,3,1))
        findDisappearedNumbers(intArrayOf(1,1))
    }
}

class Find_All_Numbers_Disappeared_In_Array_Easy_448 {
    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        val result = mutableListOf<Int>()
        nums.cyclicSort()
        nums.forEachIndexed { i, num ->
            if (num-1 != i) result.add(i+1)
        }
        return result
    }

    fun IntArray.cyclicSort() {
        for (i in 0..lastIndex) {
            while (i != this[i]-1 && this[i] != this[this[i]-1]) {
                val num = this[i]
                val temp = this[num-1]
                this[num-1] = num
                this[i] = temp
            }
        }
    }
}