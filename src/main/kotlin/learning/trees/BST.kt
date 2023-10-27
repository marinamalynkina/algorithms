package learning.trees


// Driver Code
fun main(args: Array<String>) {

    /* Let us create following BST
            50
         /     \
        30      70
       /  \    /  \
     20   40  60   80
 */
    var root: BST.node? = null

    // inserting value 50
    root = BST.insert(root, 50)

    // inserting value 30
    BST.insert(root, 30)

    // inserting value 20
    BST.insert(root, 20)

    // inserting value 40
    BST.insert(root, 40)

    // inserting value 70
    BST.insert(root, 70)

    // inserting value 60
    BST.insert(root, 60)

    // inserting value 80
    BST.insert(root, 80)

    // print the BST
    BST.inorder(root)
}

// Java program for Inserting a node
object BST {

    // Given Node
    class node {
        var key = 0
        var left: node? = null
        var right: node? = null
    }

    // Function to create a new BST node
    fun newNode(item: Int): node {
        val temp = node()
        temp.key = item
        temp.right = null
        temp.left = temp.right
        return temp
    }

    // Function to insert a new node with
    // given key in BST
    fun insert(node: node?, key: Int): node {
        // If the tree is empty, return a new node
        if (node == null) return newNode(key)

        // Otherwise, recur down the tree
        if (key < node.key) {
            node.left = insert(node.left, key)
        } else if (key > node.key) {
            node.right = insert(node.right, key)
        }

        // Return the node
        return node
    }

    // Function to do inorder traversal of BST
    fun inorder(root: node?) {
        if (root != null) {
            inorder(root.left)
            print(" " + root.key)
            inorder(root.right)
        }
    }

    // Function to do preorder traversal of BST
    fun preOrder(root: node?) {
        if (root != null) {
            print(root.key.toString() + " ")
            preOrder(root.left)
            preOrder(root.right)
        }
    }

    // Function to do postorder traversal of BST
    fun postOrder(root: node?) {
        if (root != null) {
            postOrder(root.left)
            postOrder(root.right)
            print(" " + root.key)
        }
    }

    fun height(node: node?): Int {
        return if (node == null) 0 else {

            // Compute the depth of each subtree
            val lDepth = height(node.left)
            val rDepth = height(node.right)

            // Use the larger one
            if (lDepth > rDepth) lDepth + 1 else rDepth + 1
        }
    }

    // Function that returns the node with minimum
    // key value found in that tree
    fun minValueNode(node: node?): node? {
        var current = node

        // Loop down to find the leftmost leaf
        while (current != null && current.left != null) current = current.left
        return current
    }

    // Function to get the total count of
    // nodes in a binary tree
    fun nodeSUM(node: node?): Int {
        return if (node == null) 0 else (nodeSUM(node.left)
                + nodeSUM(node.right) + 1)
    }
}