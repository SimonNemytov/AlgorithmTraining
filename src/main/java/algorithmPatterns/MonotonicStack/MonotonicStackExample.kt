package algorithmPatterns.MonotonicStack

fun main() {

    /**
     * это пиздец какой то, я вообще не пойму что это
     */

    println(findNext(intArrayOf(2, 1, 2, 4, 3)).toUIntArray())


    println(dailyTemperatures(intArrayOf(73, 74, 75, 71, 69, 72, 76, 73)).toUIntArray())

    println(largestRectangleArea(intArrayOf(1, 2)))
}

/**
 * Для каждого элемента массива найти следующий больший элемент.
 * Если такого нет — вывести -1.
 *
 * Пример:
 *
 *     Вход: nums = [2, 1, 2, 4, 3]
 *
 *     Выход: [4, 2, 4, -1, -1]
 */

fun findNext(nums: IntArray): IntArray {

    var result = IntArray(nums.size)
    result.fill(-1)

    var stack: ArrayDeque<Int> = ArrayDeque()

    for (i in nums.indices) {
        println("Current index : $i")
        println("Current num : " + nums[i])
        while (stack.isNotEmpty() && nums[i] > nums[stack.last()]) {
            println("Last index in stack " + stack.last())
            println("Last value in stack " + nums[stack.last()])
            val removeLast = stack.removeLast()
            result[removeLast] = nums[i]
        }
        println("Added index " + i + " value is " + nums[i])
        stack.addLast(i)
    }
    return result
}

/**
 * The next greater element of some element x in an array is the first greater
 * element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2,
 * where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that
 * nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2.
 * If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the
 * next greater element as described above.
 */

fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
    val nextGreater = mutableMapOf<Int, Int>()
    val stack = ArrayDeque<Int>()

    for (num in nums2) {
        while (stack.isNotEmpty() && num > stack.last()) {
            val smaller = stack.removeLast()
            nextGreater[smaller] = num
        }
        stack.addLast(num)
    }

    return nums1.map { nextGreater.getOrDefault(it, -1) }.toIntArray()
}


fun dailyTemperatures(temperatures: IntArray): IntArray {
    var result = IntArray(temperatures.size)

    result.fill(0)

    val stack = ArrayDeque<Int>()

    for (i in temperatures.indices) {
        while (stack.isNotEmpty() && temperatures[i] > temperatures[stack.last()]) {
            val last = stack.removeLast()
            result[last] = i - last
        }
        stack.addLast(i)
    }
    return result
}

fun largestRectangleArea(heights: IntArray): Int {
    val n = heights.size
    if (n == 0) return 0

    // Храним индексы границ
    val leftBoundary = IntArray(n)  // индекс ближайшего меньшего слева
    val rightBoundary = IntArray(n) // индекс ближайшего меньшего справа

    // Инициализация:
    // Если нет меньшего слева, будем считать, что граница -1 (левее начала массива)
    // Если нет меньшего справа, будем считать, что граница n (правее конца массива)
    for (i in 0 until n) {
        leftBoundary[i] = -1
        rightBoundary[i] = n
    }

    val stack = ArrayDeque<Int>()

    // ПРОХОД 1: Находим правые границы (ближайший меньший справа)
    // Идем слева направо
    for (currentIndex in 0 until n) {
        val currentHeight = heights[currentIndex]

        // Пока стек не пуст И текущий элемент МЕНЬШЕ элемента на вершине стека
        while (stack.isNotEmpty() && currentHeight < heights[stack.last()]) {
            // Для элемента на вершине стека мы нашли правую границу!
            val poppedIndex = stack.removeLast()  // индекс элемента, для которого нашли ответ
            rightBoundary[poppedIndex] = currentIndex  // текущий индекс - это правая граница
        }

        // Добавляем текущий индекс в стек
        stack.addLast(currentIndex)
    }

    // Очищаем стек для следующего прохода
    stack.clear()

    // ПРОХОД 2: Находим левые границы (ближайший меньший слева)
    // Идем справа налево
    for (currentIndex in n - 1 downTo 0) {
        val currentHeight = heights[currentIndex]

        // Пока стек не пуст И текущий элемент МЕНЬШЕ элемента на вершине стека
        while (stack.isNotEmpty() && currentHeight < heights[stack.last()]) {
            // Для элемента на вершине стека мы нашли левую границу!
            val poppedIndex = stack.removeLast()  // индекс элемента, для которого нашли ответ
            leftBoundary[poppedIndex] = currentIndex  // текущий индекс - это левая граница
        }

        // Добавляем текущий индекс в стек
        stack.addLast(currentIndex)
    }

    // ВЫЧИСЛЕНИЕ МАКСИМАЛЬНОЙ ПЛОЩАДИ
    var maxArea = 0
    for (i in 0 until n) {
        // Ширина прямоугольника с высотой heights[i]:
        // от (leftBoundary[i] + 1) до (rightBoundary[i] - 1) включительно
        val width = rightBoundary[i] - leftBoundary[i] - 1
        val area = heights[i] * width
        maxArea = maxOf(maxArea, area)
    }

    return maxArea
}

// Более компактная версия с комментариями
fun largestRectangleAreaCompact(heights: IntArray): Int {
    val stack = ArrayDeque<Int>()
    var maxArea = 0

    // Добавляем дополнительный столбец высотой 0 для обработки оставшихся
    for (i in 0..heights.size) {
        val h = if (i < heights.size) heights[i] else 0

        // Пока текущая высота меньше высоты на вершине стека
        while (stack.isNotEmpty() && h < heights[stack.last()]) {
            val heightIdx = stack.removeLast()
            val height = heights[heightIdx]

            // Ширина = текущий индекс - новый верх стека - 1
            // или текущий индекс, если стек пуст
            val width = if (stack.isEmpty()) i else i - stack.last() - 1

            maxArea = maxOf(maxArea, height * width)
        }

        stack.addLast(i)
    }

    return maxArea
}