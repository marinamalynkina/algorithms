package leetcode.trees.dfs

import leetcode.TreeNode
import kotlin.math.max

fun main() {
    Diameter_of_Binary_Tree__Easy_543().apply {
        println(diameterOfBinaryTree(TreeNode(1).apply {
            left = TreeNode(2).apply {
                left = TreeNode(4)
                right = TreeNode(5)
            }
            right = TreeNode(3)
        }))
        println(diameterOfBinaryTree(TreeNode(1).apply {
            left = TreeNode(2)
        }))
    }
}

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Example 2:
 *
 * Input: root = [1,2]
 * Output: 1
 *
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Diameter_of_Binary_Tree__Easy_543 {

    fun diameterOfBinaryTree(root: TreeNode?): Int {
        var maxDiameter = 0
        dfsHeight(root) { leftHeight, rightHeight ->
            maxDiameter = max(maxDiameter, leftHeight + rightHeight)
        }
        return maxDiameter
    }

    private fun dfsHeight(
        root: TreeNode?,
        extraActionsWithHeights: (left: Int, right: Int) -> Unit
    ): Int {
        if (root?.left == null && root?.right == null) return 0
        val leftHeight = dfsHeight(root.left, extraActionsWithHeights) + (if (root.left == null) 0 else 1)
        val rightHeight = dfsHeight(root.right, extraActionsWithHeights) + (if (root.right == null) 0 else 1)

        // add leftHeight and rightHeight and check if it more then current maxDiameter
        extraActionsWithHeights(leftHeight, rightHeight)

        // need to return max height between leftHeight and rightHeight because thi
        return max(leftHeight, rightHeight)
    }
}