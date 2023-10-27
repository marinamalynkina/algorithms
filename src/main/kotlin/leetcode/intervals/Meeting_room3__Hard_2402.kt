package leetcode.intervals

import java.util.*

fun main(args: Array<String>) {

//    val pq = PriorityQueue<Int>(compareBy {  })
//    println(MySolution3().mostBooked(2, arrayOf(
//        intArrayOf(0,10),
//        intArrayOf(1,5),
//        intArrayOf(2,7),
//        intArrayOf(3,4)
//    )))
    println(MySolution3().mostBooked(4, arrayOf(
        intArrayOf(18,19),
        intArrayOf(3,12),
        intArrayOf(17,19),
        intArrayOf(2,13),
        intArrayOf(7,10)
    )))
//    println(mostBooked(3, arrayOf(
//        intArrayOf(1,20),
//        intArrayOf(2,10),
//        intArrayOf(3,5),
//        intArrayOf(4,9),
//        intArrayOf(6,8)
//    )))

//    println(mostBooked1(10, arrayOf(
//        intArrayOf(1,20),
//        intArrayOf(2,10),
//        intArrayOf(3,5),
//        intArrayOf(4,9),
//        intArrayOf(6,8)
//    )))
}

/**
 * Runtime 1215 ms Beats 60%
 * Memory 100.4 MB Beats 80%
 */
fun mostBooked1(n: Int, meetings: Array<IntArray>): Int {
    val amountOfmeetings = IntArray(n)
    val currentMeeting = LongArray(n) // contains end of meeting
    val sortedMeetings = sortMeetings(meetings)
    var maxMeetings = 0
    var resultRoom = -1
    sortedMeetings.forEach { start, end ->
        var closestTime = 0L
        var closestRoom = -1
        var setup = false
        for (i in 0..currentMeeting.size-1) {
            if (currentMeeting[i] <= start) {
                currentMeeting[i] = end
                amountOfmeetings[i] += 1
                closestTime = 0L
                println("meeting $start,$end in room $i, amount of meetings ${amountOfmeetings[i]}")
                setup = true
                if (amountOfmeetings[i] > maxMeetings ||
                    (amountOfmeetings[i] == maxMeetings && resultRoom > i)) {
                    maxMeetings = amountOfmeetings[i]
                    resultRoom = i
                    println("result room $resultRoom")
                }
                break
            } else if (currentMeeting[i] < closestTime || closestTime == 0L) {
                closestTime = currentMeeting[i]
                closestRoom = i
            }
        }
        if (!setup && closestRoom != -1) {
            currentMeeting[closestRoom] = end + closestTime - start
            amountOfmeetings[closestRoom] += 1
            println("meeting $start,$end in closest room $closestRoom, amount of meetings ${amountOfmeetings[closestRoom]}")
            if (amountOfmeetings[closestRoom] > maxMeetings ||
                (amountOfmeetings[closestRoom] == maxMeetings && resultRoom > closestRoom)) {
                maxMeetings = amountOfmeetings[closestRoom]
                resultRoom = closestRoom
                println("result room $resultRoom")
            }
        }

    }
    return resultRoom
}

data class MeetingRoom(
    val number: Int,
    var endOfMeeting: Long = 0L,
    var countOfMeetings: Int = 0
) {
    fun addMeeting(endTime: Long) {
        endOfMeeting = endTime
        countOfMeetings += 1
    }
}
/**
 * Runtime 1206 ms Beats 60%
 * Memory 99.8 MB Beats 86.67%
 */
fun mostBooked2(n: Int, meetings: Array<IntArray>): Int {
    val rooms = Array(n) { i -> MeetingRoom(i) }
    var maxMeetings = 0
    var resultRoom = -1
    sortMeetings(meetings).forEach { start, end ->
        var closestTime = 0L
        var closestRoom = -1
        var setup = false
        for (i in 0..rooms.size-1) {
            val room = rooms[i]
            if (room.endOfMeeting <= start) {
                room.addMeeting(end)
                closestTime = 0L
                setup = true
                if (room.countOfMeetings > maxMeetings ||
                    (room.countOfMeetings == maxMeetings && resultRoom > i)) {
                    maxMeetings = room.countOfMeetings
                    resultRoom = i
                }
                break
            } else if (room.endOfMeeting < closestTime || closestTime == 0L) {
                closestTime = room.endOfMeeting
                closestRoom = i
            }
        }
        if (!setup && closestRoom != -1) {
            val closetRoom =  rooms[closestRoom]
            closetRoom.addMeeting(end + closestTime - start)
            if (closetRoom.countOfMeetings > maxMeetings ||
                (closetRoom.countOfMeetings == maxMeetings && resultRoom > closestRoom)) {
                maxMeetings = closetRoom.countOfMeetings
                resultRoom = closestRoom
            }
        }
    }
    return resultRoom
}

