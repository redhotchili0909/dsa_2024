fun digitList(integer: Int): List<Int> {
    return integer.toString().map { it.toString().toInt() }
}

fun assemble(digits: List<Int>): Int {
    var result = 0
    for (digit in digits) {
        result = result * 10 + digit
    }
    return result
}

fun nextNumber(lookAndSayNum: Int): Int {
    val result = mutableListOf<Int>()
    val num = digitList(lookAndSayNum)
    var i = 0

    while (i < num.size) {
        var count = 1
        while (i + 1 < num.size && num[i] == num[i + 1]) {
            count++
            i++
        }
        result.add(count)
        result.add(num[i])
        i++
    }
    return assemble(result)
}

fun lookAndSay(length: Int): List<Int> {
    val sequenceList = mutableListOf<Int>()
    var i = 0
    var sequence = 1
    if (length > 0) {
        sequenceList.add(sequence)
        while (i <= length - 2) {
            sequence = nextNumber(sequence)
            sequenceList.add(sequence)
            i++
        }
    }
    return sequenceList
}
