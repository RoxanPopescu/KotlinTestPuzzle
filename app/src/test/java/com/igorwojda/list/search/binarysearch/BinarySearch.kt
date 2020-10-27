package com.igorwojda.list.search.binarysearch

import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * Binary Search (half-interval search or logarithmic search) is a search algorithm that finds
 * the specified position of a value within a sorted array only.
 * You have a sorted array consisting of X elements, and you input the value to be found in it.
 * The algorithm compares your input value with the key value of the array's middle element.
 * 1 . If the input key value matches the key value of the middle element > its index is returned.
 * If the input key value is lesser than the key value of the middle element,
 * do a search on the sub-array to the left of the middle element.
 * Similarly, if the input key value is greater than the key value of the middle element,
 * do a search on the sub-array to the right of the middle element.
 */

fun binarySearch(list: List<Char>, element: Char): Int {

    var low = 0
    var high = list.size - 1

    while (low <= high) {
        val mid: Int = (high + low) / 2
        when {
            element == list[mid] -> return mid       // found the element
            element < list[mid] -> high = mid - 1   //element will be in left half of the array.
            element > list[mid] -> low = mid + 1    // element will be in right half of array
        }
    }
    return -1
}

class BinarySearchTest {
    @Test
    fun `index of A in A, B, C is 0`() {
        binarySearch(listOf('A', 'B', 'C'), 'A') shouldEqual 0
    }

    @Test
    fun `index of B in A, B, C is 1`() {
        binarySearch(listOf('A', 'B', 'C'), 'B') shouldEqual 1
    }

    @Test
    fun `index of C in A, B, C is 2`() {
        binarySearch(listOf('A', 'B', 'C'), 'C') shouldEqual 2
    }

    @Test
    fun `index of H in A, B, C is -1`() {
        binarySearch(listOf('A', 'B', 'C'), 'H') shouldEqual -1
    }

    @Test
    fun `index of A in A, B, C, D is 0`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'A') shouldEqual 0
    }

    @Test
    fun `index of B in A, B, C, D is 1`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'B') shouldEqual 1
    }

    @Test
    fun `index of C in A, B, C, D is 2`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'C') shouldEqual 2
    }

    @Test
    fun `index of D in A, B, C, D is 2`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'D') shouldEqual 3
    }

    @Test
    fun `index of H in A, B, C, D is -1`() {
        binarySearch(listOf('A', 'B', 'C', 'D'), 'H') shouldEqual -1
    }
}
