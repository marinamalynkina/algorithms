package leetcode.stringsarrays


fun main(args: Array<String>) {
//    println(TheLatestTimeToCatchABus2().latestTimeCatchTheBus(
//        buses = intArrayOf(5,15),
//        passengers = intArrayOf(11,12,13,14,15),
//        capacity = 2
//    ))

    println(TheLatestTimeToCatchABus2().latestTimeCatchTheBus(
        buses = intArrayOf(10,20),
        passengers = intArrayOf(2,17,18,19),
        capacity = 2
    ))
}

class TheLatestTimeToCatchABus {
//    fun latestTimeCatchTheBus(buses: IntArray, passengers: IntArray, capacity: Int): Int {
//        buses.sort()
//        val qForEveryBus = TreeMap<Int, PriorityQueue<Int>>()
//        qForEveryBus.put(Int.MAX_VALUE, PriorityQueue<Int>())
//        buses.forEach { bus ->
//            qForEveryBus.put(bus, PriorityQueue<Int>{a, b -> b-a})
//        }
//
//        val goOnNext = PriorityQueue<Int>()
//        passengers.forEach { p ->
//            val key = qForEveryBus.ceilingKey(p)
//            val q = qForEveryBus[key]
//            if (q.size)
//            q!!.add(p)
//        }
//
//
//        return 0
//    }
    fun latestTimeCatchTheBus(buses: IntArray, passengers: IntArray, capacity: Int): Int {
        val sortedBuses = buses.sorted()
        val sortedPassengers = passengers.sorted()

        var nextBusIndex = 0
        var nextBus = sortedBuses[0]
        var curCapacity = 0
        var maxTime = 0
        var prevp = 0
        sortedPassengers.forEach { p ->
            curCapacity++
            if (prevp == 0 && p > nextBus) maxTime = nextBus
            else if (p-1 != prevp && curCapacity <= capacity) maxTime = p-1

            var resetCap = -1
            if (curCapacity == capacity) {
                resetCap = 0
            }
            if (p > nextBus) {
                resetCap = 1
            }

            if (resetCap != -1) {
                nextBusIndex++
                if (nextBusIndex < sortedBuses.size) {
                    nextBus = sortedBuses[nextBusIndex]
                    curCapacity = resetCap
                }
            }
            prevp = p
        }
        if (curCapacity < capacity) {
            maxTime = nextBus
            if (maxTime == prevp)
                maxTime = prevp-1
        }
        return maxTime
    }

}

class TheLatestTimeToCatchABus2 {
    fun latestTimeCatchTheBus(buses: IntArray, passengers: IntArray, capacity: Int): Int {
        buses.sort()
        passengers.sort()

        var maxTime = passengers[0]-1 // on case if only one passenger in capacity
        var prevp = passengers[0]
        var count = 0
        var ilastPassanger = -1
        buses.forEach { nexBus ->
            count = 0
            while (count < capacity
                && ilastPassanger+1 < passengers.size
                && passengers[ilastPassanger+1] <= nexBus ) {
                ilastPassanger++
                count++
                if (count <= capacity && ilastPassanger < passengers.size && passengers[ilastPassanger]-1 != prevp) {
                    maxTime = passengers[ilastPassanger]-1
                }
                if (ilastPassanger != 0) prevp = passengers[ilastPassanger]
            }

            // if bas is not full and last passenger didn't come in last minute
            if (count < capacity && prevp != nexBus) {
                maxTime = nexBus
            }

        }
        return maxTime
    }
}