package leetcode.trees.dfs

import leetcode.TreeNode

fun main() {
    Binary_Tree_Postorder_Traversal__Easy_145().apply {
        /**
         * Issue on test
         * root = [3,1,2]
         * Output   [2,1,3]
         * Expected [1,2,3]
         */
    }
}

/**
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 *
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [1]
 * Output: [1]
 */
class Binary_Tree_Postorder_Traversal__Easy_145 {
    fun postorderTraversal(root: TreeNode?): List<Int> {
        val resultList = mutableListOf<Int>()
        inversePostorder(root) {
            resultList.add(it.`val`)
        }
        return resultList
    }

    private fun inversePostorder(root: TreeNode?, action: (TreeNode) -> Unit) {
        if (root == null) return
        inversePostorder(root.right, action)
        inversePostorder(root.left, action)
        action(root)
    }
}