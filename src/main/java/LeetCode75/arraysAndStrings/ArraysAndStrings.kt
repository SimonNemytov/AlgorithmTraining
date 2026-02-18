package LeetCode75.arraysAndStrings

fun main() {

    println(mergeAlternately("abc", "pqr")) //apbqcr
    println(mergeAlternately("ab", "pqrs")) //apbqrs
    println(mergeAlternately("a", "pqr"))
    println(mergeAlternately("abcd", "pq"))

    println(gcdOfStrings("ABCABC", "ABC")) // "ABC"
    println(gcdOfStrings("ABABAB", "ABAB")) //"AB"
    println(gcdOfStrings("LEET", "CODE"))//""
    println(gcdOfStrings("ABCABAB", "AB")) //""
    println(gcdOfStrings("ABCABAB", "ABC")) //""
    println(gcdOfStrings("AAAAAB", "AAA")) //""
    println(gcdOfStrings("AAAAAB", "AAAAA")) //""
    println(gcdOfStrings("ABABABAB", "ABAB")) //"ABAB"

    println(canPlaceFlowers(intArrayOf(1, 0, 1), 0)) // true
    println(canPlaceFlowers(intArrayOf(1, 0, 1), 1)) // false
    println(canPlaceFlowers(intArrayOf(1, 0, 0, 1), 1)) // false
    println(canPlaceFlowers(intArrayOf(1, 0, 0, 0, 1), 1)) // true
    println(canPlaceFlowers(intArrayOf(1, 0, 0, 0, 1), 2)) // false
    println(canPlaceFlowers(intArrayOf(1, 0, 0, 0, 0, 1), 2)) // false
    println(canPlaceFlowers(intArrayOf(1, 0, 0, 0, 0, 0, 1), 2)) // true
    println(canPlaceFlowers(intArrayOf(0, 0, 1), 1)) // true
    println(canPlaceFlowers(intArrayOf(1, 0, 0), 1)) // true
    println(canPlaceFlowers(intArrayOf(0, 0, 0, 0, 0), 3)) // true
    println(canPlaceFlowers(intArrayOf(0, 0), 1000)) // false
    println(canPlaceFlowers(intArrayOf(0), 0)) // true
    println(canPlaceFlowers(intArrayOf(0), 1)) // true
    println(canPlaceFlowers(intArrayOf(0), 2)) // true

    println("----")

    println(reverseWords("the sky is blue"))
    println(reverseWords("the sky hk is blue"))

    productExceptSelf(intArrayOf(1, 2, 3, 4))
    productExceptSelf(intArrayOf(-1, 1, 0, -3, 3))


    println(increasingTriplet(intArrayOf(1, 2, 3))) //true
    println(increasingTriplet(intArrayOf(1, 2, 1))) //false
    println(increasingTriplet(intArrayOf(2, 1, 4))) //false
    println(increasingTriplet(intArrayOf(5, 3, 4))) //false
    println(increasingTriplet(intArrayOf(1, 2, 1, 3))) //true
    println(increasingTriplet(intArrayOf(1, 2, 1, 1, 2, 3))) //true
    println(increasingTriplet(intArrayOf(1, 100, 2, 1000, 5))) //true
    println(increasingTriplet(intArrayOf(1, 100000, 2, 1000, 5))) //true
    println(increasingTriplet(intArrayOf(0, 0, 0))) //false
    println(increasingTriplet(intArrayOf(-3, -2, -1))) //true
    println(increasingTriplet(intArrayOf(-1, -2, -3))) //false
    println(increasingTriplet(intArrayOf(-1, 2, 3))) //true
    println(increasingTriplet(intArrayOf(20, 100, 10, 12, 5, 13))) //true

    println(compress(charArrayOf('a', 'a', 'b', 'b', 'c', 'c', 'c')))
    println(compress(charArrayOf('a')))
    println(compress(charArrayOf('a','b')))
}

fun mergeAlternately(word1: String, word2: String): String {
    val n1 = word1.length
    val n2 = word2.length
    var result = ""
    var i = 0

    while (i < n1 || i < n2) {
        if (i < n1) result += word1[i]
        if (i < n2) result += word2[i]
        i++
    }
    return result
}

/**
 * 1071. Greatest Common Divisor of Strings
 */

