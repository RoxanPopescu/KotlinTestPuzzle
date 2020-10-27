package com.igorwojda.list.sort.quicksort

import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * One of the fastest and space efficient sorting algorithm - time complexity O(nlogn).
 * Method: divide and conquer
 */

private fun quickSort(
    list: MutableList<Int>,
    left: Int = 0,
    right: Int = list.lastIndex
): List<Number> {

    if (list.isEmpty()) {
        return emptyList()
    }
    if (list.size <= 1) {
        return list
    }

    val index = partition(list, left, right)

    if (left < index - 1) {
        quickSort(list, left, index - 1)
    }
    if (index < right) {
        quickSort(list, index, right)
    }
    return list
}

fun partition(list: MutableList<Int>, l: Int, r: Int): Int {
    var left = l
    var right = r
    val pivot = list[(left + right) / 2]

    while (left <= right) {
        //i get the elements on the left that should be to the right
        while (list[left] < pivot) left++

        // I get the elements on right that should be on left
        while (list[right] > pivot) right--

        //then swap elements, and move left and right indices
        if (left <= right) {
            list.swap(left, right)
            left++
            right--
        }
    }
    return left
}

private fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

class QuickSortTest {
    @Test
    fun `quick sort empty list`() {
        quickSort(mutableListOf()) shouldEqual listOf()
    }

    @Test
    fun `quick sort 7`() {
        quickSort(mutableListOf(7)) shouldEqual listOf(7)
    }

    @Test
    fun `quick sort empty list 9, 3`() {
        quickSort(mutableListOf(9, 3)) shouldEqual listOf(3, 9)
    }

    @Test
    fun `quick sort 5, 1, 4, 2`() {
        quickSort(mutableListOf(5, 1, 4, 2)) shouldEqual listOf(1, 2, 4, 5)
    }

    @Test
    fun `quick sort 5, 2, 1, 8, 4, 7, 6, 3`() {
        quickSort(mutableListOf(5, 2, 1, 8, 4, 7, 6, 3)) shouldEqual listOf(1, 2, 3, 4, 5, 6, 7, 8)
    }

    @Test
    fun `quick sort 17, 4, 12, 19, 80, 75, 16`() {
        quickSort(mutableListOf(17, 4, 12, 19, 80, 75, 16)) shouldEqual listOf(
            4,
            12,
            16,
            17,
            19,
            75,
            80
        )
    }

    @Test
    fun `quick sort 11, 40, 40, 50, 43, 10, 30, 42, 20, 6, 19, 32, 20, 41, 23, 27`() {
        val list = mutableListOf(11, 40, 40, 50, 43, 10, 30, 42, 20, 6, 19, 32, 20, 41, 23, 27)
        val sorted = mutableListOf(6, 10, 11, 19, 20, 20, 23, 27, 30, 32, 40, 40, 41, 42, 43, 50)

        quickSort(list) shouldEqual sorted
    }
}
