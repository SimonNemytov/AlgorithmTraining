package coderun

fun main() {
    val genome1: String = readln()
    val genome2: String = readln()
    var mapGenome = mutableMapOf<String, Int>()

    var result = 0

    val array = genome1.windowed(2)

    for (pair in array) {
        if (mapGenome.containsKey(pair)) {
            mapGenome[pair] = (mapGenome[pair] ?: 0) + 1
        } else mapGenome.put(pair, 1)
    }
    for (pair in genome2.windowed(2).toSet()) {
        if (mapGenome.containsKey(pair)) {
            result += mapGenome[pair] ?: 0
        }
    }
    println(result)
}
