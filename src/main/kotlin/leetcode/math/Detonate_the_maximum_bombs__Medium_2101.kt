package leetcode.math

import java.math.BigInteger
import java.util.LinkedList

fun main(args: Array<String>) {

    val test1 = arrayOf(
        intArrayOf(1,2,3),
        intArrayOf(2,3,1),
        intArrayOf(3,4,2),
        intArrayOf(4,5,3),
        intArrayOf(5,6,4),
    )

//    Medium_T2101_DetonateTheMaximumBombs().maximumDetonation(test1)

//    println(SolutionFastest().maximumDetonation(test1))
    println(Solution2().maximumDetonation(test1))

//    println(Medium_T2101_DetonateTheMaximumBombs().maximumDetonation(
//        arrayOf(
//            intArrayOf(1, 1, 100000),
//            intArrayOf(100000, 100000, 1)
//        )
//    ))

//    println(Medium_T2101_DetonateTheMaximumBombs().maximumDetonation(
//        arrayOf(
//            intArrayOf(38496,37528,4845),
//            intArrayOf(46272,98187,1365),
//            intArrayOf(70550,7578,3223),
//            intArrayOf(77200,18005,7272),
//            intArrayOf(7648,58155,7628),
//            intArrayOf(95708,33470,1889),
//            intArrayOf(20157,92266,9823),
//            intArrayOf(52803,2765,6751),
//            intArrayOf(50429,63049,3002),
//            intArrayOf(72582,69729,2281),
//            intArrayOf(49317,35327,1922),
//            intArrayOf(715,8902,9620),
//            intArrayOf(21154,58349,8544),
//            intArrayOf(43935,46296,6868),
//            intArrayOf(7881,24144,2372),
//            intArrayOf(95258,97730,6554),
//            intArrayOf(5525,56971,9191),
//            intArrayOf(95762,81415,2027),
//            intArrayOf(62518,75469,1330),
//            intArrayOf(83660,4341,6817),
//            intArrayOf(30268,38781,8309),
//            intArrayOf(97922,20474,4047),
//            intArrayOf(39466,40057,2061),
//            intArrayOf(91983,24242,5451),
//            intArrayOf(92380,31509,8446),
//            intArrayOf(12436,8897,5279),
//            intArrayOf(28386,8556,4702),
//            intArrayOf(54672,88180,1106),
//            intArrayOf(17843,95337,4420),
//            intArrayOf(21956,49924,1839)
//
//        )
//    ))
}

/**
 * Runtime 420 ms Beats 65.69%
 */
class Medium_T2101_DetonateTheMaximumBombs {
    fun maximumDetonation(bombs: Array<IntArray>): Int {
        var max = 1
        val connection = getConnections(bombs)
        for (i in 0..connection.size-1) {
            val set = mutableSetOf<Int>()
            set.add(i)
            val q = LinkedList<Int>()
            q.add(i)
            while (q.isNotEmpty()) {
                val node = q.poll()
                connection[node].forEach {
                    val setSize = set.size
                    set.add(it)
                    if (set.size > 4) {
                        println("node=${node} edge =$it")
                    }
                    if (setSize != set.size) {
                        max = Math.max(max,set.size)
                        q.add(it)
                    }
                }
            }
            if (max == bombs.size) return max
        }

        return max
    }

    private fun getConnections(bombs: Array<IntArray>): Array<MutableList<Int>> {
        val connections = Array(bombs.size) { mutableListOf<Int>() }
        for (i in 0..bombs.size-1) {
            val bombi = bombs[i]
            for (j in i..bombs.size-1) {
                if (i==j) continue
                val bombj = bombs[j]
                val a = BigInteger.valueOf(Math.abs(bombi[0] - bombj[0])*1L)
                val b = BigInteger.valueOf(Math.abs(bombi[1] - bombj[1])*1L)
                val c2 = a.multiply(a) + b.multiply(b)
                val ri = BigInteger.valueOf(bombi[2]*1L)
                if (c2 <= ri.multiply(ri)) {
                    connections[i].add(j)
                }
                val rj = BigInteger.valueOf(bombj[2]*1L)
                if (c2 <= rj.multiply(rj)) {
                    connections[j].add(i)
                }
            }
        }
        return connections
    }
}


class SolutionFastest {
    fun maximumDetonation(bombs: Array<IntArray>): Int {
        if(bombs.size==1) return 1
        val graph = Array(bombs.size){BooleanArray(bombs.size)}
        for(i in bombs.indices){
            for(j in i+1 until bombs.size){
                val dist = (bombs[i][0]-bombs[j][0]).toLong()*(bombs[i][0]-bombs[j][0])+(bombs[i][1]-bombs[j][1]).toLong()*(bombs[i][1]-bombs[j][1])
                graph[i][j] = dist<= bombs[i][2].toLong()*bombs[i][2]
                graph[j][i] = dist<= bombs[j][2].toLong()*bombs[j][2]
            }
        }

        // array of indexes of sorted by radius bombs
        // make sense to start form the biggest radius
        val w = bombs.indices.sortedBy{-bombs[it][2]}
        val s = BooleanArray(graph.size)

        var max = 0
        for(i in w){
            if(s[i]) continue
            s[i] = true
            val q = mutableListOf(i)

            //allow to mark every elemts that has been seen without neccesity to check if it element already in list.
            //replacuing my varian with `set` nad checcking size
            val seen = BooleanArray(graph.size)
            seen[i] = true
            var cnt = 0
            while(q.isNotEmpty()){
                val cur = q.removeAt(0)
                cnt++
                for(j in graph[0].indices){
                    if(graph[cur][j] && !seen[j]){
                        seen[j] = true
                        s[j] = true
                        q+=j
                    }
                }
            }
            max = max.coerceAtLeast(cnt)
        }
        return max
    }
}

class Solution2 {
    fun maximumDetonation(bombs: Array<IntArray>): Int {
        val theBombs = bombs.mapIndexed { idx, it -> Bomb(it[0], it[1], it[2], idx) }

        val detonatedBombs = theBombs.map { currBomb ->
            val alreadyDetonated = mutableSetOf<Bomb>(currBomb)

            fun detonateBomb(detonatedBomb: Bomb) {
                theBombs.forEach { possibleBomb ->
                    if (
                        !alreadyDetonated.contains(possibleBomb)
                        && detonatedBomb != possibleBomb
                        && detonatedBomb.withinRange(possibleBomb)
                    ) {
                        alreadyDetonated.add(possibleBomb)
                        detonateBomb(possibleBomb)
                    }
                }
            }

            detonateBomb(currBomb)
            alreadyDetonated
        }
        return detonatedBombs.map { it.size }!!.max()!!
    }
}



data class Bomb(
    val x: Int,
    val y: Int,
    val blast: Int,
    val id: Int
) {

    fun withinRange(bomb: Bomb): Boolean {
        val squaredSides =
            Math.pow((this.x - bomb.x).toDouble(), 2.0) +
                    Math.pow((this.y - bomb.y).toDouble(), 2.0)

        val hyp = Math.pow(squaredSides, 0.5) // корень??

        return hyp <= blast
    }
}