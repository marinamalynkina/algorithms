package leetcode.bites


fun main(args: Array<String>) {
//    println(Hard_1803_CountPairsWithXORInARange2().countPairs(intArrayOf(1,4,2,7),2,6))
    println(Solution2().countPairs(intArrayOf(1,4,2,7),2,6))
}

class Hard_1803_CountPairsWithXORInARange {
    fun countPairs(nums: IntArray, low: Int, high: Int): Int {
        val n = nums.size
        var count = 0
        for (i in 0..n-2) {
            for (j in i+1..n-1) {
                if (nums[i].xor(nums[j]) in low..high) {
                    count++
                }
            }
        }
        return count
    }
}

class Hard_1803_CountPairsWithXORInARange2 {

    val maxpov = 3

    fun countPairs1(nums: IntArray, low: Int, high: Int): Int {
        val root = Node()
        var count = 0
        nums.forEach { num ->
            val countHigh = countSmaller(num, high+1, root)
            val countLow = countSmaller(num, low, root)
            count += (countHigh-countLow)
            insertNode(num, root)
        }
        return count
    }

    fun countPairs2(nums: IntArray, low: Int, high: Int): Int {
        val root = Node()
        nums.forEach { num ->
            insertNode(num, root)
        }
        var count = 0
        nums.forEach { num ->
            val countHigh = countSmaller(num, high+1, root)
            val countLow = countSmaller(num, low, root)
            count += (countHigh-countLow)
        }
        return count/2
    }

    fun insertNode(num: Int, root: Node) {
        var cur = root
        for(i in maxpov downTo 0) {
            val bitOnPositionI = (num shr i) and 1
            if (cur.children[bitOnPositionI] == null) {
                cur.children[bitOnPositionI] = Node()
            }
            cur.children[bitOnPositionI]!!.count += 1
            cur = cur.children[bitOnPositionI]!!
        }
    }

    fun countSmaller(num: Int, limit: Int, root: Node): Int {
        var cur: Node? = root
        var count = 0

        for (i in maxpov downTo 0) {
            if (cur == null) return count
            val numBit = (num shr i) and 1
            val limitBit = (limit shr i) and 1
            if (limitBit == 1) {
                cur.children.get(numBit)?.let {
                    count += it.count
                }
                cur = cur.children.get(numBit xor 1)
            } else {
                cur = cur.children.get(numBit)
            }
        }

        return count
    }

    data class Node(
        var count: Int = 0,
        val children: Array<Node?> = arrayOfNulls(2)
    )
}


//Time Complexity O(n)
internal class Solution2 {

    fun countPairs(A: IntArray, low: Int, high: Int): Int {
        return test(A, high + 1) - test(A, low)
    }

    private fun test(A: IntArray, x: Int): Int {
        println("Start test limit=${x} = ${Integer.toBinaryString(x)}")
        var x = x
        var count: MutableMap<Int, Int> = HashMap()
        var count2: MutableMap<Int, Int> = HashMap()
        for (a in A) count[a] = count.getOrDefault(a, 0) + 1
        var res = 0
        while (x > 0) {
            println("limit=${x} = ${Integer.toBinaryString(x)}")
            for (k in count.keys) {
                val v = count[k]!!
                println("key = $k value = $v = ${Integer.toBinaryString(v)} (k shr 1)=${k shr 1} = ${Integer.toBinaryString(k shr 1)}")
                println("count2[k shr 1]= ${count2.getOrDefault(k shr 1, 0)}")
                count2[k shr 1] = count2.getOrDefault(k shr 1, 0) + v
                println("count2[k shr 1]+v = ${count2[k shr 1]}")
                if (x and 1 > 0) {
                    println("(limit - 1) xor k = ${Integer.toBinaryString((x - 1))} ^ ${Integer.toBinaryString(k)}  = ${Integer.toBinaryString((x - 1) xor k)}")
                    val c = count.getOrDefault((x - 1) xor k, 0)
                    println("last bit of (limit = 1), c = $c | v * c = ${v * c}")
                    res += v * c
                    println("res = ${res}")
                }
                println("----------")
            }
            count = count2
            count2 = HashMap()
            x = x shr 1
        }
        return res / 2
    }
}