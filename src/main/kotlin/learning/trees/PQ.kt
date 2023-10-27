package learning.trees

import trees.Solution2458
import java.util.PriorityQueue

fun main(args: Array<String>) {
    val pq = PriorityQueue<Solution2458.NodeInfo>(Comparator { o1, o2 -> o2.maxLevel - o1.maxLevel })

    pq.add(Solution2458.NodeInfo(12, 5))
    pq.add(Solution2458.NodeInfo(12, 3))
    pq.add(Solution2458.NodeInfo(10, 6))
    pq.add(Solution2458.NodeInfo(18, 4))
    pq.add(Solution2458.NodeInfo(9, 5))

    while (pq.isNotEmpty()) {
        pq.poll().let { println("v = ${it.v} maxlevel = ${it.maxLevel}")}
    }

}