package algorithmPatterns.MonotonicStack

fun main() {

    /**
     * это пиздец какой то, я вообще не пойму что это
     */

    println(findNext(intArrayOf(2, 1, 2, 4, 3)).toUIntArray())
}

/**
 * Для каждого элемента массива найти следующий больший элемент.
 * Если такого нет — вывести -1.
 *
 * Пример:
 *
 *     Вход: nums = [2, 1, 2, 4, 3]
 *
 *     Выход: [4, 2, 4, -1, -1]
 */

fun findNext(nums: IntArray): IntArray {

    var result = IntArray(nums.size)
    result.fill(-1)

    var stack: ArrayDeque<Int> = ArrayDeque()

    for (i in nums.indices) {
        println("Current index : $i")
        println("Current num : " + nums[i])
        while (stack.isNotEmpty() && nums[i] > nums[stack.last()]) {
            println("Last index in stack " + stack.last())
            println("Last value in stack " + nums[stack.last()])
            val removeLast = stack.removeLast()
            result[removeLast] = nums[i]
        }
        println("Added index " + i + " value is " + nums[i])
        stack.addLast(i)
    }
    return result
}

class Solution {
    fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
        val nextGreater = mutableMapOf<Int, Int>()
        val stack = ArrayDeque<Int>()

        for (num in nums2) {
            while (stack.isNotEmpty() && num > stack.last()) {
                val smaller = stack.removeLast()
                nextGreater[smaller] = num
            }
            stack.addLast(num)
        }

        return nums1.map { nextGreater.getOrDefault(it, -1) }.toIntArray()
    }
}

