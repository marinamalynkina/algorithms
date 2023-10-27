package leetcode.matrix

class Island_perimeter__Easy_463 {
    fun islandPerimeter(grid: Array<IntArray>): Int {
        if (grid.size == 0) return 0
        grid.forEachIndexed { i, row ->
            row.forEachIndexed { j, cell ->
                if (cell == 1) {
                    return perimeterDFS(grid, i, j)
                }
            }
        }
        return 0
    }

    private val directions = arrayOf<IntArray>(
        intArrayOf(-1,0),
        intArrayOf(0,1),
        intArrayOf(1,0),
        intArrayOf(0,-1)
    )

    private fun perimeterDFS(grid: Array<IntArray>, i: Int, j: Int): Int {
        if (i !in grid.indices || j !in grid[0].indices || grid[i][j] == 0) return 1
        // required to not count everything in circle
        if (grid[i][j] == 2) return 0
        grid[i][j] = 2
        var sum = 0
        directions.forEach { direction ->
            sum += perimeterDFS(grid, i + direction[0], j + direction[1])
        }
        return sum
    }
}