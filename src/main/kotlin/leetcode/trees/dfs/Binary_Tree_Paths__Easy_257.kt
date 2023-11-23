package leetcode.trees.dfs

import leetcode.TreeNode

/**
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 *
 * A leaf is a node with no children.
 *
 * Input: root = [1,2,3,null,5]
 * Output: ["1->2->5","1->3"]
 *
 * Input: root = [1]
 * Output: ["1"]
 */
class Binary_Tree_Paths__Easy_257 {
    fun binaryTreePaths(root: TreeNode?): List<String> {
        if (root == null) return emptyList<String>()
        val resultPaths = mutableListOf<String>()
        preorder(root, "") {
            resultPaths.add(it)
        }
        return resultPaths

    }

    private fun preorder(root: TreeNode?, path: String, save: (String) -> Unit) {
        if (root == null) return
        if (root.isLeaf() == true) {
            save("${path}${root.`val`}")
            return
        }
        val newpath = "${path}${root.`val`}->"
        preorder(root.left, newpath, save)
        preorder(root.right, newpath, save)
    }

    private fun TreeNode.isLeaf() = left == null && right == null
}