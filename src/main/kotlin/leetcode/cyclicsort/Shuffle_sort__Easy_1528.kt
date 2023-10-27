package leetcode.cyclicsort

import java.lang.StringBuilder

class Shuffle_sort__Easy_1528 {
    fun restoreString(s: String, indices: IntArray): String {
        val sb = StringBuilder(s)
        for(i in 0..indices.lastIndex) {
            while (i != indices[i]) {
                val tempfromIndex = indices[indices[i]]
                val tempFromS = sb[indices[i]]
                indices[indices[i]] = indices[i]
                sb[indices[i]] = sb[i]
                indices[i] = tempfromIndex
                sb[i] = tempFromS
            }
        }
        return sb.toString()
    }
}