package leetcode.trees

fun main(args: Array<String>) {
    Medium_T427_ConstructQuadTree().apply {
        construct(
            arrayOf(
                intArrayOf(0,1),
                intArrayOf(1,0)
            )
        )
    }
}
/**
 * Definition for a QuadTree node.
 * class Node(var `val`: Boolean, var isLeaf: Boolean) {
 *     var topLeft: Node? = null
 *     var topRight: Node? = null
 *     var bottomLeft: Node? = null
 *     var bottomRight: Node? = null
 * }
 */
class Medium_T427_ConstructQuadTree {
    fun construct(grid: Array<IntArray>): Node? {
        val gridSize = grid.size
        if (gridSize == 0) return null
        return quadTree(0, gridSize, 0, gridSize, grid)
    }

    fun quadTree(rowFrom: Int, rowTo: Int, columnFrom: Int, columnTo: Int, grid: Array<IntArray>): Node {
        val gridSize = rowTo-rowFrom

        if (gridSize == 1) {
            val value = grid[rowFrom][columnFrom]
            return Node(if (value == 1) true else false, true)
        } else {
            val topL = quadTree(rowFrom, rowTo-gridSize/2, columnFrom, columnTo-gridSize/2, grid)
            val topR = quadTree(rowFrom, rowTo-gridSize/2, columnFrom+gridSize/2, columnTo, grid)
            val bottomL = quadTree(rowFrom+gridSize/2, rowTo, columnFrom, columnTo-gridSize/2, grid)
            val bottomR = quadTree(rowFrom+gridSize/2, rowTo, columnFrom+gridSize/2, columnTo, grid)

            if (topL.isLeaf && topR.isLeaf && bottomL.isLeaf && bottomR.isLeaf &&
                topL.`val` == topR.`val` &&
                topR.`val` == bottomL.`val` &&
                bottomL.`val` == bottomR.`val`) {
                return Node(topL.`val`, true)
            } else {
                return Node(true, false).apply{
                    topLeft = topL
                    topRight = topR
                    bottomLeft = bottomL
                    bottomRight = bottomR
                }
            }
        }
    }
}

class Node(var `val`: Boolean, var isLeaf: Boolean) {
    var topLeft: Node? = null
    var topRight: Node? = null
    var bottomLeft: Node? = null
    var bottomRight: Node? = null
}
