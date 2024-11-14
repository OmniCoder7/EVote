package com.ethyllium.domain.achievement

import com.ethyllium.domain.R

enum class AchievementRarity(val id: Int) {
    COMMON(id = R.drawable.common),
    RARE(id = R.drawable.rare),
    EPIC(id = R.drawable.epic),
    LEGENDARY(id = R.drawable.legendary)
}
