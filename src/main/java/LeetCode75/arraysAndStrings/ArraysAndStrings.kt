package LeetCode75.arraysAndStrings

fun main() {

//    println(mergeAlternately("abc", "pqr"))
//    println(mergeAlternately("ab", "pqrs"))
//    println(mergeAlternately("a", "pqr"))
    println(mergeAlternately("abcd", "pq"))

}

fun mergeAlternately(word1: String, word2: String): String {
    var currentIndex = 0

    var resultString = ""

    if (word1.length < word2.length) {
        for (i in word1.indices) {
            resultString += word1[i]
            resultString += word2[i]
            currentIndex++
        }

        if (currentIndex <= word2.length - 1) {
            val substring = word2.substring(currentIndex)
            resultString += substring
        }
    } else {
        for (i in word2.indices) {
            resultString += word1[i]
            resultString += word2[i]
            currentIndex++
        }

        if (currentIndex <= word1.length - 1) {
            val substring = word1.substring(currentIndex)
            resultString += substring
        }
    }

    return resultString
}