package LeetCode75.prefixSum

fun main() {

    println(largestAltitude(intArrayOf(-5, 1, 5, 0, -7)))
    println(largestAltitude(intArrayOf(-5, 1)))
    println(largestAltitude(intArrayOf(-4, -3, -2, -1, 4, 3, 2)))

    println(pivotIndex(intArrayOf(1, 7, 3, 6, 5, 6)))

}

/**
 * 1732. Find the Highest Altitude
 */

fun largestAltitude(gain: IntArray): Int {
    val array = IntArray(gain.size + 1)
    array[0] = 0

    for ((i, n) in gain.withIndex()) {
        array[i + 1] = n + array[i]
    }

    return array.max()
}

/**
 * 724. Find Pivot Index
 */

fun pivotIndex(nums: IntArray): Int {
    val array = IntArray(nums.size)
    val arrayOpposite = IntArray(nums.size)

    for ((i, n) in nums.withIndex()) {
        if (i == 0) {
            array[i] = n
        } else {
            array[i] = array[i - 1] + n
        }
    }

    for (i in nums.size - 1 downTo 0) {
        if (i == nums.size - 1) {
            arrayOpposite[i] = nums[i]
        } else {
            arrayOpposite[i] = arrayOpposite[i + 1] + nums[i]
        }
    }

    for (i in array.indices) {
        if (array[i] == arrayOpposite[i]) {
            return i
        }
    }
    return -1
}