package learning.trees


internal object GFG {
    fun insert(root: Node?, x: Interval): Node {
        if (root == null) {
            return Node(x, x.high)
        }
        if (x.low < root.range.low) {
            root.left = insert(root.left, x)
        } else {
            root.right = insert(root.right, x)
        }
        if (root.max < x.high) {
            root.max = x.high
        }
        return root
    }

    fun inOrder(root: Node?) {
        if (root == null) {
            return
        }
        inOrder(root.left)
        print(root)
        inOrder(root.right)
    }

    fun isOverlapping(
        root: Node?,
        x: Interval
    ): Interval {
        if (root == null) {
            // return a dummy interval range
            return Interval(-1, -1)
        }
        // if x overlaps with root's interval
        if ((x.low > root.range.low
                    && x.low < root.range.high)
            || (x.high > root.range.low
                    && x.high < root.range.high)
        ) {
            return root.range
        } else return if ((root.left != null
                    && root.left!!.max > x.low)
        ) {
            // the overlapping node may be present in left
            // child
            isOverlapping(root.left, x)
        } else {
            isOverlapping(root.right, x)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var root: Node = insert(null, Interval(15, 20))
        root = insert(root, Interval(10, 30))
        root = insert(root, Interval(17, 19))
        root = insert(root, Interval(5, 20))
        root = insert(root, Interval(12, 15))
        root = insert(root, Interval(30, 40))
        println(
            "Inorder traversal of constructed Interval Tree is"
        )
        inOrder(root)
        println()
        val i = Interval(6, 7)
        println("Searching for interval $i")
        println(
            "Overlaps with "
                    + isOverlapping(root, Interval(6, 7))
        )
    } // contributed by rishabhtiwari759

    internal class Interval(var low: Int, var high: Int) {
        override fun toString(): String {
            return "[" + low + "," + high + "]"
        }
    }

    internal class Node(var range: Interval, var max: Int) {
        var left: Node? = null
        var right: Node? = null
        override fun toString(): String {
            return ("[" + range.low + ", "
                    + range.high + "] "
                    + "max = " + max + "\n")
        }
    }
}