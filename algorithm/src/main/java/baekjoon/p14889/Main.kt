package baekjoon.p14889

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs
import kotlin.math.min
import kotlin.system.exitProcess

/**
 * https://www.youtube.com/watch?v=dN87B9I8BNw
 */

var N = 0
val teamA = mutableListOf<Int>()
val teamB = mutableListOf<Int>()
lateinit var score: Array<Array<Int>>
var minResult = Int.MAX_VALUE

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    N = br.readLine().toInt()
    score = Array(N) { Array(N) { 0 } }

    for (i in 0..<N) {
        val st = StringTokenizer(br.readLine())
        for (j in 0..<N) {
            score[i][j] = st.nextToken().toInt()
        }
    }

    val teamNumber = N / 2
    recursive(0, teamNumber)
    println(minResult)
}

fun recursive(order: Int, teamNumber: Int) {
    if (teamNumber == 0) {
        for (i in 0..<N) {
            if (teamA.contains(i).not()) {
                teamB.add(i)
            }
        }
        checkScore()
        teamB.clear()

        if (minResult == 0) {
            println(minResult)
            exitProcess(0)
        }
        return
    }

    for (i in order..<N) {
        teamA.add(i)
        recursive(i + 1, teamNumber - 1)
        teamA.removeAt(teamA.size - 1)
    }
}

fun checkScore() {
    var teamAScore = 0
    var teamBScore = 0

    for (i in 0..<N / 2) {
        for (j in i + 1..<N / 2) {
            teamAScore += (score[teamA[i]][teamA[j]] + score[teamA[j]][teamA[i]])
            teamBScore += (score[teamB[i]][teamB[j]] + score[teamB[j]][teamB[i]])
        }
    }

    val result = abs(teamAScore - teamBScore)
    minResult = min(result, minResult)
}