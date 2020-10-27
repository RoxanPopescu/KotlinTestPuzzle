package com.igorwojda.list.pairaverage

import org.amshove.kluent.shouldEqual
import org.junit.Test


/**
 * Hint to use double pointer.
 * I found a solution involving sorting the list ascending and then using
 * 2 indices - start and end, for the 2 end-points of the array.
 * i.e A double pointer to mark the start and end of the list.
 * Loop through the list until start < end and reduce the search space like this:
 * If the given average > than the avg of start and end , shift left pointer (start ++ to increase value
 * If the given average < than the avg of start and end, shift the right pointer ( end- to decrease the value
 *
 * O(nlogn) time complexity
 */
//Iterative-version
fun hasAverageIT(list: List<Int>, average: Double): Boolean {
    var localList = list.sorted()
    println("Sorted list: " + localList)

    var start = 0
    var end = localList.size - 1

    while (start < end) {

        var currentAvg: Double = localList[start].plus(localList[end]) / 2.00
        println("currentAvg = $currentAvg" + "( " + localList[start] + "," + localList[end] + "), avg=$average \n")

        if (currentAvg.compareTo(average) == 0) {
            println("(" + localList[start] + "," + localList[end] + ")")
            return true
        } else {
            if (currentAvg.compareTo(average) < 0) {
                start++
            } else {
                end--
            }
        }
    }
    return false
}

//recursive version
fun hasAverage(list: List<Int>, average: Double): Boolean {
    return hasAverageEF(list, 0, list.size - 1, average)
}

fun hasAverageEF(list: List<Int>, start: Int, end: Int, average: Double): Boolean {

    if (start >= end) return false

    val currentAvg: Double = list[start].plus(list[end]) / 2.00

    return when {
        currentAvg == average -> true
        currentAvg < average -> {
            hasAverageEF(list, start + 1, end, average)
        }
        currentAvg > average -> {
            hasAverageEF(list, start, end - 1, average)
        }
        else -> false
    }
}

private fun <E> MutableList<E>.aaa2(a: E) {
    set(0, a)
}

fun MutableList<Int>.aaa() {
    set(0, 12)
}

inline fun <K, V> MutableMap<K, V>.incrementExisting(key: K, value: V) {
    put(key, value)
}

class TargetAverageTest {
    @Test
    fun `empty list return false`() {
        hasAverage(listOf(), 1.0) shouldEqual false
    }

    @Test
    fun `list size 1 return false`() {
        hasAverage(listOf(1), 1.0) shouldEqual false
    }

    @Test
    fun `list 3, 5 should with average 5,5 return true`() {
        hasAverage(listOf(3, 5), 5.5) shouldEqual false
    }

    @Test
    fun `list 3, 4, 7, 9 should with average 6,5 return true`() {
        hasAverage(listOf(3, 4, 7, 9), 6.5) shouldEqual true
    }

    @Test
    fun `list 3, 5 should with average 2,7 return true`() {
        hasAverage(listOf(3, 5), 2.7) shouldEqual false
    }

    @Test
    fun `list 3, 5, 9, 7, 11, 14 should with average 8,0 return true`() {
        hasAverage(listOf(3, 5, 9, 7, 11, 14), 8.0) shouldEqual true
    }

    @Test
    fun `list 3, 7, 5 should with average 3,5 return true`() {
        hasAverage(listOf(3, 5, 7), 3.5) shouldEqual false
    }
}


