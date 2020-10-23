package com.igorwojda.list.minsublistlength

import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 *  solution - 1
 * - I traverse the array once, considering each element as the start of the subarray
 * - I traverse the array twice, and I consider all elements as ending of the array
 * On(n^2) complexity
 * I compute the sum of the elements between start and end i.e curr_sum.
 * If the curr_sum is greater than sum I will update the smallest length result.
 * */

fun minSubListLength(list: List<Int>, sum: Int): Int {

    var minLength: Int? = null

    for (start in list.indices) {
        //init current sum with 1st elem
        var currSum = list[start]

        if (currSum > sum) {
            return 1
        }
        for (end in start + 1 until list.size) {
            //add last elem to current sum
            currSum += list[end]

            if (currSum >= sum) {
                minLength = min(end - start + 1, minLength)
            }
        }
    }
    return minLength ?: 0
}

private fun min(i1: Int?, i2: Int?): Int? {
    return when {
        i1 != null && i2 != null -> kotlin.math.min(i1, i2)
        i1 != null && i2 == null -> i1
        i1 == null && i2 != null -> i2
        else -> null
    }
}

class MinSubListLengthTest {
    @Test
    fun `min sub list sum empty list and 7 returns 0`() {
        minSubListLength(listOf(), 7) shouldEqual 0
    }

    @Test
    fun `min sub list sum 1,3,4 and 5 returns 2`() {
        minSubListLength(listOf(1, 3, 4), 5) shouldEqual 2
    }

    @Test
    fun `min sub list sum 4 and 7 returns 1`() {
        minSubListLength(listOf(4), 7) shouldEqual 0
    }

    @Test
    fun `min sub list sum 4 and 3 returns 0`() {
        minSubListLength(listOf(4), 3) shouldEqual 1
    }

    @Test
    fun `min sub list sum 1,2,3,2,5,8 and 7 returns 1`() {
        minSubListLength(listOf(2, 8), 7) shouldEqual 1
    }

    @Test
    fun `min sub list sum 1, 2, 11, 5, 9, 4, 6 and 22 returns 3`() {
        minSubListLength(listOf(1, 2, 11, 5, 9, 4, 6), 22) shouldEqual 3
    }

    @Test
    fun `min sub list sum 5, 6, 4, 15, 3, 8, 4 and 31 returns 4`() {
        minSubListLength(listOf(5, 6, 4, 15, 3, 8, 4), 31) shouldEqual 5
    }

    @Test
    fun `min sub list sum 1, 20, 11, 5, 9, 4, 6 and 200 returns 0`() {
        minSubListLength(listOf(1, 20, 11, 5, 9, 4, 6), 200) shouldEqual 0
    }

    @Test
    fun `min sub list sum 4, 6, 5 and 27 returns 0`() {
        minSubListLength(listOf(4, 6, 5), 27) shouldEqual 0
    }
}
