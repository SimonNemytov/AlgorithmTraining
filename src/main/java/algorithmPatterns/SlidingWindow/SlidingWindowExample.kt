package algorithmPatterns.SlidingWindow

fun main() {

    findMaxSum(intArrayOf(2, 1, 5, 1, 3, 2), 3)
    findMaxSum(intArrayOf(0, 1, -1, 4, -100, 0), 6)
    findMaxSum(intArrayOf(1, 1, 1, 1, 1, 1), 2)

    findMaxAverage(intArrayOf(4, 0, 4, 3, 3), 5)

    println(lengthOfLongestSubstring("abcabcbb")) // 3
    println(lengthOfLongestSubstring("aaaaa")) //1
    println(lengthOfLongestSubstring("")) //0
    println(lengthOfLongestSubstring("pwkaaaaabcdefgjjkf")) //8
    println(lengthOfLongestSubstring("aab")) //2

    println("---Minimum Size Subarray Sum---")
    println(minSubArrayLen(7, intArrayOf(2, 3, 1, 2, 4, 3))) //2
    println(minSubArrayLen(4, intArrayOf(1, 4, 4))) //2
}

/**
 * Шаблон скользящего окна используется для поиска подмассива или подстроки,
 * удовлетворяющей определённому условию. Он оптимизирует временную сложность
 * за счёт поддержания «окна» фиксированного или переменного размера.
 *
 * Когда использовать: при работе с непрерывными подмассивами или подстроками.
 * Пример задачи:
 *
 * Найти максимальную сумму подмассива размера k.
 *
 * Пример:
 *
 *     Вход: nums = [2, 1, 5, 1, 3, 2], k = 3
 *
 *     Выход: 9
 */

fun findMaxSum(nums: IntArray, k: Int): Int {
    var currentSum = nums.take(k).sum()
    var maxSum = currentSum

    for (index in k until nums.size) {
        currentSum = currentSum + nums[index] - nums[index - k]
        maxSum = maxOf(maxSum, currentSum)
    }
    return maxSum
}

/**
 * You are given an integer array nums consisting of
 * n elements, and an integer k.
 *
 * Find a contiguous subarray whose
 * length is equal to k that has the maximum
 * average value and return this value. Any answer with a calculation error less
 * than 10-5 will be accepted.
 *
 * Example 1:
 *
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 *
 * Example 2:
 *
 * Input: nums = [5], k = 1
 * Output: 5.00000
 *
 */

fun findMaxAverage(nums: IntArray, k: Int): Double {
    var currSum = nums.take(k).sum()
    var maxSum = currSum
    for (i in k until nums.size) {
        currSum = currSum + nums[i] - nums[i - k]
        maxSum = maxOf(maxSum, currSum)
    }
    return maxSum.toDouble() / k
}

/**
 * Given a string s, find the length of the longest
 *
 * without duplicate characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Note that "bca" and "cab" are also correct answers.
 *
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 */


fun lengthOfLongestSubstring(s: String): Int {
    var left = 0
    var right = 0
    var max = 0

    val variants = mutableSetOf<Char>()

    while (right < s.length) {
        if (variants.add(s[right])) {
            right++
        } else {
            variants.remove(s[left])
            left++
        }
        max = maxOf(max, right - left)

    }
    return max
}


/**
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a
 *
 * whose sum is greater than or equal to target. If there is
 * no such subarray, return 0 instead.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Example 2:
 *
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 * Example 3:
 *
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 *
 */

fun minSubArrayLen(target: Int, nums: IntArray): Int {
    var left = 0
    var right = 0
    var minLength = Int.MAX_VALUE
    var tempSum = 0

    while (right < nums.size) {
        tempSum += nums[right]
        right++

        while (tempSum >= target) {
            minLength = minOf(minLength, right - left)
            tempSum -= nums[left]
            left++
        }
    }

    return if (minLength == Int.MAX_VALUE) 0 else minLength
}

