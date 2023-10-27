package leetcode.disjointset

fun main(args: Array<String>) {
    `Medium_T1101_the-earliest-moment-when-everyone-become-friends`().apply {
        earliestAcq(
            arrayOf(
                intArrayOf(20190322,4,5),
                intArrayOf(20190101,0,1),
                intArrayOf(20190104,3,4),
                intArrayOf(20190107,2,3),
                intArrayOf(20190211,1,5),
                intArrayOf(20190224,2,4),
                intArrayOf(20190301,0,3),
                intArrayOf(20190312,1,2),
            ),
            6
        )

    }
}


class `Medium_T1101_the-earliest-moment-when-everyone-become-friends` {
    fun earliestAcq(logs: Array<IntArray>, n: Int): Int {
        val newLogs = logs.sortedWith(Comparator { o1, o2 -> o1[0] - o2[0] })

        var countOfGroups = n
        val uf = UnionFind(n)
        newLogs.forEach { log ->
            val time = log[0]
            val a = log[1]
            val b = log[2]

            if (uf.union(a, b)) countOfGroups--

            if (countOfGroups == 1) return time
        }
        return -1
    }


}

class UnionFind(
    val size: Int
) {
    private val group: IntArray
    private val rank: IntArray

    init {
        group = IntArray(size) { i -> i }
        rank = IntArray(size) { i -> 0 }
    }

    fun union(a: Int, b: Int): Boolean {
        val groupA = find(a)
        val groupB = find(b)

        var isMerged = false
        if (groupA == groupB) return isMerged

        isMerged = true
        if (rank[groupA] > rank[groupB]) group[groupB] = groupA
        else if (rank[groupA] < rank[groupB]) group[groupA] = groupB
        else  {
            group[groupA] = groupB
            rank[groupB] += 1
        }

        return isMerged
    }

    fun find(a: Int): Int {
        if (group[a] != a) {
            group[a] = find(group[a])
        }
        return group[a]
    }
}