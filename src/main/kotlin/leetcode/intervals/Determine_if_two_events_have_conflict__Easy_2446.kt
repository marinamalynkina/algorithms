package leetcode.intervals

import java.text.SimpleDateFormat


fun main(args: Array<String>) {
    Easy_2446_Determine_if_Two_Events_Have_Conflict().apply {
//        println(haveConflict(
//            arrayOf<String>("01:15","02:00"),
//            arrayOf<String>("02:00","03:00")
//        ))
//
        println(haveConflict(
            arrayOf<String>("00:01","02:00"),
            arrayOf<String>("01:20","03:00")
        ))
//
//        println(haveConflict(
//            arrayOf<String>("10:00","11:00"),
//            arrayOf<String>("14:00","15:00")
//        ))

        println(haveConflict(
            arrayOf<String>("01:37","14:20"),
            arrayOf<String>("05:06","06:17")
        ))
    }
}

class Easy_2446_Determine_if_Two_Events_Have_Conflict {

    fun haveConflict(event1: Array<String>, event2: Array<String>): Boolean {
        val newEvent1 = event1.transform()
        val newEvent2 = event2.transform()
        return (newEvent1[0] in newEvent2[0]..newEvent2[1]
                || newEvent1[1] in newEvent2[0]..newEvent2[1]
                || newEvent2[0] in newEvent1[0]..newEvent1[1])
    }

    fun Array<String>.transform(): Array<Long> {
        val dateFormat = SimpleDateFormat("HH:mm");
        return map { timeString ->
            dateFormat.parse(timeString).time/1000 + 3600L
        }.toTypedArray()
    }
}