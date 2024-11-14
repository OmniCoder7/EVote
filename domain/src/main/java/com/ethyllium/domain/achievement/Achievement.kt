package com.ethyllium.domain.achievement


interface Achievement {
    val title: String
    val description: String
    val category: AchievementCategory
    val rarity: AchievementRarity
    val points: Int
}