fun gcdOfStrings(str1: String, str2: String): String {
    if ((str1 + str2) != str2 + str1) {
        return ""
    }

    val set = mutableSetOf<String>()

    for (i in 1..str1.length) {
        val string = str1.subSequence(0, i).toString()
        set.add(string)
    }

    for (i in 1..str2.length) {
        val string = str2.subSequence(0, i).toString()
        set.add(string)
    }

    var resultPattern = ""

    for (pattern in set) {
        val isOKFirst = canBeSplit(pattern, str1)
        val isOKSecond = canBeSplit(pattern, str2)
        if (isOKFirst && isOKSecond) {
            if (pattern.length > resultPattern.length) {
                resultPattern = pattern
            }
        }
    }
    return resultPattern
}

fun canBeSplit(pattern: String, str: String): Boolean {
    var result = str
    while (result.isNotEmpty()) {
        if (result.length < pattern.length) {
            return false
        }

        val removePrefix = result.removePrefix(pattern)
        if (removePrefix == result) {
            return false
        }
        result = removePrefix
    }
    return true
}

/**
 * 1431. Kids With the Greatest Number of Candies
 */

fun kidsWithCandies(candies: IntArray, extraCandies: Int): List<Boolean> {
    val greatest = candies.max()

    val result = mutableListOf<Boolean>()

    for (candy in candies) {
        if (candy + extraCandies >= greatest) {
            result.add(true)
        } else result.add(false)
    }
    return result
}

/**
 * 605. Can Place Flowers
 *
 */

fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
    var result = n

    if (flowerbed.size == 1) {
        if (n == 1) {
            return flowerbed[0] == 0
        } else if (n > 1) {
            return false
        }
    }

    for (i in 0..flowerbed.size - 1) {
        if (result == 0) {
            return true
        }
        if (i == 0) {
            if (flowerbed[i] != 1 && flowerbed[1] == 0) {
                flowerbed[i] = 1
                result--
            }
        } else if (flowerbed[i] != 1 && i == flowerbed.size - 1) {
            if (flowerbed[i - 1] == 0) {
                flowerbed[i] = 1
                result--
            }
        } else if (flowerbed[i] != 1) {
            if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                result--
                flowerbed[i] = 1
            }
        }
    }

    return result == 0
}


/**
 * 151. Reverse Words in a String
 */

fun reverseWords(s: String): String {
    val array = s
        .trim()
        .replace(Regex("\\s+"), " ")
        .split(" ").toMutableList()

    var i = 0
    var j = array.size - 1
    while (i < j) {
        val temp = array[i]
        array[i] = array[j]
        array[j] = temp
        i++
        j--
    }

    val sb = StringBuilder()

    array.forEach { it -> sb.append(it).append(" ") }

    return sb.toString().trim()
}

fun reverseWordsV2(s: String): String {
    return s.trim().split(Regex("\\s+")).reversed().joinToString(" ")
}

/**
 * 238. Product of Array Except Self
 */

fun productExceptSelf(nums: IntArray): IntArray {
    //prefix произведения до этого значения
    val prefixArray = IntArray(nums.size)
    for (index in nums.indices) {
        var j = index - 1
        if (index == 0) {
            prefixArray[0] = 1
        } else {
            val prefix = prefixArray[j] * nums[j]
            prefixArray[index] = prefix
        }
    }
    //suffix произведения после этого элемента
    val suffixArray = IntArray(nums.size)
    for (index in nums.indices.reversed()) {
        val j = index + 1
        if (index == nums.size - 1) {
            suffixArray[nums.size - 1] = 1
        } else {
            val suffix = suffixArray[j] * nums[j]
            suffixArray[index] = suffix
        }
    }
    val resultArray = IntArray(nums.size)
    for (index in nums.indices) {
        resultArray[index] = prefixArray[index] * suffixArray[index]
    }

    return resultArray
}

/**
 * 334. Increasing Triplet Subsequence
 */

fun increasingTriplet(nums: IntArray): Boolean {
    if (nums.size < 3) return false

    var first = Int.MAX_VALUE
    var second = Int.MAX_VALUE

    for (num in nums) {
        if (num <= first) {
            // Нашли новое минимальное число
            first = num
        } else if (num <= second) {
            // Нашли число, которое больше first, но меньше/равно second
            second = num
        } else {
            // Нашли число, которое больше и first, и second!
            return true
        }
    }

    return false
}

/**
 * 443. String Compression
 * были трудности
 */

fun compress(chars: CharArray): Int {
    var index = 0 // where we write stuff
    var i = 0

    while (i < chars.size) {
        val current = chars[i]
        var count = 0

        // count how many in a row
        while (i < chars.size && chars[i] == current) {
            i++
            count++
        }

        // write char
        chars[index++] = current

        // if count > 1, write digits
        if (count > 1) {
            for (c in count.toString()) {
                chars[index++] = c
            }
        }
    }

    return index
}