fun sortMeetings(meetings: Array<IntArray>): SortedMap<Long, Long> {
    val sortedMap = sortedMapOf<Long, Long>()
    for(item in meetings) {
        sortedMap.put(item[0].toLong(), item[1].toLong())
    }
    return sortedMap
}


//Fastest

class Solution {


    /*
        n; number of rooms
        meetings; list of intervals (half closed)

        meeting allocation
            next unused room
            next unused room with lowest number

        meeting notes
            meetings are prioritized by start time
            meeting will be delayed to next availability if no available room.
            meeting will retain original duration if delayed.


        Which room has the most meetings?

        Which meeting is next?
        Which rooms are available at a given time?
        Which of these rooms have the smallest room number?


        Brute force
            Sort meetings by start time
            Create room object and list of object to store room number and count
            Itterate through each meeting
                Itterate through each room
                    Keep track of the room with the earliest end time
                    if the room time is less than the current meeting time, update the room time wiht the end time and continue
                assume we couldn't find a avilable room and update the room with the earliest time

     */

    fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
        var rooms = Array<Room>(n) { i -> Room(i) }

        val meets = mutableListOf<LongArray>()
        meetings.forEach { meet ->
            meets.add(longArrayOf(meet[0].toLong(), meet[1].toLong()))
        }
        meets.sortWith(Comparator { a, b -> when {
            a[0] < b[0] -> -1
            a[0] > b[0] -> 1
            else -> 0
        }})

        meets.forEach { meet ->

            val start = meet[0]
            val end = meet[1]
            val duration = end - start
            var indexOfMinTime = -1
            for (i in 0 until rooms.size) {
                val room = rooms[i]
                if (room.end <= start) {
                    room.end = end
                    room.count++
                    return@forEach
                } else if (indexOfMinTime == -1 || room.end < rooms[indexOfMinTime].end) {
                    indexOfMinTime = i
                }
            }
            val room = rooms[indexOfMinTime]
            val newEnd = room.end + duration
            room.end = newEnd
            room.count++
        }


        var indexOfMax = 0
        rooms.forEachIndexed { index, room ->
            if (rooms[indexOfMax].count < room.count) indexOfMax = index
        }
        return indexOfMax
    }

    data class Room(
        val number: Int,
        var end: Long = 0L,
        var count: Int = 0
    )

}

class MySolution3 {

    data class MeetingRoom(
        val number: Int,
        var endOfMeeting: Int = 0,
        var countOfMeetings: Int = 0
    )

    fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
        val rooms = PriorityQueue<MeetingRoom>(
            Comparator<MeetingRoom> { m1, m2 -> m1.endOfMeeting-m2.endOfMeeting }
                .then(Comparator<MeetingRoom> { m1, m2 -> m1.number-m2.number})
        )
        for(i in 0..n-1) {
            rooms.add(MeetingRoom(i))
        }

        var maxMeetings = 0
        var resultRoom = -1
        sortMeetings(meetings).forEach { start, end ->
            val meetingTime = end - start
            val closestRoom = rooms.remove()
            closestRoom.apply {
                endOfMeeting = if (endOfMeeting > start) endOfMeeting + meetingTime else end
                countOfMeetings++
            }
            if (closestRoom.countOfMeetings > maxMeetings) {
                maxMeetings = closestRoom.countOfMeetings
                resultRoom = closestRoom.number
            } else if (closestRoom.countOfMeetings == maxMeetings){
                if (resultRoom == -1) resultRoom = closestRoom.number
                else resultRoom = kotlin.math.min(resultRoom, closestRoom.number)
            }
            rooms.add(closestRoom)
        }

        return resultRoom
    }

    fun sortMeetings(meetings: Array<IntArray>): SortedMap<Int, Int> {
        val sortedMap = sortedMapOf<Int, Int>()
        for(item in meetings) {
            sortedMap.put(item[0], item[1])
        }
        return sortedMap
    }
}