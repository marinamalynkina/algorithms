package leetcode.topological_sort

class Sequence_reconstruction__Medium_444 {

//    fun sequenceReconstruction(nums: IntArray, sequences: List<List<Int>>): Boolean {
//        val nodes = mutableSetOf<Int>()
//        fun createAdjacencyList(n: Int, sequences: List<List<Int>>): Array<MutableList<Int>?> {
//            val adj = Array<MutableList<Int>?>(n) { mutableListOf() }
//            sequences.forEach { edge ->
//                val from = edge[0]
//                val to = edge[1]
//                adj[to]!!.add(from)
//                nodes.add(to)
//                nodes.add(from)
//            }
//            return adj
//        }
//
//        val adj = createAdjacencyList(nums.size, sequences)
//
//        if (nodes.size < nums.size) return false
//
//
//
//    }
//
//
//
//    fun isOnlyShortestSuperSeq_BFS(nums: IntArray, adj: Array<MutableList<Int>>): Boolean {
//        val q = LinkedList<Int>()
//
//        // find root
//        adj.forEachIndexed { index, nodes ->
//            if (nodes.isEmpty()) q.add(index)
//        }
//        var iSeq = 0
//        while (q.isNotEmpty()) {
//            if (q.size > 1) return false
//            val node = q.remove()
//            if (nums[iSeq] != node) return false
//            adj[node].forEachIndexed { index, i ->
//
//            }
//        }
//    }
}