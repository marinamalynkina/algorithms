package leetcode.design

import java.util.*


fun main(args: Array<String>) {
    MKAverageTreeSet(4, 1).apply {
//        [3], [1], [], [10], [], [5], [5], [5], []
        addElement(1)
        addElement(2)
        addElement(3)
        addElement(4)
        println(calculateMKAverage())
        addElement(5)
        println(calculateMKAverage())
        addElement(5)
        println(calculateMKAverage())
    }
}

class Hard_T1825_FindingMKAverage(m: Int, k: Int) {
    val K: Int
    val M: Int

    val list = LinkedList<Int>()
    var count = 0

    init {
        K = k
        M = m
    }

    fun addElement(num: Int) {
        list.add(num)
        if (list.size > M) {
            list.removeFirst()
        }
        count++
    }

    fun calculateMKAverage(): Int {
        if (count < M) return -1
        else {
            val array = list.sorted()
            var sum = 0
            for(i in K..array.size-K-1) {
                sum += array[i]
            }
            return sum/(M-2*K)
        }
    }
}


internal class MKAverage(var m: Int, var k: Int) {
    var left = PriorityQueue { a: Int, b: Int -> b - a }
    var right = PriorityQueue { a: Int, b: Int -> a - b }
    var minMiddle = PriorityQueue { a: Int, b: Int -> a - b }
    var maxMiddle = PriorityQueue { a: Int, b: Int -> b - a }
    var vals: Queue<Int> = LinkedList()
    var deleteLeft: MutableMap<Int, Int> = HashMap()
    var deleteRight: MutableMap<Int, Int> = HashMap()
    var deleteMinMiddle: MutableMap<Int, Int> = HashMap()
    var deleteMaxMiddle: MutableMap<Int, Int> = HashMap()
    var sum: Long = 0
    var flag = false
    fun addElement(num: Int) {
        vals.add(num)
        if (!flag && vals.size == m) {
            initialize()
            flag = true
        } else if (flag) {
            val lv = vals.poll() // returns element that need to remove from list
            update(left, deleteLeft)
            update(right, deleteRight)
            if (!left.isEmpty() && left.peek() >= lv) {
                update(minMiddle, deleteMinMiddle)
                val v = minMiddle.poll()
                deleteMaxMiddle[v] = deleteMaxMiddle.getOrDefault(v, 0) + 1
                left.add(v)
                deleteLeft[lv] = deleteLeft.getOrDefault(lv, 0) + 1
                sum -= v.toLong()
            } else if (!right.isEmpty() && right.peek() <= lv) {
                update(maxMiddle, deleteMaxMiddle)
                val v = maxMiddle.poll()
                deleteMinMiddle[v] = deleteMinMiddle.getOrDefault(v, 0) + 1
                right.add(v)
                deleteRight[lv] = deleteRight.getOrDefault(lv, 0) + 1
                sum -= v.toLong()
            } else {
                deleteMinMiddle[lv] = deleteMinMiddle.getOrDefault(lv, 0) + 1
                deleteMaxMiddle[lv] = deleteMaxMiddle.getOrDefault(lv, 0) + 1
                sum -= lv.toLong()
            }
            update(left, deleteLeft)
            update(right, deleteRight)
            sum += if (!left.isEmpty() && left.peek() >= num) {
                val v = left.poll()
                minMiddle.add(v)
                maxMiddle.add(v)
                left.add(num)
                v.toLong()
            } else if (!right.isEmpty() && right.peek() <= num) {
                val v = right.poll()
                minMiddle.add(v)
                maxMiddle.add(v)
                right.add(num)
                v.toLong()
            } else {
                minMiddle.add(num)
                maxMiddle.add(num)
                num.toLong()
            }
        }
    }

    fun calculateMKAverage(): Int {
        // System.out.println(sum);
        return if (flag) (sum / (m - 2 * k)).toInt() else -1
    }

    private fun initialize() {
        var i: Int
        i = 0
        while (i < m) {
            val v = vals.poll()
            minMiddle.add(v)
            maxMiddle.add(v)
            sum += v.toLong()
            vals.add(v)
            ++i
        }
        i = 0
        while (i < k) {
            update(minMiddle, deleteMinMiddle)
            val v = minMiddle.poll()
            left.add(v)
            deleteMaxMiddle[v] = deleteMaxMiddle.getOrDefault(v, 0) + 1
            sum -= v.toLong()
            i++
        }
        i = 0
        while (i < k) {
            update(maxMiddle, deleteMaxMiddle)
            val v = maxMiddle.poll()
            right.add(v)
            deleteMinMiddle[v] = deleteMinMiddle.getOrDefault(v, 0) + 1
            sum -= v.toLong()
            i++
        }
    }

    private fun update(pq: PriorityQueue<Int>, delete: MutableMap<Int, Int>) {
        while (!pq.isEmpty() && delete.containsKey(pq.peek()) && delete[pq.peek()]!! > 0) {
            val v = pq.poll()
            delete[v] = delete[v]!! - 1
            if (delete[v] == 0) delete.remove(v)
        }
    }
}


class MKAverageTreeSet(private val m: Int, private val k: Int) {
    internal inner class Node(var `val`: Int, var time: Int) : Comparable<Node?> {
        override fun compareTo(other: Node?): Int {
            return if (`val` != other?.`val`) `val` - (other?.`val` ?: 0) else this.time - other.time
        }
    }

    private val set: TreeSet<Node> = TreeSet<Node>() // natural order
    private val queue: Deque<Node> = LinkedList<Node>()
    private var kLeft: Node? = null
    private var kRight: Node? = null
    private var time = 0
    private var sum = 0
    fun addElement(num: Int) {
        val node: Node = Node(num, time++)
        addNode(node)
        removeNode()
        if (time == m) init()
    }

    private fun init() {
        var i = 0
        for (node in set) {
            if (i < k - 1) ;
            else if (i == k - 1) kLeft = node
            else if (i < m - k) sum += node.`val`
            else if (i == m - k) {
                kRight = node
                return
            }
            i++
        }
        return
    }

    private fun addNode(node: Node) {
        queue.offerLast(node)
        set.add(node)
        if (queue.size <= m) return
        if (node.compareTo(kLeft) < 0) {
            sum += (kLeft?.`val`?:0)
            kLeft = set.lower(kLeft)
        } else if (node.compareTo(kRight) > 0) {
            sum += (kRight?.`val`?:0)
            kRight = set.higher(kRight)
        } else {
            sum += node.`val`
        }
    }

    private fun removeNode() {
        if (queue.size <= m) return
        val node: Node = queue.pollFirst()
        if (node.compareTo(kLeft) <= 0) {
            kLeft = set.higher(kLeft)
            sum -= (kLeft?.`val`?:0)
        } else if (node.compareTo(kRight) >= 0) {
            kRight = set.lower(kRight)
            sum -= (kRight?.`val`?:0)
        } else {
            sum -= node.`val`
        }
        set.remove(node)
    }

    fun calculateMKAverage(): Int {
        return if (queue.size < m) -1 else sum / (m - 2 * k)
    }
}