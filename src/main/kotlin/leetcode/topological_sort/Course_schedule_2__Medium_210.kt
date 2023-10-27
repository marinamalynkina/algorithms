package leetcode.topological_sort


class Course_schedule_2__Medium_210 {

    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val adjList = createAdjacencyList(numCourses, prerequisites)
        // use Kahn's algorithm
        if (adjList.filter { it.isEmpty() }.size == 0) return IntArray(0)

        val STATE_NONE = 0
        val STATE_INCYCLE = 1
        val STATE_VISITED = 2

        val state = IntArray(numCourses) { STATE_NONE }
        val result = IntArray(numCourses) {-1}
        var i = numCourses-1

        // return false if graph is cycled
        fun dfs(node: Int): Boolean {
            if (state[node] == STATE_VISITED) return true
            if (state[node] == STATE_INCYCLE) return false

            state[node] = STATE_INCYCLE
            adjList[node].forEach { child ->
                if (!dfs(child)) return false
            }
            state[node] = STATE_VISITED
            result[i--] = node
            return true
        }

        for (node in 0..numCourses-1) {
            if (!dfs(node)) return IntArray(0)
        }

        return result

    }

    private fun createAdjacencyList(numCourses: Int, prerequisites: Array<IntArray>): Array<MutableList<Int>> {
        return Array(numCourses){ mutableListOf<Int>()}.apply {
            prerequisites.forEach { edge ->
                this[edge[1]].add(edge[0])
            }
        }
    }
}