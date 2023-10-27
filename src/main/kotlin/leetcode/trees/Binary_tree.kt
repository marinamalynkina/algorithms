package trees

import java.util.LinkedList
import java.util.Stack

val root = BTNode<Int>(
    1,
    left = BTNode(2,
        left = BTNode(4),
        right = BTNode(5)
    ),
    right = BTNode(3,
        left = BTNode(6),
        right = BTNode(7)
    )
)

fun main(args: Array<String>) {
    printTreeInDepthRecursion(root)
    println()
    printTreeInDepthWhile(root)
    println()
    printTreeInBreadth(root)
}

/**
 * Stack traversal
 */
fun <T> printTreeInDepthRecursion(root: BTNode<T>?) {
    root?.run {
        if (v != null ) print(v)
        printTreeInDepthRecursion(left)
        printTreeInDepthRecursion(right)
    }
}

fun <T> printTreeInDepthWhile(root: BTNode<T>?) {
    if (root == null) return
    val stack = Stack<BTNode<T>>()
    stack.push(root)

    while (!stack.isEmpty()) {
        stack.pop().run {
            if (v != null) print(v)
            if (right != null) stack.push(right)
            if (left != null) stack.push(left)
        }
    }
}

fun <T> printTreeInBreadth(root: BTNode<T>?) {
    if (root == null) return
    val q = LinkedList<BTNode<T>>()
    q.add(root)

    while (q.isNotEmpty()) {
        q.poll().run {
            if (v != null) print(v)
            left?.let { q.add(it) }
            right?.let {q.add(it) }
        }
    }
}