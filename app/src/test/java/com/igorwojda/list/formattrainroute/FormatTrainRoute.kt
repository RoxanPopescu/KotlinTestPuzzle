package com.igorwojda.list.formattrainroute

import org.amshove.kluent.shouldEqual
import org.junit.Test

private fun formatTrainRoute(stations: List<String>): String {
    var n = stations.size - 1
    val result = "Train is calling at "

    if (n == 0) {
        return result + stations[0]
    }
    val last = stations.takeLast(1).joinToString { it }

    val followingStations = stations.dropLast(1)
        .joinToString { it }

    return "$result$followingStations and $last"
}

class TrainRouteTest {
    @Test
    fun `formatTrainRoute list "Luton"`() {
        formatTrainRoute(listOf("Luton")) shouldEqual "Train is calling at Luton"
    }

    @Test
    fun `formatTrainRoute list "Luton", "Harpenden"`() {
        formatTrainRoute(
            listOf(
                "Luton",
                "Harpenden"
            )
        ) shouldEqual "Train is calling at Luton and Harpenden"
    }

    @Test
    fun `formatTrainRoute list "Luton", "Harpenden", "London"`() {
        val list = listOf("Luton", "Harpenden", "London")
        formatTrainRoute(list) shouldEqual "Train is calling at Luton, Harpenden and London"
    }

    @Test
    fun `formatTrainRoute list "Luton", "Harpenden", "St Albans", "London"`() {
        val list = listOf("Luton", "Harpenden", "St Albans", "London")
        formatTrainRoute(list) shouldEqual "Train is calling at Luton, Harpenden, St Albans and London"
    }
}
