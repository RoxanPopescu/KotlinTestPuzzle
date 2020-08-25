package com.igorwojda.list.flatten

import org.amshove.kluent.shouldEqual
import org.junit.Test

//private fun flattenList(nestList: List<Any>,flatList: MutableList<Int>) {
//    for (e in nestList)
//        if (e is Int) {
//            flatList.add(e)
//        }
//        else {
//            flattenList(e as List<Any>, flatList)
//        }
//}

fun flatten(list: List<*>): List<*> =
    list.flatMap {
        if (it is List<*>) flatten(it as List<Any>)
        else listOf(it)
    }

//fun main() {
//    val list: List<*> = listOf(1, listOf(2, listOf(3), listOf(4, listOf(listOf(5)))))
//    println(" flattenList list is: " + flatten(list))
//}

class FlattenTest {
    @Test
    fun `flatten test 1`() {
        flatten(listOf(1)) shouldEqual listOf(1)
    }

    @Test
    fun `flatten test 2`() {
        flatten(listOf(1, listOf(2))) shouldEqual listOf(1, 2)
    }

    @Test
    fun `flatter test 3`() {
        flatten(listOf(1, listOf(2, listOf(3), emptyList<Any>()))) shouldEqual listOf(1, 2, 3)
    }

    @Test
    fun `flatter test 4`() {
        flatten(
            listOf(
                1,
                listOf(2, listOf<Any>(emptyList<Any>())),
                listOf(3)
            )
        ) shouldEqual listOf(1, 2, 3)
    }

    @Test
    fun `flatter test 5`() {
        flatten(listOf(1, listOf(2, listOf(3), listOf(4, listOf(listOf(5)))))) shouldEqual listOf(
            1,
            2,
            3,
            4,
            5
        )
    }
}
