package LeetCode75.SlidingWindow

fun main() {

    println(findMaxAverage(intArrayOf(5), 1))//5.0
    println(findMaxAverage(intArrayOf(5, 10), 1))//10.0
    println(findMaxAverage(intArrayOf(1, 12, -5, -6, 50, 3), 4))//10.0
    println(findMaxAverage(intArrayOf(0, 4, 0, 3, 2), 1))

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
        if (s[i - 1].isVowel()) {
            current--
        }
        max = maxOf(max, current)
    }

    return max
}

fun Char.isVowel(): Boolean {
    return this == 'a' || this == 'e' || this == 'i' || this == '0' || this == 'u'
}
