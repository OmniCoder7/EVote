package com.ethyllium.domain.model

data class Result(
    val electionTitle: String,
    val startDateTime: Long,
    val endDateTime: Long,
    val status: ResultStatus,
    val totalRegisteredVoters: Int,
    val totalVotesCast: Int,
    val invalidVotesCount: Int,
    var candidates: List<Candidates>,
    val demographicBreakdown: DemographicStats,
    val geographicBreakdown: List<RegionalResult>,
    val votingTimeStats: VotingTimeStats,
    val resultHash: String,
    val verificationStatus: VerificationStatus,
    val lastUpdatedTimestamp: Long,
    val isFinalized: Boolean
) {
    init {
        candidates = candidates.sortedBy { it.rankPosition }.subList(0, 3)
    }
}

data class Candidates(
    val candidateName: String,
    val partyAffiliation: String,
    val votesReceived: Int,
    val votePercentage: Double,
    val rankPosition: Int,
    val winningStatus: WinningStatus
)

data class DemographicStats(
    val ageGroups: Map<AgeRange, VoterStats>,
    val genderDistribution: Map<String, VoterStats>,
    val firstTimeVoters: VoterStats
)

data class RegionalResult(
    val regionId: String,
    val regionName: String,
    val totalVoters: Int,
    val votesCount: Int,
    val candidateWiseResults: Map<String, Int>,
    val turnoutPercentage: Double
)

data class VotingTimeStats(
    val peakVotingHours: List<HourlyVotes>,
    val averageVotingTime: Double,
    val votingPatternByHour: Map<Int, Int>
)

data class VoterStats(
    val count: Int,
    val percentage: Double,
    val comparisonWithLastElection: Double?
)

data class HourlyVotes(
    val hour: Int,
    val voteCount: Int,
    val percentage: Double
)

enum class ResultStatus {
    PARTIAL,
    COMPLETE,
    UNDER_REVIEW,
    CERTIFIED
}

enum class VerificationStatus {
    PENDING,
    VERIFIED,
    DISPUTED,
    RECOUNTING
}

enum class WinningStatus {
    WINNER,
    RUNNER_UP,
    PARTICIPATED
}

enum class AgeRange {
    RANGE_18_25,
    RANGE_26_35,
    RANGE_36_50,
    RANGE_51_65,
    RANGE_ABOVE_65
}