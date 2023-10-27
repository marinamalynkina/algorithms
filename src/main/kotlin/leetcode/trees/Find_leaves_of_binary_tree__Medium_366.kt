package leetcode.trees

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
class Medium_T366_FindLeavesOfBinaryTree {

    val leaves = mutableListOf<MutableList<Int>>()

    fun findLeaves(root: TreeNode?): List<List<Int>> {
        dfs(root)
        return leaves
    }

    fun dfs(root: TreeNode?): Int {
        if (root == null) return -1
        val hl = dfs(root.left)
        val hr = dfs(root.right)

        val leavesLevel = java.lang.Math.max(hl, hr) + 1
        if (leaves.size <= leavesLevel) {
            leaves.add(mutableListOf<Int>())
        }
        leaves[leavesLevel].add(root.`val`)
        return leavesLevel
    }
}