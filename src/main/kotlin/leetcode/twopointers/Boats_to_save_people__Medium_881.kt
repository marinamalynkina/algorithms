package leetcode.twopointers

import java.util.*


fun main(args: Array<String>) {
    Medium_881_Boats_to_Save_People().run {
//        println(numRescueBoats(intArrayOf(1,2), 3))
//        println(numRescueBoats(intArrayOf(3,2,2,1), 3))
//        println(numRescueBoats(intArrayOf(3,5,3,4), 5))
        println(numRescueBoats(intArrayOf(21,40,16,24,30), 50))
    }
}

class Medium_881_Boats_to_Save_People {

    fun numRescueBoats(people: IntArray, limit: Int): Int {
        people.sortDescending()
        var countOfBusyBoats = 0
        val freeSpaceInBoats = TreeMap<Int,Int>() // key amount of space, value count of boats
        people.forEach { weight ->
            freeSpaceInBoats.ceilingEntry(weight)?.let { entry ->
                // when some of boats partly busy
                // change count of boats or remove, because if was second pearson in boat
                if (entry.value == 1) freeSpaceInBoats.remove(entry.key)
                else freeSpaceInBoats.put(entry.key, entry.value-1)

            } ?: run {
                val spaceLeft = limit - weight
                if (spaceLeft > 0) {
                    var count = 1
                    freeSpaceInBoats.get(spaceLeft)?.let {
                        count += it
                    }
                    freeSpaceInBoats.put(spaceLeft, count)
                }
                countOfBusyBoats++
            }

        }
        return countOfBusyBoats
    }

    fun numRescueBoatsForNPeopleinBoat(people: IntArray, limit: Int): Int {
        people.sortDescending()
        var countOfBusyBoats = 0
        val freeSpaceInBoats = TreeMap<Int,Int>() // key amount of space, value count of boats
        people.forEach { weight ->
            freeSpaceInBoats.ceilingEntry(weight)?.let { entry ->
                // when some of boats partly busy
                // change free space or remove
                val spaceLeft = entry.key - weight
                if (spaceLeft == 0) {
                    if (entry.value == 1) freeSpaceInBoats.remove(entry.key)
                    else freeSpaceInBoats.put(entry.key, entry.value-1)
                } else {
                    if (entry.value == 1) freeSpaceInBoats.remove(entry.key)
                    updateFreeSpaceInBoats(freeSpaceInBoats, spaceLeft)
                }

            } ?: run {
                val spaceLeft = limit - weight
                updateFreeSpaceInBoats(freeSpaceInBoats, spaceLeft)
                countOfBusyBoats++
            }

        }
        return countOfBusyBoats
    }

    private fun updateFreeSpaceInBoats(freeSpaceInBoats: TreeMap<Int,Int>, spaceLeft: Int) {
        if (spaceLeft > 0) {
            var count = 1
            freeSpaceInBoats.get(spaceLeft)?.let {
                count += it
            }
            freeSpaceInBoats.put(spaceLeft, count)
        }
    }
}