package com.igorwojda.string.issubstring

import org.amshove.kluent.shouldEqual
import org.junit.Test

private fun isSubstring(str: String, subStr: String): Boolean {
    if (subStr.isEmpty()) return true
    if (str.isEmpty() && subStr.isNotEmpty()) return false

    fun helper(first: String, second: String, firstPointer1: Int = 0, secondPointer2: Int = 0): Boolean {
        if (firstPointer1 > first.lastIndex) {
            return false
        }

        return if (first[firstPointer1] == second[secondPointer2]) {
            val localPointer1 = firstPointer1 + 1
            val localPointer2 = secondPointer2 + 1

            if (localPointer1 > first.lastIndex || localPointer2 > second.lastIndex) {
                return true
            } else {
                helper(first, second, localPointer1, localPointer2)
            }
        } else {
            val p1 = firstPointer1 + 1

            if (p1 > first.lastIndex) {
                return false
            } else {
                helper(first, second, p1, secondPointer2)
            }
        }
    }

    return helper(str, subStr)
}

class IsSubstringTest {
    @Test
    fun `"abcd" does not contain "e"`() {
        isSubstring("abcd", "e") shouldEqual false
    }

    @Test
    fun `"abd" contains "ab"`() {
        isSubstring("abc", "ab") shouldEqual true
    }

    @Test
    fun `"abc" contains "bc"`() {
        isSubstring("abc", "bc") shouldEqual true
    }

    @Test
    fun `"ababc" contains "abc"`() {
        isSubstring("ababc", "abc") shouldEqual true
    }

    @Test
    fun `"abcdef" contains "cd"`() {
        isSubstring("abcdef", "cd") shouldEqual true
    }

    @Test
    fun `"abc" contains ""`() {
        isSubstring("abc", "") shouldEqual true
    }

    @Test
    fun `"" does not contains "abc"`() {
        isSubstring("", "abc") shouldEqual false
    }

    @Test
    fun `"" contains ""`() {
        isSubstring("", "") shouldEqual true
    }
}
