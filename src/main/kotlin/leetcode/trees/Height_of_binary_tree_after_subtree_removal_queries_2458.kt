package trees

import java.lang.Math.max

fun main(args: Array<String>) {
    val root = TreeNode(1,
        left = TreeNode(3, left = TreeNode(2)),
        right = TreeNode(4, left = TreeNode(6), right = TreeNode(5, right = TreeNode(7)))
        )
//    val root = TreeCreator(arrayOf<Any?>(8,6,37,3,7,33,65,1,4,"","",29,36,51,66,"",2,"",5,26,31,35,"",45,58,"","","","","","",22,28,30,32,34,"",44,47,55,59,21,25,27,"","","","","","","",41,"",46,48,54,56,"",62,13,"",24,"","","",38,42,"","","",49,53,"","",57,60,64,11,20,23,"","",39,"",43,"",50,52,"","","","",61,63,"",10,12,18,"","","","",40,"","","","","","","","","","",9,"","","",16,19,"","","","",15,17,"","",14)).root
    val s = ShortestSolution2458()
//    s.treeQueries(root, intArrayOf(57,7,32,55,20,25,45,34,5,19,45,30,48,1,47,32,60,31,22,15,31))
    s.treeQueries(root, intArrayOf(4))
}

/**
 * Runtime 1056 ms Beats 22.22%
 */
class Solution2458 {
    data class NodeInfo(
        val v: Int,
        val maxLevel: Int
    )

    val nodesLevel = hashMapOf<Int, Int>()
    val levels = mutableListOf<ArrayList<NodeInfo>>()

    fun treeQueries(root: TreeNode?, queries: IntArray): IntArray {
        if (root == null) return IntArray(0)
        getMaxLevel(root, 0)
        sortLevels()
        val result = IntArray(queries.size)
        for (i in 0..queries.size-1) {
            val q = queries[i]
            val level = nodesLevel.get(q)
            if (level != null) {
                val maxHeights = levels[level]
                val peek = maxHeights[0]
                when {
                    peek.v != q -> result[i] = peek.maxLevel
                    maxHeights.size >= 2 -> {
                        val second: NodeInfo = maxHeights[1]
                        if (second.v != q) result[i] = second.maxLevel
                    }
                    else -> result[i] = level-1
                }
            }
        }
        return result
    }

    fun getMaxLevel(root: TreeNode?, level: Int): Int {
        if (root == null) return level - 1
        nodesLevel.put(root.`val`, level)
        if (levels.size == level) {
            levels.add(level, arrayListOf<NodeInfo>())
        }
        val max = Math.max(getMaxLevel(root.left, level+1), getMaxLevel(root.right, level+1))
        levels[level].add(NodeInfo(root.`val`, max))
        return max
    }

    fun sortLevels() {
        for(i in 0..levels.size-1) {
            levels[i].sortByDescending { nodeInfo -> nodeInfo.maxLevel }
        }
    }
}

class ShortestSolution2458 {
    private var leftMax = 0
    private var rightMax = 0
    private val heightMap = mutableMapOf<Int, Int>()

    fun treeQueries(root: TreeNode?, queries: IntArray): IntArray {
        dfsLeft(root, 0)
        dfsRight(root, 0)
        return queries.map { heightMap[it]!! }.toIntArray()
    }

    private fun dfsLeft(root: TreeNode?, level: Int) {
        if (root == null) return
        heightMap[root.`val`] = leftMax
        leftMax = max(leftMax, level)
        dfsLeft(root.left, level + 1)
        dfsLeft(root.right, level + 1)
    }

    private fun dfsRight(root: TreeNode?, level: Int) {
        if (root == null) return
        heightMap[root.`val`] = max(rightMax, heightMap[root.`val`]!!)
        rightMax = max(rightMax, level)
        dfsRight(root.right, level + 1)
        dfsRight(root.left, level + 1)
    }
}