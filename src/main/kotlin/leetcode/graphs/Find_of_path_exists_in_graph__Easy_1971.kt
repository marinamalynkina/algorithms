package leetcode.graphs

import java.util.*

fun main(args: Array<String>) {

}

/**
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
 * The edges in the graph are represented as a 2D integer array edges,
 * where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi.
 * Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 *
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 *
 * Given edges and the integers n, source, and destination,
 * return true if there is a valid path from source to destination,
 * or false otherwise.
 *
 * Example 1:
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 *
 * Example 2:
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 */
class Easy_T1971_FindOfPathExistsInGraph {
    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        if (n == 1 && source == destination && source == 0) return true
        val connections = Array(n){ mutableSetOf<Int>() }
        for(i in edges.indices) {
            val edge = edges[i]
            connections[edge[0]].add(edge[1])
            connections[edge[1]].add(edge[0])
        }
        val result = false
        val q = LinkedList<Int>()
        q.add(source)
        val visited = BooleanArray(n)
        visited[source] = true
        while(q.isNotEmpty()) {
            val children = connections[q.poll()]
            children.forEach { child ->
                if (child == destination) return true
                if (!visited[child]) {
                    visited[child] = true
                    q.add(child)
                }
            }
        }
        return result
    }
}

class FastestSolution {
    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val dSet = DisjointSet(n, edges)
        return dSet.areConnected(source, destination)
    }
}

class DisjointSet(n: Int, edges: Array<IntArray>) {
    private val parent = IntArray(n) { it }

    init {
        for (edge in edges) {
            union(edge[0], edge[1])
        }
    }

    public fun areConnected(u: Int, v: Int): Boolean {
        return find(u) == find(v);
    }

    public fun find(u: Int): Int {
        var x = u
        while (x != parent[x]) {
            x = parent[x]
        }
        parent[u] = x
        return x
    }

    public fun union(u: Int, v: Int) {
        var a = find(u)
        var b = find(v)
        parent[a] = b
    }
}