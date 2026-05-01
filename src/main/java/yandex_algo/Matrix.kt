package yandex_algo

fun main() {

    println(searchMatrix(arrayOf(intArrayOf(1, 3, 5, 7)), 3)) //true
    println(searchMatrix(arrayOf(intArrayOf(1, 3, 5, 7)), 4)) //false
    println(searchMatrix(arrayOf(intArrayOf(1, 1)), 1)) //false
    println(
        searchMatrix(
            arrayOf(intArrayOf(1, 3, 5, 7), intArrayOf(10, 11, 16, 20), intArrayOf(23, 30, 34, 60)),
            4
        )
    ) //false

}

fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    if (matrix.isEmpty() || matrix[0].isEmpty()) return false

    val rows = matrix.size
    val cols = matrix[0].size

    var left = 0
    var right = (rows * cols) - 1

    while (left <= right) {
        val mid = left + (right - left) / 2

        val midValue = matrix[mid / cols][mid % rows]

        when {
            midValue == target -> return true
            midValue < target -> left = mid + 1  // Ищем в правой половине
            else -> right = mid - 1             // Ищем в левой половине
        }
    }

    return false
}


/**
 * 54. Spiral Matrix
 */


//fun spiralOrder(matrix: Array<IntArray>): List<Int> {
//
//}