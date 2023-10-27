package hash

/**
 * Runtime 220 ms Beats 71.75%
 * Memory 38.3 MB Beats 66.97%
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val cacheOfPreviousValues = mutableMapOf<Int, Int>() // value-index
    nums.forEachIndexed { index, value ->
        val diff = target - value
        cacheOfPreviousValues.get(diff)?.let { secondIndex ->
            return intArrayOf(secondIndex, index)
        } ?:run {
            cacheOfPreviousValues.put(value, index)
        }
    }
    return intArrayOf()
}

/**
 * Runtime 167 ms Beats 100%
 * Memory 38.3 MB Beats 66.97%
 */
class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        //coefficient -> index
        val storedNumbers = HashMap<Int, Int>()
        for ((key, num) in nums.withIndex()) {
            if (storedNumbers.containsKey(num)){
                return IntArray(2).apply {
                    set(0, storedNumbers[num]!!)
                    set(1, key)
                }
            } else {
                storedNumbers[target - num] = key
            }
        }
        return IntArray(2)
    }
}