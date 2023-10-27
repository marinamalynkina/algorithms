package leetcode.stringsarrays

import java.util.*

fun main(args: Array<String>) {
    println(Easy_T20_ValidParentheses().isValid("([)]"))
}

class Easy_T20_ValidParentheses {

    fun isValid(s: String): Boolean {
        val stack = LinkedList<Char>()
        for(c in s.toCharArray()) {
            when(c) {
                '(','[','{' -> {
                    stack.push(c)
                }
                ')',']','}' -> {
                    if (isCloseBracketFor(stack.peek(), c)) stack.pop()
                    else return false
                }
            }
        }
        return stack.isEmpty()
    }

    private fun isCloseBracketFor(openBracket: Char?, closeBracket: Char): Boolean {
        return when(openBracket) {
            '(' -> closeBracket == ')'
            '[' -> closeBracket == ']'
            '{' -> closeBracket == '}'
            else -> false
        }
    }
}
