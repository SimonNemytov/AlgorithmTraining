package LeetCode75.SlidingWindow

fun main() {

    println(findMaxAverage(intArrayOf(5), 1))//5.0
    println(findMaxAverage(intArrayOf(5, 10), 1))//10.0
    println(findMaxAverage(intArrayOf(1, 12, -5, -6, 50, 3), 4))//10.0
    println(findMaxAverage(intArrayOf(0, 4, 0, 3, 2), 1))//4.0

    println(maxVowels("i", 0))//0
    println(maxVowels("i", 1))//1
    println(maxVowels("d", 1))//0
    println(maxVowels("ii", 4))//2
    println(maxVowels("abciiidef", 3))//3

    println(maxVowels("ioefghae", 3))//3
    println(maxVowels("ifoefghae", 3))//2

    println(longestSubarray(intArrayOf(1, 0, 1)))
    println(longestSubarray(intArrayOf(1, 0, 1)))
    println(longestSubarray(intArrayOf(1)))//1
    println(longestSubarray(intArrayOf(0)))//0
    println(longestSubarray(intArrayOf(1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1)))

}

fun findMaxAverage(nums: IntArray, k: Int): Double {
    var maxSum = nums.take(k).sum()
    var currentSum = maxSum

    for (i in k until nums.size) {
        currentSum = currentSum - nums[i - k] + nums[i]
        maxSum = maxOf(maxSum, currentSum)
    }

    return maxSum.toDouble() / k
}


fun maxVowels(s: String, k: Int): Int {
    var max = 0

    for (char in s.take(k)) {
        if (char.isVowel()) {
            max++
        }
    }

    var current = max

    for (i in k until s.length) {
        if (s[i].isVowel()) {
            current++
        }
        if (s[i - k].isVowel()) {
            current--
        }
        max = maxOf(max, current)
    }

    return max
}

fun Char.isVowel(): Boolean {
    return this == 'a' || this == 'e' || this == 'i' || this == 'o' || this == 'u'
}

/**
 * 1004. Max Consecutive Ones III
 * повторить !!!
 */

fun longestOnes(nums: IntArray, k: Int): Int {
    var left = 0
    var zeroCount = 0
    var maxLength = 0

    for (right in nums.indices) {
        // если встретили ноль, увеличиваем счётчик
        if (nums[right] == 0) {
            zeroCount++
        }

        // пока нулей в окне больше допустимого k, сужаем окно слева
        while (zeroCount > k) {
            if (nums[left] == 0) {
                zeroCount--
            }
            left++
        }

        // обновляем максимальную длину валидного окна
        maxLength = maxOf(maxLength, right - left + 1)
    }

    return maxLength
}

/**
 * 1493. Longest Subarray of 1's After Deleting One Element
 *
 */

fun longestSubarray(nums: IntArray): Int {
    var left = 0
    var zeroCount = 0
    var maxLen = 0

    for (right in nums.indices) {
        // Если встретили ноль, увеличиваем счетчик
        if (nums[right] == 0) {
            zeroCount++
        }

        // Если нулей больше 1, сдвигаем левую границу
        while (zeroCount > 1) {
            if (nums[left] == 0) {
                zeroCount--
            }
            left++
        }

        // Обновляем максимальную длину (минус 1, так как удаляем один элемент)
        maxLen = maxOf(maxLen, right - left)
    }

    return maxLen
}