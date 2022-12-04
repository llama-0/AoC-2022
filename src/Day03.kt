fun main() {

    fun fillCase(start: Int, end: Int, char: Char): Map<Char, Int> {
        val case = mutableMapOf<Char, Int>()
        for (i in start..end) {
            case[char + i - start] = i
        }
        return case
    }

    val lowercase = fillCase(1, 26, 'a')
    val uppercase = fillCase(27, 52, 'A')
    val case = lowercase + uppercase

    fun part1(input: List<String>): Int {
        var res = 0
        for (i in input) {
            val middle = i.length / 2
            val first = i.slice(0 until middle).toList()
            val second = i.slice(middle until i.length).toSet()
            val elem = first.intersect(second).single()
            res += case[elem] ?: 0
        }
        return res
    }

    fun part2(input: List<String>): Int {
        var res = 0
        var i = 0
        while (i < input.size - 2) {
            // todo: check for end index if needed
            val first = input[i].toList()
            val second = input[i + 1].toSet()
            val third = input[i + 2].toSet()
            val elemsFirstSecond = first.intersect(second)
            val elemsSecondThird = second.intersect(third)
            val elem = elemsFirstSecond.intersect(elemsSecondThird).single()
            res += case[elem] ?: 0
            i += 3
        }
        return res
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day03_test")
//    println(testInput)
//    check(part1(testInput) == 157)
//    check(part2(testInput) == 70)


    val input = readInput("Day03")
//    val input = readInput("Day03_test")
//    println(part1(input))
    println(part2(input))
}