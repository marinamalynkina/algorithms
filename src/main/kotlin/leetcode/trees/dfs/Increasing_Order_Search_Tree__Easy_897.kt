package leetcode.trees.dfs

import learning.trees.BST
import leetcode.TreeNode

fun main() {
    Increasing_Order_Search_Tree__Easy_897().apply {
//        println(TreeNode(212))
//        println(
//            increasingBST(TreeNode(5).apply {
//                left = TreeNode(3).apply {
//                    left = TreeNode(2).apply {
//                        left = TreeNode(1)
//                    }
//                    right = TreeNode(4)
//                }
//                right = TreeNode(6).apply {
//                    right = TreeNode(8).apply {
//                        left = TreeNode(7)
//                        right = TreeNode(9)
//                    }
//                }
//            })
//        )
        println(
            increasingBST(TreeNode(5).apply {
                left = TreeNode(1)
                right = TreeNode(7)
            })
        )

//        println(
//            increasingBST(TreeNode(4).apply {
//                left = TreeNode(1).apply {
//                    left = TreeNode(0)
//                    right = TreeNode(2).apply {
//                        right = TreeNode(3)
//                    }
//                }
//                right = TreeNode(6).apply {
//                    left = TreeNode(5)
//                    right = TreeNode(7).apply {
//                        right = TreeNode(8)
//                    }
//                }
//            })
//        )
    }
}

/**
 * Given the root of a binary search tree, rearrange the tree in in-order
 * so that the leftmost node in the tree is now the root of the tree,
 * and every node has no left child and only one right child.
 *    5      1
 *  /  \  =>  \
 * 1    7      5
 *              \
 *               7
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Increasing_Order_Search_Tree__Easy_897 {

    // doesn't work
    fun increasingBST(root: TreeNode?): TreeNode? {
        var minNode: TreeNode? = null
        val left = reorderTree(root?.left, false) {
            if (minNode == null) minNode = it
        }
        if (minNode == null) minNode = left
        left?.right = root
        root?.left = null
        root?.right = reorderTree(root?.right, true) {}

        return if (minNode == null) return root else minNode
    }

    // reorder tree during Inorder traversal
    fun reorderTree(root: TreeNode?, fromRight: Boolean, setupMinNode: (TreeNode) -> Unit): TreeNode?  {
        if (root?.isLeaf() == true || root == null) return root

        var current: TreeNode? = root
        reorderTree(root.left, fromRight, setupMinNode, )?.let {
            setupMinNode(it)
            it.right = root
            it.right?.left = null
            current = if (!fromRight) {
                it.right
            } else {
                it
            }
        } ?: run {
            setupMinNode(root)
        }
        reorderTree(root.right, fromRight, setupMinNode)?.let {
            root.right = it
            if (!fromRight) current = current?.right // adding fromRight was a bad solution. it doesn't work as i expected
        }

        return current
    }

    private fun TreeNode.isLeaf() = left == null && right == null


    var cur: TreeNode? = null

    fun increasingBST_v2(root: TreeNode?): TreeNode? {
        val emptyRoot = TreeNode(-1)
        cur = emptyRoot
        inorder(root)
        return emptyRoot.right
    }

    private fun inorder(root: TreeNode?) {
        if (root == null) return
        inorder(root.left)
        root.left = null
        cur?.right = root
        cur = root
        inorder(root.right)
    }
}