package leetcode.stringsarrays



fun main(args: Array<String>) {
    T13().run {
//        println(romanToInt("III"))
        println(romanToInt("LVIII"))
        println(romanToInt("MCMXCIV"))
    }
}

class T13 {
    val romanMap = mapOf(
        ' ' to 0,
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )

    val romanArray = mapOf(
        ' ' to 0,
        'I' to 1,
        'V' to 2,
        'X' to 3,
        'L' to 4,
        'C' to 5,
        'D' to 6,
        'M' to 7
    )

    private fun romanArrayValue(char: Char) = romanArray[char] ?: 0
    private fun romanMapValue(char: Char) = romanMap[char] ?: 0

    fun romanToInt(s: String): Int {
        var sum = 0
        val array = s.toCharArray()
        var previousChar = ' '
        var count = 0
        for (i in array.size-1 downTo 0) {
            val char = array[i]
            if (char != previousChar || i == 0) {
                val diff = romanArrayValue(previousChar) - romanArrayValue(char)
                if (diff > 0 && diff <= 2 && (romanArray[char] ?: 0) % 2 != 0 ) {
                    sum += (romanMapValue(previousChar) - romanMapValue(char))
                    count = 0
                } else {
                    sum += romanMapValue(previousChar) * count
                    count = 1
                }
            } else {
                count++
            }
            previousChar = char
        }
        if (count != 0) {
            sum += romanMapValue(previousChar) * count
        }
        return sum
    }

    fun romanToInt2(s: String): Int {
        var sum = 0
        s.forEachIndexed { index, char ->
            var nextChar = ' '
            if(index < s.length - 1)
                nextChar = s[index + 1]
            when (char) {
                'I' -> if(nextChar == 'V' || nextChar == 'X') sum -=1 else sum+=1
                'V' -> sum+=5
                'X' -> if(nextChar == 'L' || nextChar == 'C') sum -=10 else sum+=10
                'L' -> sum +=50
                'C' -> if(nextChar == 'D' || nextChar == 'M') sum -=100 else sum+=100
                'D' -> sum +=500
                'M' -> sum +=1000

            }
        }
        return sum
    }
}