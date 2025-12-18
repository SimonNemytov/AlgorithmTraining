package algorithmPatterns.PrefixSum

fun main() {
//    println(findSumDiapason(arrayOf(1, 3, 7, 9, 2), 1, 4)) // 21
//    println(findSumDiapason(arrayOf(1, 3, 7, 9, 2), 0, 1)) // 4
//    println(findSumDiapason(arrayOf(1, 3, 7, 9, 2), 700, 1)) // 0


//    println(findMaxLength(intArrayOf(0, 1))) //2
//    println(findMaxLength(intArrayOf(0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1))) //7
//    println(findMaxLength(intArrayOf(0, 1, 1, 1, 1, 1, 0, 0, 0))) //6
//    println(findMaxLength(intArrayOf(0, 1, 1, 1, 0, 1, 0))) //6
//    println(findMaxLength(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1))) //6

    println(subarraySum(intArrayOf(1, 2, 3), 3)) //2
//    println(subarraySum(intArrayOf(1, 1, 2), 2)) //2
//    println(subarraySum(intArrayOf(1, 1, 2, 6), 2)) //2
//    println(subarraySum(intArrayOf(1, 1, 2, 6, 1, 1, 2), 2)) //4
//    println(subarraySum(intArrayOf(1, 1, 1, 2, 1, 1, 6), 2)) //4
//    println(subarraySum(intArrayOf(1, 2, 3, 2, 1, 1, 1), 3)) //4
//    println(subarraySum(intArrayOf(2, 3, 5, 2, 3, 6), 10)) //3
//    println(subarraySum(intArrayOf(2, 2, 5, 17, 2, 3, 6), 10)) //0
//    println(subarraySum(intArrayOf(1), 1)) //0
//    println(subarraySum(intArrayOf(1, -1, 0), 0)) //3
    println(subarraySum(intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 0))
}


/**
 * Пример задачи:
 *
 * Дан массив nums. Вычислите сумму элементов в диапазоне [i, j].
 *
 * Пример:
 *
 *     Вход: nums = [1, 2, 3, 4, 5, 6], i = 1, j = 3
 *
 *     Выход: 9
 */

fun findSumDiapason(array: Array<Int>, i: Int, j: Int): Int {
    if (i < 0 || j < 0 || i > array.size - 1 || j > array.size - 1) return 0
    if (i > j) return 0
    var resultArray = mutableListOf<Int>()
    for ((index, i) in array.withIndex()) {
        if (index == 0) {
            resultArray.add(i)
        } else {
            resultArray.add(i + resultArray[index - 1])
        }
    }
    return if (i > 0) resultArray[j] - resultArray[i - 1] else resultArray[j]
}

/**
 * Суть задачи
 *
 * Вам дан массив, состоящий только из 0 и 1.
 * Вам нужно найти самый длинный непрерывный
 * подмассив (отрезок внутри массива),
 * в котором количество нулей и единиц одинаково.
 */

fun findMaxLength(nums: IntArray): Int {
    val countPerIndex = mutableMapOf<Int, Int>()
    countPerIndex[0] = -1
    var maxRange = 0
    var count = 0;
    for ((index, num) in nums.withIndex()) {
        if (num == 0) {
            count--
        } else {
            count++
        }
        if (countPerIndex.contains(count)) {
            maxRange = Math.max(maxRange, index - (countPerIndex[count] ?: 0))
        } else {
            countPerIndex[count] = index
        }
    }
    return maxRange
}

/**
 * Имея массив целых чисел nums и целое число k,
 * возвращаем общее количество подмассивов, сумма которых равна k.
 *
 * Подмассив — это непрерывная непустая последовательность элементов внутри массива.
 */

fun subarraySum(nums: IntArray, k: Int): Int {
    var count = 0
    var currentSum = 0
    var sumToCount = mutableMapOf<Int, Int>()

    sumToCount[0] = 1

    for (num in nums) {
        currentSum += num
        val diff = currentSum - k

        if (sumToCount.contains(diff)) {
            count += sumToCount[diff] ?: 0
        }

        sumToCount[currentSum] = (sumToCount[currentSum] ?: 0) + 1

    }
    return count
}