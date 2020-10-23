package com.igorwojda.list.sort.insertionsort

import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * The idea of insertion sort is that you insert each element at its
 * correct position, by comparing each two consecutive/near elements
 * and swap them if they are not in order.
 * For each element i I compare it with all the elements that follow i,
 * until the end of the array
 */
private fun insertionSort(list: MutableList<Int>): List<Int> {
   if(list.isEmpty() || list.size < 2){
       return list
   }else{
       for(i in 1 until list.size){
           val item = list[i]
           var j = i

           while(j > 0 && item < list[j -1]){
               //make space for the element to insert
               list[j] = list[j-1]
               j -= 1
           }
           list[j] = item
       }
   }
    return list
}

class InsertionSortTest {
    @Test
    fun `insertion sort empty list`() {
        insertionSort(mutableListOf()) shouldEqual listOf()
    }

    @Test
    fun `insertion sort 7`() {
        insertionSort(mutableListOf(7)) shouldEqual listOf(7)
    }

    @Test
    fun `insertion sort empty list 9, 3`() {
        insertionSort(mutableListOf(9, 3)) shouldEqual listOf(3, 9)
    }

    @Test
    fun `insertion sort 5, 1, 4, 2`() {
        insertionSort(mutableListOf(5, 1, 4, 2)) shouldEqual listOf(1, 2, 4, 5)
    }

    @Test
    fun `insertion sort 5, 2, 1, 8, 4, 7, 6, 3`() {
        insertionSort(mutableListOf(5, 2, 1, 8, 4, 7, 6, 3)) shouldEqual listOf(1, 2, 3, 4, 5, 6, 7, 8)
    }

    @Test
    fun `insertion sort 17, 4, 12, 19, 80, 75, 16`() {
        insertionSort(mutableListOf(17, 4, 12, 19, 80, 75, 16)) shouldEqual listOf(4, 12, 16, 17, 19, 75, 80)
    }

    @Test
    fun `insertion sort 11, 40, 40, 50, 43, 10, 30, 42, 20, 6, 19, 32, 20, 41, 23, 27`() {
        val list = mutableListOf(11, 40, 40, 50, 43, 10, 30, 42, 20, 6, 19, 32, 20, 41, 23, 27)
        val sorted = mutableListOf(6, 10, 11, 19, 20, 20, 23, 27, 30, 32, 40, 40, 41, 42, 43, 50)

        insertionSort(list) shouldEqual sorted
    }
}
