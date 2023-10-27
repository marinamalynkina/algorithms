package leetcode.stringsarrays

fun main(args: Array<String>) {

    Medium_T2007_FindOriginalArrayFromDoubledArray2().apply {
        findOriginalArray(intArrayOf(1,3,4,2,6,8))
//        findOriginalArray(intArrayOf(4,4,16,20,8,8,2,10))
    }
}

class Medium_T2007_FindOriginalArrayFromDoubledArray {
    fun findOriginalArray(changed: IntArray): IntArray {

        if (changed.size%2 >0) return IntArray(0)

        val searchForOriginal = mutableMapOf<Int, Int>()
        val searchForDoubles = mutableMapOf<Int, Int>()
        val originalArray = mutableListOf<Int>()

        changed.forEach { value ->
            if (value%2 == 0) { // possible doubled value
                if (searchForDoubles.getOrDefault(value, 0) > 0) {
                    originalArray.add(value / 2)
                    searchForDoubles[value] = searchForDoubles[value]!! - 1
                    if (searchForOriginal.contains(value/4)) searchForOriginal[value/4] = searchForOriginal[value/4]!! - 1
                } else if (searchForOriginal.getOrDefault(value, 0) > 0) {
                    originalArray.add(value)
                    searchForOriginal[value] = searchForOriginal[value]!! - 1
                    if (searchForDoubles.contains(value*4)) searchForDoubles[value*4] = searchForDoubles[value*4]!! - 1
                } else {
                    // it can be original or doubles
                    searchForOriginal[value/2] = searchForOriginal.getOrDefault(value/2, 0)+1
                    if (value != 0) searchForDoubles[value*2] = searchForDoubles.getOrDefault(value*2, 0)+1
                }
            } else {
                if (searchForOriginal.getOrDefault(value, 0)>0) {
                    originalArray.add(value)
                    searchForOriginal[value] = searchForOriginal[value]!! - 1
                } else {
                    searchForDoubles[value*2] = searchForDoubles.getOrDefault(value*2, 0)+1
                }
            }
        }

        if (originalArray.size == changed.size/2)
            return originalArray.toIntArray()
        else return IntArray(0)
    }
}

class Medium_T2007_FindOriginalArrayFromDoubledArray2 {
    fun findOriginalArray(changed: IntArray): IntArray {
        // It can't be doubled array, if the size is odd
        if (changed.size % 2 == 1) {
            return IntArray(0)
        }
        var maxNum = 0
        // Find the max element in the array
        for (num in changed) {
            maxNum = Math.max(maxNum, num)
        }
        val freq = IntArray(maxNum + 1)
        // Store the frequency in the map
        for (num in changed) {
            freq[num]++
        }
        val original = IntArray(changed.size / 2)
        var index = 0
        var num = 0
        var originSize = 0
        while (num <= maxNum && originSize < original.size) {

            // If element exists
            if (freq[num] > 0) {
                freq[num]--
                val twiceNum = num * 2
                if (twiceNum <= maxNum && freq[twiceNum] > 0) {
                    // Pair up the elements, decrement the count
                    freq[twiceNum]--
                    // Add the original number to answer
                    original[index++] = num
                    originSize++
                    num--
                } else {
                    return IntArray(0)
                }
            }
            num++
        }
        return original
    }
}