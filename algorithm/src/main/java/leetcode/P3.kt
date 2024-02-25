package leetcode

import kotlin.math.max

class P3

//    fun lengthOfLongestSubstring(s: String): Int {
//
//    }


/**
 * 50000 * 50000 = 2500000000 시간 초과
 */
fun lengthOfLongestSubstring(s: String): Int {
    val list = s.toList()
    var answer = 0
    var current: Int
    val nonRepeatList:MutableSet<Char> = mutableSetOf()

    for (i in list.indices) {
        current = 0
        for (j in i..<list.size) {
            val char = list[j]
            if (nonRepeatList.contains(char)) {
                answer = max(current, answer)
                nonRepeatList.clear()
                break
            } else {
                current += 1
                nonRepeatList.add(char)
                answer = max(current, answer)
            }

        }
    }


    return answer
}
fun main() {
    println(lengthOfLongestSubstring("dvdf"))
}