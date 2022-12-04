enum class Goal(val value: Char) {
    Win('Z'),
    Loose('X'),
    Draw('Y'),
}

enum class Variant(val value: Char) {
    Rock('A'),
    Paper('B'),
    Scissors('C')
}

fun main() {

    val outcomesForSecond = mapOf(
        Pair('A', 'A') to 4, //
        Pair('A', 'B') to 8,
        Pair('A', 'C') to 3,
        Pair('B', 'B') to 5, //
        Pair('B', 'A') to 1,
        Pair('B', 'C') to 9,
        Pair('C', 'C') to 6, //
        Pair('C', 'B') to 2,
        Pair('C', 'A') to 7,
    )

    val unknownToValues = mapOf(
        'X' to 'A',
        'Y' to 'B',
        'Z' to 'C',
     )

    fun part1(input: List<String>): Int {
        var res = 0
        for (i in input) {
            val friend = i[0]
            val me = unknownToValues[i[2]]
            res += outcomesForSecond[Pair(friend, me)] ?: 0
        }
        return res
    }

    fun mapGoalToWinningStrategy(goal: Goal, friendVariant: Variant): Char =
        when(goal) {
            Goal.Loose -> when(friendVariant) {
                Variant.Rock -> 'C'
                Variant.Paper -> 'A'
                Variant.Scissors -> 'B'
            }
            Goal.Draw -> when(friendVariant) {
                Variant.Rock -> 'A'
                Variant.Paper -> 'B'
                Variant.Scissors -> 'C'
            }
            Goal.Win -> when(friendVariant) {
                Variant.Rock -> 'B'
                Variant.Paper -> 'C'
                Variant.Scissors -> 'A'
            }
        }

    fun Char.toGoal(): Goal = when(this) {
        'Z' -> Goal.Win
        'Y' -> Goal.Draw
        else -> Goal.Loose
    }

    fun Char.toVariant(): Variant = when(this) {
        'A' -> Variant.Rock
        'B' -> Variant.Paper
        else -> Variant.Scissors
    }

    fun part2(input: List<String>): Int {
        var res = 0
        for (i in input) {
            val friend = i[0]
            val me = mapGoalToWinningStrategy(i[2].toGoal(), friend.toVariant())
            res += outcomesForSecond[Pair(friend, me)] ?: 0
        }
        return res
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day02_test")
//    println(testInput)
//    check(part1(testInput) == 15)
//    check(part2(testInput) == 12)


    val input = readInput("Day02")
//    val input = readInput("Day02_test")
//    println(part1(input))
    println(part2(input))
}

private operator fun <E> Set<E>.get(e: E): Any {
    return e as Char
}
