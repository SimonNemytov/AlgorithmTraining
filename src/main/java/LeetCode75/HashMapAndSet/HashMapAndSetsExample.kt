package LeetCode75.HashMapAndSet

fun main() {

    println(findDifference(intArrayOf(1, 2, 3), intArrayOf(2, 4, 6))) // [[1, 3], [4, 6]]
    println(findDifference(intArrayOf(1, 2, 3, 3), intArrayOf(1, 2, 2, 1))) // [[3], []]
    println(findDifference(intArrayOf(1, 2, 5, 5), intArrayOf(3, 4, 6, 8))) //  [[1, 2, 5], [3, 4, 6, 8]]
    println(findDifference(intArrayOf(1, 2, 3), intArrayOf(1, 2, 3))) // [[], []]
    println(findDifference(intArrayOf(1, 2, 3, 5), intArrayOf(1, 2, 3))) // [[5], []]
    println(findDifference(intArrayOf(1, 2, 3), intArrayOf(1, 2, 3, 5))) // [[], [5]]


    println(uniqueOccurrences(intArrayOf(1, 2, 2, 1, 1, 3))) //true
    println(uniqueOccurrences(intArrayOf(1, 2))) //false
    println(uniqueOccurrences(intArrayOf(1, 2, 3, 2))) //false
    println(uniqueOccurrences(intArrayOf(1, 2, 3, 2))) //false
    println(uniqueOccurrences(intArrayOf(-3, 0, 1, -3, 1, 1, 1, -3, 10, 0))) //true
    println(uniqueOccurrences(intArrayOf(2, 1, 1, 5, 5, 5, 5))) //true

    println("----closeStrings----")
    println(closeStrings("abc", "bca")) // true
    println(closeStrings("a", "ab")) // false
    println(closeStrings("cabbba", "abbccc")) // true
    println(closeStrings("aa", "bb")) // false
    println(closeStrings("aac", "bbe")) // false
    println(closeStrings("aac", "bbc")) // false
}

/**
 * 2215. Find the Difference of Two Arrays
 */

fun findDifference(nums1: IntArray, nums2: IntArray): List<List<Int>> {
    var result: MutableList<MutableList<Int>> = mutableListOf()
    result.add(mutableListOf())
    result.add(mutableListOf())
    var set1 = nums1.toSet()
    var set2 = nums2.toSet()

    for (n in nums1) {
        if (!set2.contains(n)) {
            if (!result[0].contains(n)) {
                result[0].add(n)
            }
        }
    }

    for (n in nums2) {
        if (!set1.contains(n)) {
            if (!result[1].contains(n)) {
                result[1].add(n)
            }
        }
    }

    return result
}

/**
 * 1207. Unique Number of Occurrences
 */

fun uniqueOccurrences(arr: IntArray): Boolean {

    val numToCount = mutableMapOf<Int, Int>()

    for (n in arr) {
        if (numToCount.contains(n)) {
            numToCount[n] = numToCount[n]!! + 1
        } else {
            numToCount.put(n, 1)
        }
    }

    return numToCount.values.toSet().size == numToCount.size
}

fun closeStrings(word1: String, word2: String): Boolean {

    if (word1.length != word2.length) {
        return false
    }

    if (word1.toSortedSet() != word2.toSortedSet()) {
        return false
    }

    val numToFrequency1 = mutableMapOf<Char, Int>()
    val numToFrequency2 = mutableMapOf<Char, Int>()

    for (c in word1) {
        if (numToFrequency1.contains(c)) {
            numToFrequency1[c] = numToFrequency1[c]!! + 1
        } else {
            numToFrequency1.put(c, 1)
        }
    }

    for (c in word2) {
        if (numToFrequency2.contains(c)) {
            numToFrequency2[c] = numToFrequency2[c]!! + 1
        } else {
            numToFrequency2.put(c, 1)
        }
    }

    val freq1 = numToFrequency1.values.sorted()
    val freq2 = numToFrequency2.values.sorted()

    if (freq1 == freq2) {
        return true
    }

    return false
}