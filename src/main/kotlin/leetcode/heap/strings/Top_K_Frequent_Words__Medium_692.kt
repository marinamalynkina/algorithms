package leetcode.heap.strings

import java.util.PriorityQueue

class Top_K_Frequent_Words__Medium_692 {
    fun topKFrequent(words: Array<String>, k: Int): List<String> {
        val wordsMap = mutableMapOf<String, Int>()
        words.forEach { word ->
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1)
        }

        val priorityQueue = PriorityQueue<String>( { w1, w2 ->
            if (wordsMap.get(w1) == wordsMap.get(w2)) {
                w2.compareTo(w1)
            } else {
                wordsMap.get(w1)!! - wordsMap.get(w2)!!
            }
        })
        wordsMap.keys.forEach { key ->
            priorityQueue.offer(key)
            if (priorityQueue.size > k) {
                priorityQueue.poll()
            }
        }

        val result = mutableListOf<String>()
        while(priorityQueue.isNotEmpty()) {
            result.add(priorityQueue.poll())
        }
        result.reverse()
        return result
    }
}