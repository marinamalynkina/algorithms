package leetcode.intervals

class Meeting_room__Easy_252 {
    fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
        intervals.sortWith {interval1, interval2 -> Integer.compare(interval1[0], interval2[0])}
        for(i in 0..intervals.lastIndex-1) {
            if (intervals[i][1] > intervals[i+1][0]) return false
        }
        return true
    }

}