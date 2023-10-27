package learning.sort

fun main(args: Array<String>) {
    val merge = MergeSort()
    val array = intArrayOf(12,11,13,5,6,7)
    merge.sort(array)

    array.forEach {
        print("${it} ")
    }
}

class MergeSort {

    fun sort(array: IntArray) {
        sort(array, 0, array.lastIndex)
    }

    private fun sort(array: IntArray, startIndex: Int, endIndex: Int) {
        if (startIndex < endIndex) {
            val middle = startIndex + (endIndex-startIndex)/2
            // left part
            sort(array, startIndex, middle)
            // right part
            sort(array, middle+1, endIndex)

            merge(array, startIndex, middle, endIndex)
        }
    }

    /**
     * merge subarray [startIndex, middleIndex] and subarray[middleIndex+1,endIndex]
     */
    private fun merge(array: IntArray, startIndex: Int, middleIndex:Int, endIndex: Int) {
        val leftSize = middleIndex - startIndex + 1
        val leftArray = IntArray(leftSize) { i: Int -> array[startIndex+i] }

        val rightSize = endIndex - middleIndex
        val rightArray = IntArray(rightSize) { i: Int -> array[middleIndex+1+i] }

        // merge two arrays
        var ileft = 0
        var iright = 0
        var i = startIndex
        while (ileft < leftSize && iright < rightSize) {
            if (leftArray[ileft] < rightArray[iright]) {
                array[i] = leftArray[ileft]
                ileft++
            } else {
                array[i] = rightArray[iright]
                iright++
            }
            i++
        }
        while (ileft < leftSize) {
            array[i] = leftArray[ileft]
            ileft++
            i++
        }

        while (iright < rightSize) {
            array[i] = rightArray[iright]
            iright++
            i++
        }
    }
}