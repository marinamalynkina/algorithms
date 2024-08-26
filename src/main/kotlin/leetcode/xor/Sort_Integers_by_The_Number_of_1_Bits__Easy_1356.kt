package leetcode.xor

class Sort_Integers_by_The_Number_of_1_Bits__Easy_1356 {
    fun sortByBits(arr: IntArray): IntArray {
        return arr.sortedWith { n1, n2 ->
            val n1bits = countBits(n1)
            val n2bits = countBits(n2)
            if (n1bits == n2bits) n1 - n2
            else n1bits - n2bits
        }.toIntArray()
    }

    fun countBits(num: Int): Int {
        var num_ = num
        var countBits = 0
        while(num_ > 0) {
            if (num_ xor 1 < num_) countBits++
            num_ = num_ shr 1
        }
        println("$num has $countBits bits")
        return countBits
    }
}

fun main() {
    Sort_Integers_by_The_Number_of_1_Bits__Easy_1356().apply {
//        sortByBits(intArrayOf(0,1,2,3,4,5,6,7,8)).forEach {
//            print("$it, ")
//        }
        sortByBits(intArrayOf(1024,512,256,128,64,32,16,8,4,2,1)).forEach {
            print("$it, ")
        }
    }
}