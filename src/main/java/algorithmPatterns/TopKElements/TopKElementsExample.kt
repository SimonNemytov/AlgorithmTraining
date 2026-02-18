package algorithmPatterns.TopKElements

import java.util.PriorityQueue

fun main() {

    println(findKthLargest(intArrayOf(1, 3, 24, 2, 8, 0), 1)) //24
    println(findKthLargest(intArrayOf(1, 3, 24, 2, 8, 0), 2)) //8
    println(findKthLargest(intArrayOf(1, 3, 24, 2, 8, 0), 3)) //3

    println(topKFrequent(intArrayOf(1, 1, 1, 1, 2, 2, 2), 1).toUIntArray())
    println(topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), 2).toUIntArray())
    println(topKFrequent(intArrayOf(1, 2, 1, 2, 1, 2, 3, 1, 3, 2), 2).toUIntArray())
    println(topKFrequent(intArrayOf(1), 2).toUIntArray())
    println(topKFrequent(intArrayOf(1), 1).toUIntArray())


    kSmallestPairs(intArrayOf(1, 7, 11), intArrayOf(2, 4, 6), 3).forEach { println(it.toString()) }
    kSmallestPairs(intArrayOf(1, 1, 2), intArrayOf(1, 2, 3), 2).forEach { println(it.toString()) }

}

/**
 * 215. Kth Largest Element in an Array
 *
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Can you solve it without sorting?
 */

fun findKthLargest(nums: IntArray, k: Int): Int {
    val minHeap = PriorityQueue<Int>()

    for (i in nums) {
        minHeap.offer(i)
        if (minHeap.size > k) {
            minHeap.poll()
        }
    }
    return minHeap.poll()
}

/**
 * 347. Top K Frequent Elements
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 */

fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    for (i in nums) {
        if (map.contains(i)) {
            map[i] = map[i]!! + 1
        } else {
            map.put(i, 0)
        }
    }
    val comparatorByValue = compareBy<Map.Entry<Int, Int>> { it.value }

    val heap = PriorityQueue(comparatorByValue)

    for (i in map) {
        heap.offer(i)
        if (heap.size > k) {
            heap.poll()
        }
    }
    var result = mutableListOf<Int>()
    for (i in heap) {
        result.add(i.key)
    }
    return result.toIntArray()
}

/**
 *  373. Find K Pairs with Smallest Sums
 * Solved
 * Medium
 * Topics
 * premium lock iconCompanies
 *
 * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
 *
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 *
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 */

/* Complexity:
 * Time O(kLogk) and Space O(k);
 */
fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
    // pq of pair(index1, index2) where the pairs are compared by nums1[index1] + nums2[index2]
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { nums1[it.first] + nums2[it.second] })
    val result = mutableListOf<List<Int>>()
    pq.offer(Pair(0, 0))
    while (result.size < k && pq.isNotEmpty()) {
        val (index1, index2) = pq.poll()
        result.add(listOf(nums1[index1], nums2[index2]))
        if (index2 + 1 < nums2.size) {
            pq.offer(Pair(index1, index2 + 1))
        }
        if (index2 == 0 && index1 + 1 < nums1.size) {
            pq.offer(Pair(index1 + 1, 0))
        }
    }
    return result
}
