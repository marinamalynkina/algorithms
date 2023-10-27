package leetcode.stringsarrays

fun main(args: Array<String>) {
    Solution2().backspaceCompare("bbbextm", "bbb#extm")
}

/**
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors.
 * '#' means a backspace character.
 * Note that after backspacing an empty text, the text will continue empty.
 */
class Solution2 {
    fun backspaceCompare(s: String, t: String): Boolean {
        val a = s.toCharArray()
        var i = a.size - 1
        var aCur: Char? = null
        var aBScount = 0
        var aNext = false

        val b = t.toCharArray()
        var j = b.size - 1
        var bCur: Char? = null
        var bBScount = 0
        var bNext = false

        while (i >= 0 || j >= 0) {
            do {
                aCur = if (i >= 0) a[i] else null
                if (aCur == '#') aBScount++
                else if (aBScount > 0) {
                    aNext = true
                    aBScount--
                } else {
                    aNext = false
                }
                i--
            } while (aBScount > 0 || aNext)
            do {
                bCur = if (j >= 0) b[j] else null
                if (bCur == '#') bBScount++
                else if (bBScount > 0) {
                    bNext = true
                    bBScount--
                } else {
                    bNext = false
                }
                j--
            } while (bBScount > 0 || bNext)

            if (aCur != bCur) return false
        }

        return true
    }
}