fun main() {
    fun convertInputToInts(input: List<String>): List<Int> {
        val ints = mutableListOf<Int>()
        for (i in input) {
            if (i != "") {
                ints.add(i.toInt())
            } else {
                ints.add(0)
            }
        }
        return ints
    }

    fun updateThreeMax(threeMax: MutableList<Int>, current: Int) {
        val min = threeMax.min()
        val index = threeMax.indexOf(min)
        if (threeMax.contains(0) || current > min) {
            threeMax[index] = current
        }
    }

    fun part1(input: List<String>): Int {
        var max = 0
        var current = 0
        val ints = convertInputToInts(input)
        for (i in ints) {
            if (i != 0) {
                current += i
            } else {
                max = maxOf(max, current)
                current = 0
            }
        }
        return max
    }

    fun part2(input: List<String>): Int {
        val threeMax = MutableList(3) { 0 }
        var current = 0
        val ints = convertInputToInts(input)
        for (i in ints) {
            if (i != 0) {
                current += i
            } else {
                updateThreeMax(threeMax, current)
                current = 0
            }
        }
        updateThreeMax(threeMax, current)
        return threeMax.sum()
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 24000)
//    check(part2(testInput) == 45000)


    val input = readInput("Day01")
//    println(part1(input))
    println(part2(input))
}
