package leetcode.heap

import java.util.LinkedList

class Reorganize_String__Medium__767 {
    fun reorganizeString(s: String): String {
        val repeatedChars = mutableMapOf<Char, Int>()
        val newString = StringBuilder()
        var previousChar = ' '
        var i = 0
        while(i < s.length) {
            val char = s[i]
            if (char != previousChar) {
                newString.append(char)
                previousChar = char
            } else {
                // missed condition on impossible string
                repeatedChars.put(char, repeatedChars.getOrDefault(char, 0) + 1)
                do {
                    repeatedChars.keys.forEach {
                        val count = repeatedChars.get(it)!!
                        if (count > 0 && it != previousChar) {
                            newString.append(char)
                            previousChar = char
                            val newCount = count - 1
                            if (newCount == 0) {
                                repeatedChars.remove(it)
                            } else {
                                repeatedChars.put(it, count - 1)
                            }
                        }
                    }
                } while(repeatedChars.size > 1)
            }
            i++
        }

        return if(newString.length == s.length) newString.toString() else ""
    }

}

fun main() {
    Reorganize_String__Medium__767().apply {
        println(reorganizeString("aab"))
    }
}