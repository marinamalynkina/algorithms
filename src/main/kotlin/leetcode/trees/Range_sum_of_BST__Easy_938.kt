package leetcode.trees

import trees.TreeNode

fun main(args: Array<String>) {

}

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
class Solution {
    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
        if (root == null) return 0

        var sum = 0
        if (low < root.`val`) sum += rangeSumBST(root.left, low, high)
        if (high > root.`val`) sum += rangeSumBST(root.right, low, high)

        if (root.`val` in low..high) sum += root.`val`
        return sum
    }
}