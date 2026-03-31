package yandex_algo


fun main() {

    merge(intArrayOf(1, 2, 3, 0, 0, 0), 3, intArrayOf(2, 5, 6), 3)

    println(maxOperations(intArrayOf(1, 2, 3, 4), 5))
    println(maxOperations(intArrayOf(3, 1, 3, 4, 3), 6))

    println(threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)))

    println(intersection(intArrayOf(1, 2, 2, 1), intArrayOf(2, 2)).toUIntArray())


    println("задача с яндекса ")
    println(findKClosest(intArrayOf(2, 3, 5, 7, 11), 3, 2).toUIntArray())
    println(findKClosest(intArrayOf(4, 12, 15, 15, 24), 1, 3).toUIntArray())
    println(findKClosest(intArrayOf(2, 3, 5, 7, 11), 2, 2).toUIntArray())

    val data = intArrayOf(1, 5, 10) // index=2 это число 10
    println(findKClosest(data, index = 2, k = 3).toUIntArray())


    println("----maxArea---")

    println(maxArea(intArrayOf(1, 3, 3, 1))) // 3
    println(maxArea(intArrayOf(3, 5, 2, 4))) //9
    println(maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7))) //49


    println("----maxOperations---")
    println(maxOperations(intArrayOf(1, 2, 3), 3)) //1
    println(maxOperations(intArrayOf(0, 1, 2, 3), 3)) // 2
    println(maxOperations(intArrayOf(0, 1, 2, 3), 3)) // 2
    println(maxOperations(intArrayOf(1, 2, 3, 4), 5)) // 2
    println(maxOperations(intArrayOf(3, 1, 3, 4, 3), 6)) // 1

    println("---reverseWords---")
    println(reverseWords("434df.  234 %vdsdf fsdf#445       fs f~234//w   "))
    println(reverseWords("the sky is blue"))
    println(reverseWords("  hello world  "))
    println(reverseWords("a good   example"))
}

/**
 * 88. Merge Sorted Array
 * плохо понял, надо пересмотреть
 */

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    var p1 = m - 1
    var p2 = n - 1
    var p = m + n - 1

    while (p2 >= 0) {
        if (p1 >= 0 && nums1[p1] > nums2[p2]) {
            nums1[p] = nums1[p1]
            p1--
        } else {
            nums1[p] = nums2[p2]
            p2--
        }
        p--
    }
}

fun merge2(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    var p1 = m - 1
    var p2 = n - 1
    var p = m + n - 1

    while (p2 >= 0) {
        if (p1 >= 0 && nums1[p1] > nums2[p2]) {
            nums1[p] = nums1[p1]
            p1--
        } else {
            nums1[p] = nums2[p2]
            p2--
        }
        p--
    }

}

/**
 * 9. Max Number of K-Sum Pairs
 * понял но надо еще раз потрогать
 *
 * O(n)
 */
//fun maxOperations(nums: IntArray, k: Int): Int {
//    var freqMap = mutableMapOf<Int, Int>()
//    var count = 0
//
//    for (n in nums) {
//        val diff = k - n
//        if (freqMap.getOrDefault(diff, 0) > 0) {
//            count++
//            freqMap[diff] = freqMap[diff]!! - 1
//        } else {
//            freqMap[n] = (if (freqMap.containsKey(n)) freqMap[n] else 0)!! + 1
//        }
//    }
//    return count
//}

fun maxOperations(nums: IntArray, k: Int): Int {
    nums.sort()
    var left = 0
    var right = nums.size - 1
    var counter = 0

    while (left < right) {
        val sum = nums[left] + nums[right]
        if (sum < k) {
            left++
        } else if (sum > k) {
            right--
        } else {
            counter++
            left++
            right--
        }
    }
    return counter
}



/**
 * 15. 3Sum
 *
 * O(n^2)
 */
fun threeSum(nums: IntArray): List<List<Int>> {
    nums.sort()

    var setOfVariants = mutableSetOf<List<Int>>()

    for ((i, n) in nums.withIndex()) {
        if (i != nums.lastIndex && i != nums.lastIndex - 1) {
            var right = nums.lastIndex
            var left = i + 1

            while (left < right) {
                if ((nums[left] + nums[right] + n) < 0) {
                    left++
                } else if ((nums[left] + nums[right] + n) > 0) {
                    right--
                } else {
                    setOfVariants.add(listOf(n, nums[right], nums[left]).sorted())
                    left++
                    right--
                }
            }
        }
    }
    return setOfVariants.toList()
}

/**
 * 4sum
 * не решил
 */



/**
 * Дан отсортированный по неубыванию список целых чисел data, индекс элемента
 * index и целое число k.
 * Необходимо вернуть в любом порядке k  чисел из списка, которые являются
 * ближайшими по значению к элементу data[index].
 *
 * Ограничения:
 * - Размер списка 1 <= len(data) <= 10^6 ;
 * - Элементы списка: -10^9 <= data[i] <= 10^9 ;
 * - Число 0 <= k <= len(data) ;
 * - Индекс элемента 0 <= index < len(data) .
 *
 *
 * findKClosest(data=[2, 3, 5, 7, 11], index=3, k=2) -> [5, 7]
 * findKClosest(data=[4, 12, 15, 15, 24], index=1, k=3) -> [12, 15, 15]
 * findKClosest(data=[2, 3, 5, 7, 11], index=2, k=2) -> [3, 5] или [5, 7]
 */

fun findKClosest(array: IntArray, index: Int, k: Int): IntArray {

    if (k == 0) {
        return intArrayOf()
    }
    if (k == 1) {
        return intArrayOf(array[index])
    }
    val valueOnIndex = array[index]

    val result = mutableListOf(valueOnIndex)

    var left = index - 1
    var right = index + 1

    while (result.size < k) {
        if (left >= 0 && right < array.size) {
            val diffLeft = valueOnIndex - array[left]
            val diffRight = array[right] - valueOnIndex
            if (diffRight < diffLeft) {
                result.add(array[right])
                right++
            } else {
                result.add(array[left])
                left--
            }
        } else if (right < array.size) {
            result.add(array[right])
            right++
        } else if (left >= 0) {
            result.add(array[left])
            left--
        } else break
    }
    result.sort()
    return result.toIntArray()
}

/**
 * 11. Container With Most Water (Разбор твоего вопроса):
 */

fun maxArea(height: IntArray): Int {

    var left = 0
    var right = height.size - 1
    var result = 0

    while (left < right) {
        val high = minOf(height[left], height[right])
        val size = right - left
        result = maxOf(result, high * size)
        if (height[left] < height[right]) {
            left++
        } else {
            right--
        }
    }
    return result
}


fun reverseWords(s: String): String {

    var result = s.replace(Regex(" +"), " ").trim()

    val array = result.split(" ").toMutableList()

    var left = 0
    var right = array.size - 1

    while (left < right) {
        val temp = array[right]
        array[right] = array[left]
        array[left] = temp
        left++
        right--
    }

    val res = array.joinToString(separator = " ").trim()
    return res
}

