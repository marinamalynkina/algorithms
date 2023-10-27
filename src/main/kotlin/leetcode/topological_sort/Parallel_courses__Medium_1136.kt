package leetcode.topological_sort

import java.lang.Math.max

fun main(args: Array<String>) {
//    Medium_T1136_ParallelCourses().minimumSemesters(3, arrayOf(intArrayOf(1,3), intArrayOf(2,3)))
    Medium_T1136_ParallelCourses().minimumSemesters(3, arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(3,1)))
}

/**
 * Runtime 460 ms Beats 17.39%
 * Memory 57.6 MB Beats 30.44%
 */
class Medium_T1136_ParallelCourses {
    fun minimumSemesters(n: Int, relations: Array<IntArray>): Int {
        val heads = Array(n) {-1}
        val children = Array(n) { mutableSetOf<Int>() }
        var count = n
        relations.forEach { relation ->
            if (relation.size == 2) {
                val prevNode = relation[0] - 1
                val nextNode = relation[1] - 1

                if (heads[prevNode] == -1) count--
                heads[nextNode] = prevNode
                children[prevNode].add(nextNode)
            }
        }
        if (count == 0) return -1

        var max = -1
        for(i in 0..heads.size-1) {
            if (heads[i] == -1) {
                val visited = IntArray(n) {0}
                max = max(max, searchMaxPath(i, children, visited))
            }
        }

        return max
    }

    fun searchMaxPath(node: Int, children: Array<MutableSet<Int>>, visited: IntArray): Int {
        val v = visited[node]
        if (v > 0 || v == -1) return v
        if (v == 0) visited[node] = -1
        var max = 0
        for (child in children[node]) {
            val maxpath = searchMaxPath(child, children, visited)
            if (maxpath == -1) return -1
            max = max(max, maxpath)
        }
        visited[node] = max + 1
        return visited[node]
    }
}