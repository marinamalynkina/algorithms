package learning.trees

class Node(var data: Int) {
    var left: Node? = null
    var right: Node? = null
    var parent: Node? = null
    var color = false
}