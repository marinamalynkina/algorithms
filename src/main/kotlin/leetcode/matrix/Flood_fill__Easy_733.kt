package leetcode.matrix

fun main() {
    Easy_733_floodFill().floodFill(
        arrayOf(
            intArrayOf(1,1,1),
            intArrayOf(1,1,0),
            intArrayOf(1,0,1),
        ),
        1,1,2)
}

class Easy_733_floodFill {

    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        if (image.size == 0) return image
        if (sr !in image.indices || sc !in image[0].indices) return image
        val initialColor = image[sr][sc]
        if (initialColor == color) return image
        floodFillDFS(image, sr, sc, initialColor, color)
        return image
    }

    private fun floodFillDFS(image: Array<IntArray>, sr: Int, sc: Int, initialColor: Int, newcolor: Int) {
        if (sr !in image.indices || sc !in image[0].indices || image[sr][sc] != initialColor) {
            return
        }
        image[sr][sc] = newcolor
        directions.forEach { direction ->
            floodFillDFS(image, sr+direction[0], sc+direction[1], initialColor, newcolor)
        }
    }

    private val directions = arrayOf<IntArray>(
        intArrayOf(-1,0),
        intArrayOf(0,1),
        intArrayOf(1,0),
        intArrayOf(0,-1)
    )
}