package leetcode.stringsarrays

fun main(args: Array<String>) {
    println(Easy_T258_AddDigits().addDigits(38))
}

class Easy_T258_AddDigits {
    fun addDigits(num: Int): Int {

        var nnum = num
        while (nnum > 9) {
            var nextNum = 0
            nnum.toString().forEach { c ->
                nextNum += c.toString().toInt()
            }
            nnum = nextNum
        }
        return nnum
    }
}