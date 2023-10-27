package leetcode.dp


fun main(args: Array<String>) {
    Hard_2088_CountFertilePyromidsInALand().apply {
//        println(countPyramids(arrayOf(intArrayOf(0,1,1,0), intArrayOf(1,1,1,1))))
//        println(countPyramids(arrayOf(intArrayOf(1,1,1), intArrayOf(1,1,1))))
        println(countPyramids(arrayOf(intArrayOf(1,1,1,1,0), intArrayOf(1,1,1,1,1), intArrayOf(1,1,1,1,1), intArrayOf(0,1,0,0,1))))
    }
}

class Hard_2088_CountFertilePyromidsInALand {

    fun countPyramids(grid: Array<IntArray>): Int {
        var count = 0
        val rows = grid.size
        if (rows == 0) return 0
        val col = grid[0].size
        val copyGrid = Array(grid.size){ i: Int -> grid[i].clone() }
        for(i in rows-2 downTo 0) {
            for (j in 1..col-2) {
                if (grid[i][j] == 1) {
                    count += java.lang.Math.max((isPyramid(grid, i, j, 2, false)-1), 0)
                }
            }
        }

        for(i in 1..rows-1) {
            for (j in 1..col-2) {
                if (copyGrid[i][j] == 1) {

                    count += java.lang.Math.max((isPyramid(copyGrid, i, j, 2, true)-1),0)
                }
            }
        }
        return count
    }

    fun isPyramid(grid: Array<IntArray>, r: Int, c: Int, h: Int = 2, inverse: Boolean): Int {
        var isPyromid = false
        var min = Integer.MAX_VALUE
        if (inverse) {
            val i = r-1

            if (i in (r-h+1)..r) {
                for (j in (c - (r - i))..(c + (r - i))) {
                    if (grid[i][j] >= 1) {
                        min = java.lang.Math.min(min, grid[i][j])
                        isPyromid = true
                    } else {
                        isPyromid = false
                        break
                    }
                }
            }
        } else {
            val i = r+1

            if (i in r..(r+h-1) && i < grid.size) {
                for (j in (c-(i-r))..(c+(i-r))) {
                    if (grid[i][j] >= 1) {
                        min = java.lang.Math.min(min, grid[i][j])
                        isPyromid = true
                    } else {
                        isPyromid = false
                        break
                    }
                }
            }
        }

        if (isPyromid == true) {
            grid[r][c] = min+1
        }

        return if (isPyromid) {
            grid[r][c]
        } else {
            0
        }

    }
}