package leetcode.trees.dfs

import leetcode.TreeNode
import java.util.LinkedList

fun main() {
    Two_Sum_IV_Input_is_a_BST__Easy_653().apply {
        println(findTarget(
            TreeNode(5).apply {
                left = TreeNode(3).apply {
                    left = TreeNode(2)
                    right = TreeNode(4)
                }
                right = TreeNode(6).apply {
                    right = TreeNode(7)
                }
            }, 9
        ))
    }
}
/**
 * Given the root of a binary search tree and an integer k,
 * return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.
 *
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 *
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 */
class Two_Sum_IV_Input_is_a_BST__Easy_653 {
    fun findTarget(root: TreeNode?, k: Int): Boolean {
        val searchValues = HashSet<Int>()
        var hasSum = false
        bfs(root) { value ->
            if (searchValues.contains(value)) {
                hasSum = true
                return@bfs false
            } else searchValues.add(k - value)
            return@bfs true
        }
        return hasSum
    }

    private fun bfs(root: TreeNode?, needContinue:(Int) -> Boolean) {
        if (root == null) return
        val stack = LinkedList<TreeNode>()
        stack.add(root)
        while(stack.isNotEmpty()) {
            val node = stack.pop()
            if (!needContinue(node.`val`)) return
            addLeafsInStack(stack, node)
        }
    }

    private fun addLeafsInStack(stack: LinkedList<TreeNode>, node: TreeNode) {
        if (node.left != null) stack.add(node.left!!)
        if (node.right != null) stack.add(node.right!!)
    }
}