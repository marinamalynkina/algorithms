package leetcode.matrix

class Transform_matrix__Easy_867 {

    fun transpose(matrix: Array<IntArray>): Array<IntArray> {
        if (matrix.size == 0) return matrix
        val n = matrix.size
        val m = matrix[0].size
        val newMatrix = Array(m){i -> IntArray(n) }
        for(i in 0..matrix.lastIndex) {
            for (j in 0..matrix[i].lastIndex) {
                newMatrix[j][i] = matrix[i][j]
            }
        }
        return newMatrix
    }
}