package leetcode.dp.strings


fun main(args: Array<String>) {
    println(Medium_139_WordBreak().wordBreak("leetcode", listOf("leet","code")))
}


class Medium_139_WordBreak {

    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val wordsContain = BooleanArray(s.length) { false }
        for(i in 0..s.length-1) {
            wordDict.forEach { word ->
                val startOfWord = i - (word.length-1)
                if (startOfWord >= 0 && word[word.lastIndex] == s[i]
                    && (startOfWord == 0 || wordsContain[startOfWord-1])) {
                    val wordFromString = s.substring(startOfWord, i+1)
                    if (wordFromString == word) {
                        wordsContain[i] = true
                    }
                }
            }
        }
        return wordsContain[wordsContain.lastIndex]
    }
}


//  ==== Trie Optimization======

internal class TrieNode {
    var isWord = false
    var children: MutableMap<Char, TrieNode>

    init {
        children = HashMap()
    }
}


class Solution_Medium_139_WordBreak {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val root = TrieNode()
        for (word in wordDict) {
            var curr: TrieNode? = root
            for (c in word.toCharArray()) {
                if (!curr!!.children.containsKey(c)) {
                    curr.children[c] = TrieNode()
                }
                curr = curr.children[c]
            }
            curr!!.isWord = true
        }
        val dp = BooleanArray(s.length)
        for (i in 0 until s.length) {
            if (i == 0 || dp[i - 1]) {
                var curr: TrieNode? = root
                for (j in i until s.length) {
                    val c = s[j]
                    if (!curr!!.children.containsKey(c)) {
                        // No words exist
                        break
                    }
                    curr = curr.children[c]
                    if (curr!!.isWord) {
                        dp[j] = true
                    }
                }
            }
        }
        return dp[s.length - 1]
    }
}