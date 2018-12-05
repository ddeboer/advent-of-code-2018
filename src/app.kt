import java.io.File

fun main(args: Array<String>) {
    println(day2Part1())
    println(day2Part2())
}

fun day2Part1(): Int {
    val (twice, thrice) = readFile("input/day2").fold(Pair(0, 0)) { (a, b), boxId ->
        val counts = boxId.groupingBy { it }.eachCount()
        val twice = if (counts.containsValue(2)) a + 1 else a
        val thrice = if (counts.containsValue(3)) b + 1 else b
        Pair(twice, thrice)
    }

    return twice * thrice
}

fun day2Part2(): String? {
    val inputs = readFile("input").asSequence()
    inputs.forEachIndexed { i, input ->
        inputs.drop(i).forEach {
            if (it.difference(input).length == 1) {
                return it.similarity(input)
            }
        }
    }

    return null
}

fun readFile(file: String) = File(file).readLines()

fun String.difference(with: String): String {
    return this.filterIndexed { i, char -> with[i] != char }
}

fun String.similarity(with: String): String {
    return this.filterIndexed { i, char -> with[i] == char }
}
