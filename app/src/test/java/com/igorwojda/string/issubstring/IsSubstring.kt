package com.igorwojda.string.issubstring

import org.amshove.kluent.shouldEqual
import org.junit.Test

private fun isSubstring(str: String, str2: String): Boolean {
    if (str2.isEmpty()) return true
    if (str.isEmpty() && str2.isNotEmpty()) return false

    if (str.length <= str2.length) return false

    var pointer1 = 0
    var pointer2 = 0

    while (pointer1 <= str.lastIndex) {
        if (str[pointer1] == str2[pointer2]) {
            if (pointer2 == str2.lastIndex) {
                return true
            } else {
                pointer1++
                pointer2++
            }
        } else {
//            if(pointer2 < str2.lastIndex) {
//                pointer2++
//            } else {
//                pointer1++
//                pointer2 = 0
            pointer1++
            pointer2 = 0
//            }
        }
    }

    return false
}

class IsSubstringTest {
    @Test
    fun `"abc" contains "bc"`() {
        isSubstring("abc", "bc") shouldEqual true
    }

    @Test
    fun `"ababc" contains "abc"`() {
        isSubstring("ababc", "abc") shouldEqual true
    }

    @Test
    fun `"abcd" does not contains "abd"`() {
        isSubstring("abcd", "abd") shouldEqual false
    }

    @Test
    fun `"abcd" does not contain "e"`() {
        isSubstring("abcd", "e") shouldEqual false
    }

    @Test
    fun `"abd" contains "ab"`() {
        isSubstring("abc", "ab") shouldEqual true
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
