package yandex_algo


fun main() {

    println(canPlaceFlowers(intArrayOf(1, 0, 0, 0, 1), 1))
    println(canPlaceFlowers(intArrayOf(1, 0, 0, 0, 1), 2))
    println(canPlaceFlowers(intArrayOf(1, 0, 1, 0, 1, 0, 1), 1))

}

fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
    var counter = 0

    if (n == 0) {
        return true
    }
    if (flowerbed.isEmpty()) {
        return false
    }

    if (flowerbed.size == 1) {
        return flowerbed[0] == 0
    }

    for ((index, place) in flowerbed.withIndex()) {

        if (index == 0) {
            if (flowerbed[1] == 0 && flowerbed[index] == 0) {
                counter++
                flowerbed[index] = 1
            }
        } else if (index == flowerbed.size - 1) {
            if (flowerbed[index - 1] == 0 && flowerbed[index] == 0) {
                counter++
                flowerbed[index] = 1
            }
        } else {
            if (flowerbed[index - 1] == 0 && flowerbed[index + 1] == 0) {
                if (flowerbed[index] == 0) {
                    counter++
                    flowerbed[index] = 1
                }
            }
        }

        if (counter >= n) {
            return true
        }
    }
    return false
}