package leetcode

class P4 {

}

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val sortedArray = nums1 + nums2.sortedArray()
    return if (sortedArray.size % 2 == 0) {
        (sortedArray[sortedArray.size / 2 - 1] + sortedArray[sortedArray.size / 2]).toDouble() / 2
    } else {
        sortedArray[sortedArray.size / 2].toDouble()
    }
}

fun main() {
    println(1.50000.toDouble())
    println(findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3, 4)))
}