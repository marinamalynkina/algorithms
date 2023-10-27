package leetcode.matrix

class Number_of_islands__Medium_200 {

    fun numIslands(grid: Array<CharArray>): Int {
        if (grid.size == 0) return 0
        var numIslands = 0
        grid.forEachIndexed { i, chars ->
            chars.forEachIndexed { j, cell ->
                if (cell == '1') {
                    numIslands++
                    matrixDFS(grid, i, j)
                }
            }
        }
        return numIslands
    }

    val direction = arrayOf<IntArray>(intArrayOf(-1,0), intArrayOf(0,1),intArrayOf(1,0),intArrayOf(0,-1))

    private fun matrixDFS(grid: Array<CharArray>, i: Int, j: Int) {
        if (i !in grid.indices || j !in grid[0].indices || grid[i][j] == '0') {
            return
        }
        grid[i][j] = '0'
        direction.forEach { direction ->
            matrixDFS(grid, i + direction[0], j + direction[1])
        }

    }
}