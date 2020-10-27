package com.igorwojda.list.sort.bubblesort

import org.amshove.kluent.shouldEqual
import org.junit.Test


/**
 * Idea of this sorting method - BUBBLE SORT
 * If we have a list of integers with size n,
 * then we iterate over the list from left to right
 * and for each position we compare the number on
 * that position to the number of next position.
 * If they are in wrong order then will swap them.
 * Suppose we sort ascending !
 * At each step we have n-j elements to sort, where
 * j is the start nr(0 initially)
 */

fun swapValues(list:IntArray, a: Int, b: Int) {
    val temp = list[b]
    list[b] = list[a]
    list[a] = temp
}

private fun bubbleSort(list: List<Int>): List<Number> {
    val sortedList = list.toIntArray()

    for (i in 0 until (sortedList.size - 1)) {
        for (j in 0 until (sortedList.size - i - 1)) {
            if (sortedList[j] > sortedList[j + 1]) {
                swapValues(sortedList,j,j+1)
            }
        }
    }
    return sortedList.toList()
}

//private fun bubbleSort2(list: List<Int>): List<Number> {
//   return list.bubble
//
//}

class BubbleSortTest {
    @Test
    fun `bubble sort empty list`() {
        bubbleSort(mutableListOf()) shouldEqual listOf()
    }

    @Test
    fun `bubble sort 7`() {
        bubbleSort(mutableListOf(7)) shouldEqual listOf(7)
    }

    @Test
    fun `bubble sort empty list 9, 3`() {
        bubbleSort(mutableListOf(9, 3)) shouldEqual listOf(3, 9)
    }

    @Test
    fun `bubble sort 5, 1, 4, 2`() {
        bubbleSort(listOf(5, 1, 4, 2)) shouldEqual listOf(1, 2, 4, 5)
    }

    @Test
    fun `bubble sort 5, 2, 1, 8, 4, 7, 6, 3`() {
        bubbleSort(mutableListOf(5, 2, 1, 8, 4, 7, 6, 3)) shouldEqual listOf(1, 2, 3, 4, 5, 6, 7, 8)
    }

    @Test
    fun `bubble sort 17, 4, 12, 19, 80, 75, 16`() {
        bubbleSort(listOf(17, 4, 12, 19, 80, 75, 16)) shouldEqual listOf(4, 12, 16, 17, 19, 75, 80)
    }

    @Test
    fun `bubble sort 11, 40, 40, 50, 43, 10, 30, 42, 20, 6, 19, 32, 20, 41, 23, 27`() {
        val list = mutableListOf(11, 40, 40, 50, 43, 10, 30, 42, 20, 6, 19, 32, 20, 41, 23, 27)
        val sorted = mutableListOf(6, 10, 11, 19, 20, 20, 23, 27, 30, 32, 40, 40, 41, 42, 43, 50)

        bubbleSort(list) shouldEqual sorted
    }
}
