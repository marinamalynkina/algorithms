package stringsarrays

fun main(args: Array<String>) {
    println(removeVowels2("leetcodeisacommunityforcoders"))
}

/**
 * Runtime 145 ms Beats 88.89%
 * Memory 34.8 MB Beats 88.89%
 */
fun removeVowels1(s: String): String {
    return removeChars(s, "aeiou")
}

fun removeChars(s: String, chars: String): String {
    val newString = StringBuilder()
    s.forEach { newChar ->
        if (!chars.contains(newChar)) newString.append(newChar)
    }
    return newString.toString()
}

/**
 * Runtime 129 ms Beats 100%
 * Memory 33.7 MB Beats 100%
 */
fun removeVowels2(s: String): String {
    val newString = StringBuilder()
    s.forEach { newChar ->
        if (!newChar.equals('a') &&
            !newChar.equals('e') &&
            !newChar.equals('i') &&
            !newChar.equals('o') &&
            !newChar.equals('u')) {
            newString.append(newChar)
        }
    }
    return newString.toString()
}

fun removeVowels3(s: String): String {
    val vowels = hashSetOf('a', 'e', 'i', 'o', 'u')
    val newString = StringBuilder()
    s.forEach { newChar ->
        if (!vowels.contains(newChar)) {
            newString.append(newChar)
        }
    }
    return newString.toString()
}