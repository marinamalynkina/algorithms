package trees

fun main(args: Array<String>) {
    val root = TreeCreator(arrayOf(1,2,3,"","","",4)).root
    T1110_DeleteNodesAndReturnForest2().delNodes(root, intArrayOf(2,1))
}

/**
 * Runtime 252 ms Beats 55.56%
 * Memory 37.7 MB Beats 88.89%
 */
class T1110_DeleteNodesAndReturnForest {

    val parentMap = hashMapOf<Int, TreeNode?>()

    fun delNodes(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {
        if (root == null) return emptyList()
        val result = hashMapOf<Int, TreeNode?>()
        result.put(root.`val`, root)
        setupHashMap(root, null)
        for (todeleteItem in to_delete) {
            if (result.contains(todeleteItem)) result.remove(todeleteItem)
            val parent = parentMap[todeleteItem]
            var nodeToDelete: TreeNode? = null
            if (parent?.left?.`val` == todeleteItem) {
                nodeToDelete = parent.left
                parent.left = null
            } else if (parent?.right?.`val` == todeleteItem) {
                nodeToDelete = parent.right
                parent.right = null
            } else if (parent == null && todeleteItem == root.`val`) {
                nodeToDelete = root
            }
            parentMap.remove(todeleteItem)
            nodeToDelete?.left?.run {
                result.put(`val`, this)
            }
            nodeToDelete?.right?.run {
                result.put(`val`, this)
            }
        }
        return result.values.toList()
    }

    fun setupHashMap(node: TreeNode?, parent: TreeNode?) {
        if (node == null) return
        parentMap.put(node.`val`, parent)
        setupHashMap(node.left, node)
        setupHashMap(node.right, node)
    }
}

class T1110_DeleteNodesAndReturnForest2 {

    val toDelete = hashSetOf<Int>()
    val result = mutableListOf<TreeNode>()

    fun delNodes(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {
        toDelete.addAll(to_delete.toTypedArray())
        deleteNode(root, true)
        return result
    }

    fun deleteNode(node: TreeNode?, isRoot: Boolean): TreeNode? {
        if (node == null) return null

        val delete = toDelete.contains(node.`val`)
        if (!delete && isRoot) {
            result.add(node)
        }
        node.left = deleteNode(node.left, delete)
        node.right = deleteNode(node.right, delete)
        return if (delete) null else node
    }
}