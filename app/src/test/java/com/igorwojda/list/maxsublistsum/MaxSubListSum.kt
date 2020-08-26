package com.igorwojda.list.maxsublistsum

import org.amshove.kluent.shouldEqual
import org.junit.Test


/**
 * Rephrase problem:
 * - get all arrays of size n,
 * - compute theirs sums and
 * - return the max of the sums.
 * If list has size m and subarray is n => O(m *n) complexity.
 *
 * An Efficient Solution = that sum of a subarray (or window) of size n
 * can be obtained in O(1) time using the sum of previous subarray (or window)
 * of size n.
 * Except for the first subarray of size n, for other subarrays, we compute
 * sum by removing the first element of the last window and
 * adding the last element of the current window. => O(m) complexity
 *
 *
 */
private fun maxSubListSum(list: List<Int>, n: Int): Int? {
    val listSize = list.size

    if( listSize < n) return null
    else{
        // Compute sum of first window of size k
        var result = 0
        for(i in 0 until n step 1){
            result += list[i]
        }
        // Compute sums of remaining windows by
        // removing first element of previous
        // window and adding last element of
        // current window.

        var curr_sum = result
        for( i in n until  listSize step 1){
            curr_sum+= list[i] - list[i-n]
            result = max(result, curr_sum)!!
        }
        return result
    }
}

private fun max(i1: Int?, i2: Int?): Int? {
    return when {
        i1 != null && i2 != null -> Math.max(i1, i2)
        i1 != null && i2 == null -> i1
        i1 == null && i2 != null -> i2
        else -> null
    }
}

class MaxSubListTest {
    @Test
    fun `max sublist sum for list 4, 2, 7 and n 2 `() {
        maxSubListSum(listOf(4, 2, 7), 2) shouldEqual 9
    }

    @Test
    fun `max sublist sum for list 4, 2, 7, 5, 8, 9, 5, 1 and n 3 `() {
        maxSubListSum(listOf(4, 2, 7, 5, 8, 9, 5, 1), 3) shouldEqual 22
    }

    @Test
    fun `max sublist sum for list 1,2,5,2,8,1,5 and n 4 `() {
        maxSubListSum(listOf(1, 2, 5, 2, 8, 1, 5), 4) shouldEqual 17
    }

    @Test
    fun `max sublist sum for empty list and n 5 `() {
        maxSubListSum(emptyList(), 5) shouldEqual null
    }
}
