package LeetCode75.twoPointers

import LeetCode75.arraysAndStrings.kidsWithCandies
import kotlin.math.min

fun main() {
    moveZeroes(intArrayOf(0, 1, 0, 3, 12))
    moveZeroes(intArrayOf(1, 2))
    moveZeroes(intArrayOf(1, 0, 2))
    moveZeroes(intArrayOf(1, 0, 2, 0, 0, 0))
    moveZeroes(intArrayOf(1, 0, 2, 0, 0, 0, 12))

    println(isSubsequence("abc", "ahbgdc"))
    println(isSubsequence("abx", "ahbgdc"))
    println(isSubsequence("", "ahbgdc"))


    println(maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)))

    println(maxOperations(intArrayOf(1, 2), 3))//1
    println(maxOperations(intArrayOf(1, 2, 3), 3))//1
    println(maxOperations(intArrayOf(1, 2, 1, 2), 3)) //2
    println(maxOperations(intArrayOf(1, 2, 1, 2, 4), 3)) //2
    println(maxOperations(intArrayOf(1, 2, 3, 4), 5)) //2
    println(maxOperations(intArrayOf(3, 1, 3, 4, 3), 6)) //1
}


/**
 * 283. Move Zeroes
 */

fun moveZeroes(nums: IntArray): Unit {
    if (nums.size == 1) {
        return
    }
    var zeroPointer = 0
    var numPointer = 0

    while (zeroPointer < nums.size && numPointer < nums.size) {
        if (nums[zeroPointer] != 0) {
            zeroPointer++
            numPointer = zeroPointer
        } else {
            if (nums[numPointer] != 0) {
                nums[zeroPointer] = nums[numPointer]
                nums[numPointer] = 0
            }
            numPointer++
        }
    }
    println(nums.toUIntArray())
}

/**
 * 392. Is Subsequence
 */

fun isSubsequence(s: String, t: String): Boolean {
    if (s == "") {
        return true
    }
    var i = 0

    for (char in t) {
        if (char == s[i]) {
            i++
            if (i == s.length) {
                return true
            }
        }
    }

    return false
}

fun maxArea(height: IntArray): Int {
    var left = 0
    var right = height.size - 1
    var area = 0

    while (left < right) {
        val temp = min(height[left], height[right]) * (right - left)
        if (height[left] < height[right]) {
            left++
        } else {
            right--
        }
        area = maxOf(area, temp)
    }
    return area
}


fun maxOperations(nums: IntArray, k: Int): Int {
    val freqMap = mutableMapOf<Int, Int>()
    var count = 0

    for (num in nums) {
        val complement = k - num

        if (freqMap.getOrDefault(complement, 0) > 0) {
            count++
            freqMap[complement] = freqMap[complement]!! - 1
        } else {
            freqMap[num] = freqMap.getOrDefault(num, 0) + 1
        }
    }

    return count
}

