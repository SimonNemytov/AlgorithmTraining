package yandex_algo

fun main() {
    println(intersection(intArrayOf(1, 2, 2, 1), intArrayOf(2, 2)).toUIntArray())

    println(groupAnagrams(arrayOf("eat","tea","tan","ate","nat","bat"))) //[["bat"],["nat","tan"],["ate","eat","tea"]]
}

fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
    val n1 = nums1.toSet()
    var result = mutableSetOf<Int>()
    for (n in nums2) {
        if (n1.contains(n)) {
            result.add(n)
        }
    }
    return result.toIntArray()
}

//fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
//    val freeq = mutableSetOf<Int>()
//    var result = mutableListOf<Int>()
//    for (n in nums1) {
//        freeq.add(n)
//    }
//    for (n in nums2) {
//        if (freeq.contains(n)) {
//            result.add(n)
//            freeq.remove(n)
//        }
//    }
//    return result.toIntArray()
//}

/**
 * 49. Group Anagrams
 */
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    var map = mutableMapOf<String, MutableList<String>>()

    for (s in strs) {
        val pattern = s.toList().sorted().toString()
        if (map.contains(pattern)) {
            map[pattern]?.add(s)
        } else {
            map[pattern] = mutableListOf(s)
        }
    }
    return map.map { it.value.toList() }
}