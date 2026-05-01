package yandex_algo

import kotlin.math.max

fun main() {

    println(findMaxAverage(intArrayOf(1, 12, -5, -6, 50, 3), 4))
    println(findMaxAverage(intArrayOf(0, 4, 0, 3, 2), 1))

    println("---maxVowels--")
    println(maxVowels("i", 0)) //0
    println(maxVowels("j", 0)) //0
    println(maxVowels("i", 1)) //1
    println("---")
    println(maxVowels("b", 1)) //0
    println(maxVowels("ba", 2)) //1
    println(maxVowels("bj", 2)) //0
    println("---")
    println(maxVowels("bji", 3)) //1
    println(maxVowels("bjj", 3)) //0
    println("---")
    println(maxVowels("abchhhkauekaur", 3)) // 3
    println(maxVowels("aueklfgakeeejkajiiikld", 5)) //4


    println("---lengthOfLongestSubstring--")
    println(lengthOfLongestSubstring("abc")) // 3
    println(lengthOfLongestSubstring("abac")) //3
    println(lengthOfLongestSubstring("aaa")) //1
    println(lengthOfLongestSubstring("abceedfkttynl")) //5
    println(lengthOfLongestSubstring("aaa"))//1
    println(lengthOfLongestSubstring("bbba"))//2
    println(lengthOfLongestSubstring("bbbab"))//2
    println(lengthOfLongestSubstring("bbbab"))//2
    println(lengthOfLongestSubstring("pwwkew"))//3

    println("---minSubArrayLen--")
    println(minSubArrayLen(7, intArrayOf(2, 3, 1, 2, 4, 3)))//2
    println(minSubArrayLen(4, intArrayOf(1, 4, 4))) //1
    println(minSubArrayLen(10, intArrayOf(1, 4, 4))) //0
    println(minSubArrayLen(3, intArrayOf(1, 1, 1))) // 3
    println(minSubArrayLen(3, intArrayOf(1, 1, 3))) // 1
    println(minSubArrayLen(7, intArrayOf(2, 3, 1, 2, 4, 3))) //2
    println(minSubArrayLen(11, intArrayOf(1, 1, 1, 1, 1, 1, 1, 1))) //0
    println(minSubArrayLen(4, intArrayOf(1, 4, 4))) //1


    println("---findMaxConsecutiveOnes----")
    println(findMaxConsecutiveOnes(intArrayOf(1, 1)))
    println(findMaxConsecutiveOnes(intArrayOf(1, 1, 0, 1, 1, 1)))

    println("--findAnagrams--")
    println(findAnagrams("abc", "abc"))//[0]
    println(findAnagrams("abcbcacab", "abc"))//[0,3,6]
    println(findAnagrams("abcbdsfcacab", "abc"))//[0,9]
    println(findAnagrams("cbaebabacd", "abc"))//[0,6]
    println(findAnagrams("abab", "ab"))//[0,1,2]
    println(findAnagrams("ababababab", "aab"))//[0,2,4,6]

    println("---characterReplacement---")
    println(characterReplacement("ABA", 1))//3
    println(characterReplacement("ABAB", 1))//3
    println(characterReplacement("AAAA", 1))//4
    println(characterReplacement("ABAB", 2))//4
    println(characterReplacement("AABABBA", 1))//4
    println(characterReplacement("AABHGLBBBAGHV", 1))//4
}


/**
 * 643. Maximum Average Subarray I
 */

fun findMaxAverage(nums: IntArray, k: Int): Double {
    var left = 0
    var current = 0
    var max = Int.MIN_VALUE

    for ((right, n) in nums.withIndex()) {
        current += n

        if (right - left + 1 == k) {
            max = maxOf(current, max)
            current -= nums[left]
            left++
        }

    }
    return max.toDouble() / k
}

fun maxVowels(s: String, k: Int): Int {
    val set = setOf('a', 'e', 'i', 'o', 'u')

    var temp = 0
    var result = 0

    for ((index, letter) in s.withIndex()) {
        if (index < k) {
            if (set.contains(letter)) {
                temp++
                result++
            }
        } else {
            val prevLetter = s[index - k]
            if (set.contains(prevLetter)) {
                temp--
            }
            if (set.contains(letter)) {
                temp++
            }
            result = maxOf(temp, result)
        }
    }

    return result
}


fun lengthOfLongestSubstring(s: String): Int {

    var set = mutableSetOf<Char>()
    var left = 0
    var result = 0

    for ((right, c) in s.withIndex()) {
        while (set.contains(c)) {
            set.remove(s[left])
            left++
        }
        set.add(c)
        result = maxOf(result, right - left + 1)

    }
    return result
}

/**
 * 209. Minimum Size Subarray Sum
 */

fun minSubArrayLen(target: Int, nums: IntArray): Int {
    var left = 0
    var temp = 0
    var result = Int.MAX_VALUE

    for ((right, n) in nums.withIndex()) {
        temp += n
        while (temp >= target) {
            result = minOf(result, right - left + 1)
            temp -= nums[left]
            left++

        }
    }
    return if (result == Int.MAX_VALUE) 0 else result
}

fun findMaxConsecutiveOnes(nums: IntArray): Int {
    var right = 0
    var temp = 0
    var result = 0

    for (n in nums) {
        if (n == 1) {
            temp += 1
        } else {
            result = max(result, temp)
            temp = 0
        }
        right++
    }
    return max(result, temp)
}

/**
 * 438. Find All Anagrams in a String**
 */
fun findAnagrams(s: String, p: String): List<Int> {
    var left = 0
    val intArray = IntArray(26)
    for (char in p) {
        val index = char - 'a'
        intArray[index] = intArray[index] + 1
    }
    var result = mutableListOf<Int>()

    val tempArray = IntArray(26)

    for ((right, c) in s.withIndex()) {
        if (right < p.length - 1) {
            continue
        } else {
            val tempString = s.substring(left, right + 1)
            for (char in tempString) {
                val index = char - 'a'
                tempArray[index] = tempArray[index] + 1
            }

            if (intArray.contentEquals(tempArray)) {
                result.add(left)
            }
            left++

            tempArray.fill(0)
        }

    }
    return result
}

/**
 * 424. Longest Repeating Character Replacement
 */

fun characterReplacement(s: String, k: Int): Int {
    var result = 0

    for ((i, n) in s.withIndex()) {
        var temp = k
        var right = i + 1
        var left = if (i == 0) 0 else i - 1
        while ((left >= 0 || right <= s.length - 1) && ((s[right] == n && s[left] == n) || temp > 0)) {
            if (s[right] != n && temp > 0) {
                temp--
            } else if (s[left] != n && temp > 0) {
                temp--
            } else {
                break
            }
            right++
            left--
        }
        result = max(result, right - left)
    }

    return result
}
