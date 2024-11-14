package com.ethyllium.domain.achievement

enum class AdminAchievement(
    override val title: String,
    override val description: String,
    override val rarity: AchievementRarity,
    override val points: Int
) : Achievement {
    // Organization Achievements
    FIRST_ELECTION(
        "Election Organizer",
        "Successfully organize your first election",
        AchievementRarity.COMMON,
        30
    ),
    MASS_PARTICIPATION(
        "Mass Mobilizer",
        "Organize an election with 10,000+ voters",
        AchievementRarity.EPIC,
        200
    ),
    PERFECT_EXECUTION(
        "Perfect Coordinator",
        "Complete an election with zero reported issues",
        AchievementRarity.LEGENDARY,
        400
    ),

    // Security Achievements
    SECURITY_EXPERT(
        "Security Expert",
        "Maintain perfect security record for 10 elections",
        AchievementRarity.EPIC,
        250
    ),
    RAPID_RESPONSE(
        "Quick Responder",
        "Resolve all reported issues within 15 minutes",
        AchievementRarity.RARE,
        100
    ),
    FRAUD_PREVENTION(
        "Watchful Guardian",
        "Successfully prevent 5 attempted voting irregularities",
        AchievementRarity.EPIC,
        175
    ),

    // Innovation Achievements
    TEMPLATE_CREATOR(
        "Template Innovator",
        "Create 5 reusable election templates",
        AchievementRarity.RARE,
        80
    ),
    EFFICIENCY_EXPERT(
        "Efficiency Expert",
        "Complete election setup in record time",
        AchievementRarity.RARE,
        90
    ),
    MASTER_ADMINISTRATOR(
        "Master Administrator",
        "Successfully manage 100 elections",
        AchievementRarity.LEGENDARY,
        500
    ),

    // Community Support
    MENTOR(
        "Admin Mentor",
        "Help train 5 new election administrators",
        AchievementRarity.EPIC,
        150
    ),
    DOCUMENTATION_HERO(
        "Documentation Hero",
        "Create comprehensive election guidelines",
        AchievementRarity.RARE,
        70
    );

    override val category = AchievementCategory.ADMIN
}