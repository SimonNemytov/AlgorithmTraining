package algorithmPatterns.TwoPointers

import kotlin.math.min

fun main() {

    println(twoSum(intArrayOf(1, 2, 4, 7, 9), 9).asUIntArray())//1,3
    println(twoSum(intArrayOf(1, 3, 4, 7, 9), 9).asUIntArray())//0,4

    println(threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)))
    println(threeSum(intArrayOf(-1, 0, 1, 0)))

    println(maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)))//49
    println(maxArea(intArrayOf(1, 100, 2, 2)))//4
    println(maxArea(intArrayOf(1, 100, 2, 2)))//4
    println(maxArea(intArrayOf(1, 2, 1, 2, 4, 2, 1)))//8
    println(maxArea(intArrayOf(0, 1)))//0
    println(maxArea(intArrayOf(1, 1)))//0
    println(maxArea(intArrayOf(1, 0)))//0
}


/**
 * Пример задачи:
 *
 * Найти два числа в отсортированном массиве, сумма которых равна заданному значению.
 *
 * Пример:
 *
 *     Вход: nums = [1, 2, 3, 4, 6], target = 6
 *
 *     Выход: [1, 3] (индексы)
 *
 *
 */

fun twoSum(nums: IntArray, target: Int): IntArray {
    var left = 0
    var right = nums.size - 1

    while (left <= right) {
        val sum = nums[left] + nums[right]
        if (sum < target) {
            left++
        } else if (sum > target) {
            right--
        } else {
            break
        }
    }
    return intArrayOf(left + 1, right + 1)
}

/**
 * Given an integer array nums, return all the triplets
 * [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
 * and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 */

fun threeSum(nums: IntArray): List<List<Int>> {
    nums.sort()

    var setOfVariants = mutableSetOf<List<Int>>()

    for ((index, num) in nums.withIndex()) {

        var left = 0
        var right = nums.size - 1

        while (left < right) {
            if (left == index) {
                left++
            } else if (right == index) {
                right--
            }
            if (nums[left] + nums[right] + num < 0) {
                left++
            } else if (nums[left] + nums[right] + num > 0) {
                right--
            } else {
                if (left != right) {
                    setOfVariants.add(listOf(nums[left], nums[right], num).sorted())
                    left++
                    right--
                }
            }
        }
    }

    return setOfVariants.toList()
}

/**
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two
 * endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 */

fun maxArea(height: IntArray): Int {
    var left = 0
    var right = height.size - 1
    var maxAmount = 0

    while (left < right) {
        val tempAmount = min(height[left], height[right]) * (right - left)
        maxAmount = maxOf(maxAmount, tempAmount)

        if (height[left] < height[right]) {
            left++
        } else {
            right--
        }
    }
    return maxAmount
}