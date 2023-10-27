package leetcode.graphs

import java.util.LinkedList

fun main(args: Array<String>) {
    val test1 = arrayOf(
        intArrayOf(0,1),
        intArrayOf(1,0)
    )

    val test2 = arrayOf(
        intArrayOf(0,1,0),
        intArrayOf(0,0,0),
        intArrayOf(0,0,1),

    )
//    println(Medium_T934_Shortest_Bridge().shortestBridge(test1))
    println(Medium_T934_Shortest_Bridge().shortestBridge(test2))
}

class Medium_T934_Shortest_Bridge {

    private val q = LinkedList<Position>()

    fun shortestBridge(grid: Array<IntArray>): Int {
        findLand(grid)?.let {
            locateWholeIsland(it, grid)
        }
        var distance = 0
        var countDown = q.size
        while (q.isNotEmpty()) {
            while (q.isNotEmpty() && countDown > 0) {
                val position = q.remove()
                countDown--
                getCellsAround(position, grid.size).forEach {
                    if ( grid[it.i][it.j] == 0) {
                        grid[it.i][it.j] = -1
                        q.add(it)
                    } else if (grid[it.i][it.j] == 1) {
                        return distance
                    }
                }
            }
            distance++
            countDown = q.size
        }

        return distance
    }

    fun findLand(grid: Array<IntArray>): Position? {
        for(i in 0..grid.size-1) {
            for(j in 0..grid[i].size-1) {
                if (grid[i][j] == 1) return Position(i,j)
            }
        }
        return null
    }

    fun locateWholeIsland(position: Position, grid: Array<IntArray>) {
        position.run {
            grid[i][j] = 2
        }
        q.add(position)
        getCellsAround(position, grid.size).forEach {
            if (grid[it.i][it.j] == 1) locateWholeIsland(it, grid)
        }

    }

    fun getCellsAround(p: Position, n: Int): Array<Position> {
        val array = mutableListOf<Position>()
        p.run {
            if (i > 0) array.add(Position(i-1, j))
            if (i < n-1) array.add(Position(i+1, j))
            if (j > 0) array.add(Position(i, j-1))
            if (j < n-1) array.add(Position(i, j+1))
        }
        return array.toTypedArray()
    }

    data class Position(
        val i: Int,
        val j: Int
    )
}