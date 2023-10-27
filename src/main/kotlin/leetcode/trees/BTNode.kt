package trees

import java.util.LinkedList

data class BTNode<T>(
    val v: T,
    var left: BTNode<T>? = null,
    var right: BTNode<T>? = null
)

data class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

class TreeCreator(val array: Array<Any?>) {

    init {
        toTree()
    }

    var root: TreeNode? = null

    fun toTree() {
        val q = LinkedList<TreeNode>()
        var v: Int? = null
        if (array.isNotEmpty()) {
            array[0].let {
                if (it != "") v = it as Int
            }
        }
        root = v?.let { TreeNode(it) }
        if (root == null) return
        q.add(root!!)


        var i = 1
        while (q.isNotEmpty()) {
            val node = q.poll()
            if (i < array.size){
                node.left = getElementFromArray(i)
                if (node.left != null) q.add(node.left!!)
                i++
                node.right = getElementFromArray(i)
                if (node.right != null) q.add(node.right!!)
                i++

            }
        }
    }

    private fun getElementFromArray(i: Int): TreeNode? {
        return if (i < array.size){
            array[i]?.let {
                if (it != "") TreeNode(it as Int) else null
            }
        } else null
    }

}