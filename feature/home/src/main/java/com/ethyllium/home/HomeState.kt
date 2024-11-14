package com.ethyllium.home

import com.ethyllium.domain.achievement.Achievement

data class HomeState(
    val recentElection: List<String>,
    private val achievement: List<Achievement>
) {
    val achievements: List<Achievement>
        get() = achievement.take(3)
    val recentElections: List<String>
        get() = recentElection.take(3)
}
