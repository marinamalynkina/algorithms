package leetcode.intervals

import java.util.*
import kotlin.math.max

fun main(args: Array<String>) {
    MeetingsRoom2_Medium_253().apply {
        println(minMeetingRooms(arrayOf(
            intArrayOf(0,30),
            intArrayOf(5,10),
            intArrayOf(15,20),
        )))
        println(minMeetingRooms(arrayOf(
            intArrayOf(7,10),
            intArrayOf(2,4),
        )))
    }
}

class MeetingsRoom2_Medium_253 {
    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        intervals.sortWith {interval1, interval2 -> Integer.compare(interval1[0], interval2[0])}
        var minRooms = 1
        val rooms = PriorityQueue<Int>() // saves time when room become available
        intervals.forEach { meeting ->
            var meetingAssignedToRoom = false
            while (!meetingAssignedToRoom) {
                if (rooms.isEmpty()) {
                    rooms.add(meeting[1])
                    meetingAssignedToRoom = true
                }else {
                    val busyTill = rooms.peek()
                    if (meeting[0] >= busyTill) rooms.remove()
                    else {
                        rooms.add(meeting[1])
                        meetingAssignedToRoom = true
                    }
                }
            }
            minRooms = max(minRooms, rooms.size)
        }
        return minRooms
    }

    fun minMeetingRooms2(intervals: Array<IntArray>): Int {
        intervals.sortWith {interval1, interval2 -> interval1[0] - interval2[0] }
        var minRooms = 1
        val rooms = PriorityQueue<Int>() // saves time when room become available
        intervals.forEach { meeting ->
            var meetingAssignedToRoom = false
            while (!meetingAssignedToRoom) {
                if (rooms.isNotEmpty() && rooms.peek() <= meeting[0]) {
                    rooms.poll()
                }
                rooms.add(meeting[1])
                meetingAssignedToRoom = true
            }
            minRooms = max(minRooms, rooms.size)
        }
        return minRooms
    }
}