package trees

import java.util.*


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

/**
 * Making sub arrays from leaves
 */
fun levelOrder1(root: TreeNode?): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    if (root == null) return result
    val q = LinkedList<TreeNode>()
    q.add(root)
    result.add(listOf(root.`val`))

    while (q.isNotEmpty()) {
        q.poll().run {
            val list = mutableListOf<Int>()
            val leftV = left?.`val`
            if (leftV != null) {
                list.add(leftV)
                q.add(left!!)
            }
            val rightV = right?.`val`
            if (rightV != null) {
                list.add(rightV)
                q.add(right!!)
            }
            if (list.isNotEmpty()) result.add(list.toList())
        }
    }
    return result
}

/**
 * Runtime 219 ms Beats 63.50%
 * Memory 37.4 MB Beats 59.70%
 */
fun levelOrder(root: TreeNode?): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    if (root == null) return result
    val q = LinkedList<LinkedList<TreeNode>>()
    val firstLevel = LinkedList<TreeNode>()
    firstLevel.add(root)
    q.add(firstLevel)
    result.add(listOf(root.`val`))

    while (q.isNotEmpty()) {
        q.poll().let { level ->
            val levelNodes = mutableListOf<Int>()
            val levelQ = LinkedList<TreeNode>()
            while (level.isNotEmpty()) {
                level.poll().run {
                    val leftV = left?.`val`
                    if (leftV != null) {
                        levelNodes.add(leftV)
                        levelQ.add(left!!)
                    }
                    val rightV = right?.`val`
                    if (rightV != null) {
                        levelNodes.add(rightV)
                        levelQ.add(right!!)
                    }
                }
            }
            if (levelQ.isNotEmpty()) q.add(levelQ)
            if (levelNodes.isNotEmpty()) result.add(levelNodes.toList())
        }
    }
    return result
}


fun levelOrder3(root: TreeNode?): List<List<Int>> {
    val r: MutableList<List<Int>> = mutableListOf()
    if(root == null) return r
    val q: Deque<TreeNode> = LinkedList()
    q.add(root)
    while(q.isNotEmpty()) {
        val l = mutableListOf<Int>()
        var s = q.size
        while(s > 0) {
            val n = q.poll()
            l.add(n.`val`)
            if(n.left != null) q.add(n.left)
            if(n.right != null) q.add(n.right)
            s--
        }
        r.add(l)
    }
    return r
}

internal class Solution {
    var levels: MutableList<MutableList<Int>> = ArrayList()
    fun helper(node: TreeNode?, level: Int) {
        // start the current level
        if (levels.size == level) levels.add(ArrayList())

        // fulfil the current level
        levels[level].add(node!!.`val`)

        // process child nodes for the next level
        if (node.left != null) helper(node.left, level + 1)
        if (node.right != null) helper(node.right, level + 1)
    }

    fun levelOrder(root: TreeNode?): List<MutableList<Int>> {
        if (root == null) return levels
        helper(root, 0)
        return levels
    }
}