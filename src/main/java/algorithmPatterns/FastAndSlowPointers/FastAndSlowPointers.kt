package algorithmPatterns.FastAndSlowPointers

fun main() {

    println(isHappy(22888921))
}

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 */

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun hasCycle(head: ListNode?): Boolean {
    var fast = head
    var slow = head

    while (fast != null && fast.next != null) {
        fast = fast.next?.next
        slow = slow?.next

        if (fast == slow) {
            return true
        }
    }
    return false
}


fun isHappy(n: Int): Boolean {

    var fast = getNextStep(n)
    var slow = getNextStep(getNextStep(n))

    while (slow != fast) {
        if (fast == 1) {
            return true
        }
        slow = getNextStep(slow)
        fast = getNextStep(getNextStep(fast))
    }
    return slow == 1

}

/**
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 *     Starting with any positive integer, replace the number by the sum of
 *     the squares of its digits.
 *     Repeat the process until the number equals 1 (where it will stay),
 *     or it loops endlessly in a cycle which does not include 1.
 *     Those numbers for which this process ends in 1 are happy.
 *
 * Return true if n is a happy number, and false if not.
 */
fun getNextStep(n: Int): Int {
    var output = 0
    var num = n
    while (num > 0) {
        var digit = num % 10
        output += digit * digit
        num = num / 10
    }
    return output
}


/**
 * 287. Find the Duplicate Number
 * Medium
 * Topics
 * premium lock iconCompanies
 *
 * Given an array of integers nums containing n + 1
 * integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums,
 * return this repeated number.
 *
 * You must solve the problem without modifying the array nums
 * and using only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * Example 3:
 *
 * Input: nums = [3,3,3,3,3]
 * Output: 3
 *
 *
 */

fun findDuplicate(nums: IntArray): Int {

    var slow = nums[0]
    var fast = nums[0]

    while (true) {
        slow = nums[slow]
        fast = nums[nums[fast]]
        if (slow == fast) {
            break
        }
    }

    fast = nums[0]
    while (slow != fast) {
        slow = nums[slow];
        fast = nums[fast];
    }
    return slow;

}