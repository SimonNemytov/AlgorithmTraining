package yandex_algo

fun main() {

//    println(search(intArrayOf(1), 1)) //0
//    println(search(intArrayOf(), 1)) //-1
    println(search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0))//4
    println(search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3))//-1
    println(search(intArrayOf(1), 0)) //-1
    println(search(intArrayOf(5, 0, 1, 4), 1)) //2
    println(search(intArrayOf(1, 3), 0)) //-1
}

/**
 * 33. Search in Rotated Sorted Array
 */

fun search(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1

    if (nums.size == 1) {
        if (nums[0] == target) {
            return 0
        } else return -1
    }

    while (left < right) {
        val mid = left + (right - left) / 2

        if (nums[mid] == target) {
            return mid
        }

        if (nums[left] == target) {
            return left
        }

        if (nums[right] == target) {
            return right
        }

        if (nums[left] <= nums[mid]) {
            if (target >= nums[left] && target < nums[mid]) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        } else {
            if (target > nums[mid] && target <= nums[right]) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
    }
    return -1
}


/**
 * 33. Search in Rotated Sorted Array
 * литкод ее принял, но это вообще не бинарный поиск
 */

fun search2(nums: IntArray, target: Int): Int {
    if (nums.size == 1) {
        return if (nums[0] == target) 0 else -1
    }
    var left = 0
    var right = nums.size - 1
    while (left < right) {
        if (nums[left] == target) {
            return left
        } else if (nums[right] == target) {
            return right
        } else if (nums[left] < target) {
            left++
        } else if (nums[right] > target) {
            right--
        } else {
            left++
            right--
        }
    }
    return -1
}
