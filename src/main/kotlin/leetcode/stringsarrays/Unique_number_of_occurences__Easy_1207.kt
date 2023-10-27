package leetcode.stringsarrays
fun main(args: Array<String>) {
    Easy_T1207_UniqueNumberOfOccurences().uniqueOccurrences(intArrayOf(1,2,2,1,1,3))
}

class Easy_T1207_UniqueNumberOfOccurences {

    fun uniqueOccurrences(arr: IntArray): Boolean {

        val oc = mutableMapOf<Int,Int>()
        arr.forEach { a ->
            oc[a] = (oc[a] ?: 0) + 1
        }
        return oc.values.toSet().size == oc.size
    }
}