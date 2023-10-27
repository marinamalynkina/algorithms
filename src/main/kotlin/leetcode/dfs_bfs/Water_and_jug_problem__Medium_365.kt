package leetcode.dfs_bfs

fun main(args: Array<String>) {
    Water_and_jug_problem_Medium_365().apply {
//        println(canMeasureWater(3, 5, 4))
        println(canMeasureWater(2, 6, 5))
        println(canMeasureWater(1, 2, 3))
        println(canMeasureWater(34, 5, 6))
    }
}

class Water_and_jug_problem_Medium_365 {

    fun canMeasureWater(jug1Capacity: Int, `jug2Capacity`: Int, targetCapacity: Int): Boolean {
        var diff1 = 0
        var diff2 = 0
        if (jug1Capacity > `jug2Capacity`) {
            diff1 = jug1Capacity%`jug2Capacity`
            diff2 = `jug2Capacity` - diff1
        } else {
            diff1 = `jug2Capacity`%jug1Capacity
            diff2 = jug1Capacity - diff1
        }
        if (diff2 < 0) diff2 *= -1
        if (diff2 == 0) diff2 = diff1
        if (diff1 == 0) diff1 = `jug2Capacity`

        val set = setOf(jug1Capacity, jug2Capacity, diff1, diff2)

        return fit(targetCapacity, set.toList().sorted())
    }

    fun fit(rest: Int, list: List<Int>): Boolean {
        list.forEachIndexed { i, jug ->
            if (rest < jug) return false
            val newRest = rest%jug
            return if (newRest == 0) true
            else {
                fit(newRest,
                    mutableListOf<Int>().apply {
                        addAll(list)
                        removeAt(i)
                    }
                )
            }
        }
        return false
    }
}