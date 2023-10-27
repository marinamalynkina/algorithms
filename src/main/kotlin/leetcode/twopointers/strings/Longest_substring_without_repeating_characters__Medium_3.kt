package leetcode.twopointers.strings

import kotlin.math.max

fun main(args: Array<String>) {
    Longest_Substring_Without_Repeating_characters_Medium_3().apply {
//        println(lengthOfLongestSubstring("abcabcbb"))
//        println(lengthOfLongestSubstring("bbbbb"))
//        println(lengthOfLongestSubstring("pwwkew"))
        println(lengthOfLongestSubstring("~   "))
    }

//    "0123456789aAbBcCdDeEfFgGhHiIjJlLkKmMnNoOpPrRsStTuUvVwWxXyYsZ!@#$%^&*()_+=-,./?\\|".forEach {
//        println(it.code)
//    }
}


class Longest_Substring_Without_Repeating_characters_Medium_3 {

    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) return 0
        var maxSize = 0
        var leftPosition = 0
        var rightPosition = 0
        val chars = Array<Int>(126-32+1) {-1}
        for(i in 0..s.length-1) {
            val oldPosition = s[i].code - 32
            if (chars[oldPosition] != -1 && chars[oldPosition] >= leftPosition ) {
                leftPosition = chars[oldPosition] + 1
            }
            rightPosition = i
            chars[oldPosition] = i
            maxSize = max(maxSize, rightPosition - leftPosition + 1)
        }
        return maxSize
    }


    fun lengthOfLongestSubstring2(s: String): Int {
        if (s.isEmpty()) return 0
        var maxSize = 0
        var leftPosition = 0
        var rightPosition = 0
        val map = mutableMapOf<Char, Int>()
        for(i in 0..s.length-1) {
            map.get(s[i])?.let { oldPosition ->
                // remove from map before oldPosition
                map.clearMap(leftPosition, oldPosition, s)
                leftPosition = oldPosition + 1
            }

            rightPosition = i
            map.put(s[i], i)
            maxSize = max(maxSize, rightPosition - leftPosition + 1)
        }
        return maxSize
    }

    fun MutableMap<Char, Int>.clearMap(from: Int, to: Int, s: String) {
        for (i in from..to) {
            remove(s[i])
        }
    }
}