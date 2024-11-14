package com.ethyllium.domain.achievement

enum class CandidateAchievement(
    override val title: String,
    override val description: String,
    override val rarity: AchievementRarity,
    override val points: Int
) : Achievement {
    // Campaign Achievements
    FIRST_CAMPAIGN(
        "Rookie Candidate",
        "Launch your first election campaign",
        AchievementRarity.COMMON,
        25
    ),
    MANIFESTO_MASTER(
        "Manifesto Master",
        "Get 1000+ views on your election manifesto",
        AchievementRarity.RARE,
        75
    ),
    CAMPAIGN_VETERAN(
        "Campaign Veteran",
        "Participate in 10 different elections as a candidate",
        AchievementRarity.EPIC,
        150
    ),

    // Performance Achievements
    LANDSLIDE_VICTORY(
        "Landslide Victor",
        "Win an election with over 75% of the votes",
        AchievementRarity.LEGENDARY,
        300
    ),
    CLOSE_CALL(
        "Photo Finish",
        "Win an election by a margin of less than 1%",
        AchievementRarity.RARE,
        100
    ),
    COMEBACK_KID(
        "Comeback Kid",
        "Win after being behind in preliminary results",
        AchievementRarity.EPIC,
        200
    ),

    // Engagement Achievements
    PEOPLES_CHOICE(
        "People's Choice",
        "Receive positive feedback from 90% of voters",
        AchievementRarity.EPIC,
        175
    ),
    DEBATE_MASTER(
        "Debate Master",
        "Participate in 5 election debates",
        AchievementRarity.RARE,
        80
    ),
    SOCIAL_BUTTERFLY(
        "Social Butterfly",
        "Respond to 100+ voter queries during campaign",
        AchievementRarity.RARE,
        60
    );

    override val category = AchievementCategory.CANDIDATE
}
