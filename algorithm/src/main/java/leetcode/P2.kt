package leetcode

class P22 {

    /**
     * 덱에 집어넣고 하나씩 꺼내면서 더해보고 10 넘으면 그거에 대한 처리하기
     * 정답을 리스트에 담은 다음 노드로 구성하기
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var nodes1 = l1
        var nodes2 = l2

        val q1 = ArrayDeque<Int>()
        val q2 = ArrayDeque<Int>()

        while (nodes1?.`val` != null) {
            q1.add(nodes1.`val`)
            nodes1 = nodes1.next
        }

        while (nodes2?.`val` != null) {
            q2.add(nodes2.`val`)
            nodes2 = nodes2.next
        }

        val result = ArrayList<Int>()

        while (q1.isNotEmpty() || q2.isNotEmpty()) {
            var sum: Int
            var removeFirst1 = q1.removeFirstOrNull() ?: 0
            var removeFirst2 = q2.removeFirstOrNull() ?: 0

            if (removeFirst1 == 10) {
                removeFirst1 = 0
                q1.addFirst(q1.removeFirstOrNull()?.plus(1) ?: 1)
            }

            if (removeFirst2 == 10) {
                removeFirst2 = 0
                q2.removeFirstOrNull()?.plus(1)
            }

            sum = removeFirst1 + removeFirst2
            if (sum >= 10) {
                result.add(asdf(sum))
                q1.addFirst(q1.removeFirstOrNull()?.plus(1) ?: 1)
            } else {
                result.add(sum)
            }
        }

        val head = ListNode(result[0])
        var current = head

        for (i in 1..<result.size) {
            val next = ListNode(result[i])
            current.next = next
            current = next

        }

        return head
    }

    fun asdf(n: Int): Int {
        if (n >= 10) {
            return n - 10
        }
        return n
    }

}


class ListNode(var `val`: Int) {
    var next: ListNode? = null

}