package algorithmPatterns.OverlapsingIntervalsExample

import kotlin.math.max
import kotlin.math.min

fun main() {

    println("----")
    merge(
        arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 6),
            intArrayOf(8, 10),
            intArrayOf(15, 18)
        )
    ).forEach { println(it.toUIntArray()) }
    println("----")
    merge(arrayOf(intArrayOf(1, 4), intArrayOf(2, 3))).forEach { println(it.toUIntArray()) }
    println("----")
    merge(arrayOf(intArrayOf(2, 3), intArrayOf(1, 4))).forEach { println(it.toUIntArray()) }
    println("----")
    merge(arrayOf(intArrayOf(1, 4), intArrayOf(4, 5))).forEach { println(it.toUIntArray()) }
    println("----")
    merge(arrayOf(intArrayOf(4, 7), intArrayOf(1, 4))).forEach { println(it.toUIntArray()) }


    println("----")
    println(eraseOverlapIntervals(arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(1, 3)))) //1
    println(eraseOverlapIntervals(arrayOf(intArrayOf(1, 2), intArrayOf(1, 2), intArrayOf(1, 2)))) //2
    println(eraseOverlapIntervals(arrayOf(intArrayOf(1, 2), intArrayOf(2, 3)))) //0
    println(
        eraseOverlapIntervals(
            arrayOf(
                intArrayOf(1, 100),
                intArrayOf(11, 22),
                intArrayOf(1, 11),
                intArrayOf(2, 12)
            )
        )
    ) // 2
    println(eraseOverlapIntervals(arrayOf(intArrayOf(0, 1), intArrayOf(3, 4), intArrayOf(1, 2)))) // 2
}

/**
 * 56. Merge Intervals
 * Attempted
 * Medium
 * Topics
 * premium lock iconCompanies
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping
 * intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 */


fun merge(intervals: Array<IntArray>): Array<IntArray> {
    val resultArray = mutableListOf<IntArray>()
    intervals.sortBy { array -> array[0] }

    resultArray.add(intervals[0])

    for (interval in intervals.drop(1)) {
        val lastInterval = resultArray.last()
        if (lastInterval.last() >= interval.first()) {
            resultArray.removeLast()
            resultArray.add(
                intArrayOf(
                    min(lastInterval.first(), interval.first()),
                    max(lastInterval.last(), interval.last())
                )
            )
        } else {
            resultArray.add(interval)
        }
    }
    return resultArray.toTypedArray()
}


fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    val resultArray = mutableListOf<IntArray>()

    val newArray = intervals + newInterval

    newArray.sortBy { array -> array[0] }

    resultArray.add(newArray[0])

    for (interval in newArray.drop(1)) {
        val lastInterval = resultArray.last()
        if (lastInterval.last() >= interval.first()) {
            resultArray.removeLast()
            resultArray.add(
                intArrayOf(
                    min(lastInterval.first(), interval.first()),
                    max(lastInterval.last(), interval.last())
                )
            )
        } else {
            resultArray.add(interval)
        }
    }
    return resultArray.toTypedArray()
}

fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
    val resultArray = mutableListOf<IntArray>()
    intervals.sortBy { array -> array[1] - array[0] }

    resultArray.add(intervals[0])

    var counter = 0
    for (interval in intervals.drop(1)) {
        val lastInterval = resultArray.last()
        if (intersects(lastInterval, interval)) {
            counter++
        } else {
            resultArray.add(interval)
        }
    }
    return counter
}

fun intersects(a: IntArray, b: IntArray): Boolean {
    val (aStart, aEnd) = a
    val (bStart, bEnd) = b
    // Для закрытых интервалов [aStart, aEnd] и [bStart, bEnd]
    return aStart <= bEnd && bStart <= aEnd
}