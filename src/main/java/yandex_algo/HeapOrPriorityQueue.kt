package yandex_algo

import topKFrequent
import java.util.PriorityQueue

fun main() {
    println("---topKFrequent---")
    println(topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), 2).toUIntArray()) // 1,2
    println(topKFrequent(intArrayOf(1, 2, 1, 2, 1, 2, 3, 1, 3, 2), 2).toUIntArray())// 1,2
    println(topKFrequent(intArrayOf(1, 2), 2).toUIntArray())// 1,2
    println(topKFrequent(intArrayOf(1, 2, 3, 3, 2, 2), 2).toUIntArray())// 2,3

    println("---merge----")
    merge(arrayOf(intArrayOf(1, 3), intArrayOf(2, 4))).forEach { println(it.toUIntArray()) } //1,4
    merge(arrayOf(intArrayOf(2, 4), intArrayOf(1, 3))).forEach { println(it.toUIntArray()) } //1,4
    merge(arrayOf(intArrayOf(1, 10), intArrayOf(2, 3), intArrayOf(5, 6))).forEach { println(it.toUIntArray()) } //1,10

    merge(
        arrayOf(
            intArrayOf(2, 4),
            intArrayOf(1, 3),
            intArrayOf(6, 7),
            intArrayOf(9, 12),
            intArrayOf(10, 15)
        )
    ).forEach { println(it.toUIntArray()) } // 1,4 6,7 9,15

    println("---topKFrequent---")
    println(topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), 2).toUIntArray()) // 1,2
    println(topKFrequent(intArrayOf(1, 2, 1, 2, 1, 2, 3, 1, 3, 2), 2).toUIntArray())// 1,2
    println(topKFrequent(intArrayOf(1, 2), 2).toUIntArray())// 1,2
    println(topKFrequent(intArrayOf(1, 2, 3, 3, 2, 2), 2).toUIntArray())// 2,3

}

/**
 * 347. Top K Frequent Elements
 */

fun topKFrequent(nums: IntArray, k: Int): IntArray {
    var freeq = mutableMapOf<Int, Int>()

    for (n in nums) {
        freeq[n] = freeq.getOrDefault(n, 0) + 1
    }
    var result = IntArray(k)
    var queue = PriorityQueue<MutableMap.MutableEntry<Int, Int>>(compareByDescending { it.value })
    for (pair in freeq) {
        queue.add(pair)
    }
    for (i in 0 until k) {
        result[i] = queue.poll().key
    }
    return result
}


/**
 * 56. Merge Intervals
 *
 * Формула для двух интервалов a, b и c, d
 * Интервалы пересекаются, если выполняется ОДНО из условий:
 *
 * a <= d И b >= c
 */

fun merge(intervals: Array<IntArray>): Array<IntArray> {
    intervals.sortBy { it -> it[0] }

    var queue = ArrayDeque<IntArray>()

    queue.add(intervals[0])
    for (index in 1 until intervals.size) {
        val prev = queue.last()
        val curr = intervals[index]
        if (prev[0] <= curr[1] && prev[1] >= curr[0]) {
            queue.removeLast()
            val result = IntArray(2)
            result[0] = minOf(prev[0], curr[0])
            result[1] = maxOf(prev[1], curr[1])
            queue.addLast(result)
        } else {
            queue.addLast(curr)
        }
    }

    return queue.toTypedArray()
}