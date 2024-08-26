package leetcode.xor

import java.lang.StringBuilder

/**
 * Given two binary strings a and b, return their sum as a binary string.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 104
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 */
class Add_Binary__Easy_67 {

    fun addBinary(a: String, b: String): String {
        var ia = a.length - 1
        var ib = b.length - 1

        var newNumber = ""
        var addToNext = '0'
        while (ia >= 0 || ib >= 0) {
            val aChar = a.getOrElse(ia) {'0'}
            val bChar = b.getOrElse(ib) {'0'}
            when {
                (aChar == '1' && bChar == '1' && addToNext == '0') -> {
                    newNumber = "0${newNumber}"
                    addToNext = '1'
                }
                (aChar == '1' && bChar == '1' && addToNext == '1') -> {
                    newNumber = "1${newNumber}"
                    addToNext = '1'
                }
                (aChar == '1' && bChar == '0' && addToNext == '0') -> {
                    newNumber = "1${newNumber}"
                    addToNext = '0'
                }
                (aChar == '1' && bChar == '0' && addToNext == '1') -> {
                    newNumber = "0${newNumber}"
                    addToNext = '1'
                }
                (aChar == '0' && bChar == '1' && addToNext == '0') -> {
                    newNumber = "1${newNumber}"
                    addToNext = '0'
                }
                (aChar == '0' && bChar == '1' && addToNext == '1') -> {
                    newNumber = "0${newNumber}"
                    addToNext = '1'
                }
                (aChar == '0' && bChar == '0' && addToNext == '0') -> {
                    newNumber = "0${newNumber}"
                    addToNext = '0'
                }
                (aChar == '0' && bChar == '0' && addToNext == '1') -> {
                    newNumber = "1${newNumber}"
                    addToNext = '0'
                }
            }
            ia--
            ib--
        }

        if (addToNext == '1')
            newNumber = "1${newNumber}"


        return newNumber
    }
}

fun main() {
    Add_Binary__Easy_67().apply {
        println(addBinary("11", "1")+"=100")
        println(addBinary("1010", "1011")+"=10101")
    }
}