package com.ethyllium.domain.achievement

enum class VoterAchievement(
    override val title: String,
    override val description: String,
    override val rarity: AchievementRarity,
    override val points: Int
) : Achievement {
    FIRST_VOTE(
        "First-Time Voter",
        "Cast your first vote in any election",
        AchievementRarity.COMMON,
        10
    ),
    EARLY_BIRD(
        "Early Bird",
        "Vote within the first hour of an election starting",
        AchievementRarity.RARE,
        25
    ),
    PERFECT_ATTENDANCE(
        "Perfect Attendance",
        "Participate in all elections for 6 consecutive months",
        AchievementRarity.EPIC,
        100
    ),
    DEMOCRACY_CHAMPION(
        "Democracy Champion",
        "Cast votes in 100 different elections",
        AchievementRarity.LEGENDARY,
        500
    ),

    // Verification Achievements
    VERIFIED_VOTER(
        "Verified Voter",
        "Complete identity verification process",
        AchievementRarity.COMMON,
        15
    ),
    TWO_FACTOR_HERO(
        "Security Champion",
        "Enable two-factor authentication",
        AchievementRarity.COMMON,
        20
    ),

    // Community Achievements
    VOTER_RECRUITER(
        "Voter Recruiter",
        "Successfully invite 5 new voters who complete registration",
        AchievementRarity.RARE,
        50
    ),
    FEEDBACK_CONTRIBUTOR(
        "Voice of Democracy",
        "Provide constructive feedback on 10 different elections",
        AchievementRarity.RARE,
        40
    );

    override val category = AchievementCategory.VOTER
}
