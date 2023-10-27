package leetcode.segment_trees

fun main(args: Array<String>) {
    Medium_307_RangeSumQuery_Mutable(intArrayOf(2,4,5,7))
}


class Medium_307_RangeSumQuery_Mutable(nums: IntArray) {
    lateinit var tree: IntArray
    var n = 0

    init {
        n = nums.size
        tree = IntArray(n*2)
        buildTree(nums)
    }

    private fun buildTree(nums: IntArray) {
        run {
            var i = n
            var j = 0
            while (i < 2 * n) {
                tree[i] = nums[j]
                i++
                j++
            }
        }
        for (i in n - 1 downTo 1) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1]
        }
    }

    fun updateTree(pos: Int, v: Int) {
        val treePos = pos + n
        tree[treePos] = v
        var nodePos = treePos/2
        while (nodePos > 0) {
            tree[nodePos] = tree[nodePos * 2] + tree[nodePos * 2 + 1]
            nodePos /= 2
        }
    }

    fun sumRange(_l: Int, _r: Int): Int {
        // get leaf with value 'l'
        var l = _l
        var r = _r
        l += n
        // get leaf with value 'r'
        r += n
        var sum = 0
        while (l <= r) {
            if (isRightNode(l)) {
                sum += tree[l]
                l++
            }
            if (isLeftNode(r)) {
                sum += tree[r]
                r--
            }
            l /= 2
            r /= 2
        }
        return sum
    }

    private fun isLeftNode(i: Int) = i % 2 == 0
    private fun isRightNode(i: Int) = i % 2 == 1



}