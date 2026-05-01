package yandex_algo

fun main() {

    println(myAtoi("42")) //42
    println(myAtoi("00042")) //42
    println(myAtoi(" -042")) //-42
    println(myAtoi("1337c0d3")) //1337
    println(myAtoi("0-1")) //0
    println(myAtoi("words and 987")) //0


    println("//---longestPalindrome--//")

//    println(longestPalindrome("a"))//a
//    println(longestPalindrome("ab"))//a
//    println(longestPalindrome("aa")) //aa
//    println(longestPalindrome("aba")) //aba
    println(longestPalindrome("cbbd")) //aba

}

fun myAtoi(s: String): Int {
    val input = s.trim()
    if (input.isEmpty()) return 0

    var i = 0
    var sign = 1
    var result = 0L // Используем Long для промежуточного хранения

    // 1. Обработка знака (только в начале!)
    if (input[i] == '-' || input[i] == '+') {
        sign = if (input[i] == '-') -1 else 1
        i++
    }

    // 2. Чтение цифр
    while (i < input.length && input[i].isDigit()) {
        val digit = input[i] - '0'

        result = result * 10 + digit

        // 3. Проверка на переполнение прямо в процессе
        if (result * sign > Int.MAX_VALUE) return Int.MAX_VALUE
        if (result * sign < Int.MIN_VALUE) return Int.MIN_VALUE

        i++
    }

    return (result * sign).toInt()
}

fun longestPalindrome(s: String): String {
    if (s.length <= 1) {
        return s
    }

    var maxStr = s.substring(0, 1)

    for (i in s.indices) {
        val odd = expandFromCenter(s, i, i)
        val even = expandFromCenter(s, i, i + 1)

        if (odd.length > maxStr.length) {
            maxStr = odd
        }

        if (even.length > maxStr.length) {
            maxStr = even
        }
    }

    return maxStr
}

fun expandFromCenter(s: String, start: Int, end: Int): String {
    var left = start
    var right = end

    while (left >= 0 && right < s.length && s[left] == s[right]) {
        left--
        right++
    }

    return s.substring(left + 1, right)
}

