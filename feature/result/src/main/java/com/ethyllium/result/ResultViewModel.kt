package com.ethyllium.result

import androidx.lifecycle.ViewModel
import com.ethyllium.domain.model.AgeRange
import com.ethyllium.domain.model.Candidates
import com.ethyllium.domain.model.DemographicStats
import com.ethyllium.domain.model.HourlyVotes
import com.ethyllium.domain.model.RegionalResult
import com.ethyllium.domain.model.Result
import com.ethyllium.domain.model.ResultStatus
import com.ethyllium.domain.model.VerificationStatus
import com.ethyllium.domain.model.VoterStats
import com.ethyllium.domain.model.VotingTimeStats
import com.ethyllium.domain.model.WinningStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ResultViewModel: ViewModel() {
    private val _state: MutableStateFlow<Result> = MutableStateFlow(Result(
        electionTitle = "2024 Presidential Election",
        startDateTime = 1709251200000, // March 1, 2024 00:00:00
        endDateTime = 1709337600000,   // March 2, 2024 00:00:00
        status = ResultStatus.COMPLETE,

        totalRegisteredVoters = 250000,
        totalVotesCast = 175000,
        invalidVotesCount = 1250,

        candidates = listOf(
            Candidates(
                candidateName = "Sarah Johnson",
                partyAffiliation = "Progressive Alliance",
                votesReceived = 68000,
                votePercentage = 38.85,
                rankPosition = 1,
                winningStatus = WinningStatus.WINNER
            ), Candidates(
                candidateName = "Michael Chen",
                partyAffiliation = "Unity Party",
                votesReceived = 58750,
                votePercentage = 33.57,
                rankPosition = 2,
                winningStatus = WinningStatus.RUNNER_UP
            ), Candidates(
                candidateName = "Robert Martinez",
                partyAffiliation = "Citizens First",
                votesReceived = 47000,
                votePercentage = 26.85,
                rankPosition = 3,
                winningStatus = WinningStatus.PARTICIPATED
            )
        ),

        demographicBreakdown = DemographicStats(
            ageGroups = mapOf(
                AgeRange.RANGE_18_25 to VoterStats(35000, 20.0, 2.5),
                AgeRange.RANGE_26_35 to VoterStats(45500, 26.0, -1.2),
                AgeRange.RANGE_36_50 to VoterStats(52500, 30.0, 1.8),
                AgeRange.RANGE_51_65 to VoterStats(28000, 16.0, -0.5),
                AgeRange.RANGE_ABOVE_65 to VoterStats(14000, 8.0, -2.6)
            ), genderDistribution = mapOf(
                "Male" to VoterStats(87500, 50.0, -0.8),
                "Female" to VoterStats(85750, 49.0, 1.2),
                "Other" to VoterStats(1750, 1.0, 0.4)
            ), firstTimeVoters = VoterStats(28000, 16.0, null)
        ),

        geographicBreakdown = listOf(
            RegionalResult(
                regionId = "REG_001",
                regionName = "Northern District",
                totalVoters = 75000,
                votesCount = 52500,
                candidateWiseResults = mapOf(
                    "CAND_001" to 22000, "CAND_002" to 18500, "CAND_003" to 12000
                ),
                turnoutPercentage = 70.0
            ), RegionalResult(
                regionId = "REG_002",
                regionName = "Southern District",
                totalVoters = 85000,
                votesCount = 59500,
                candidateWiseResults = mapOf(
                    "CAND_001" to 24000, "CAND_002" to 20250, "CAND_003" to 15250
                ),
                turnoutPercentage = 70.0
            ), RegionalResult(
                regionId = "REG_003",
                regionName = "Central District",
                totalVoters = 90000,
                votesCount = 63000,
                candidateWiseResults = mapOf(
                    "CAND_001" to 22000, "CAND_002" to 20000, "CAND_003" to 21000
                ),
                turnoutPercentage = 70.0
            )
        ),

        votingTimeStats = VotingTimeStats(
            peakVotingHours = listOf(
                HourlyVotes(9, 15750, 9.0),
                HourlyVotes(11, 19250, 11.0),
                HourlyVotes(14, 21000, 12.0),
                HourlyVotes(16, 17500, 10.0)
            ), averageVotingTime = 4.5, // minutes
            votingPatternByHour = mapOf(
                8 to 10500,
                9 to 15750,
                10 to 17500,
                11 to 19250,
                12 to 15750,
                13 to 17500,
                14 to 21000,
                15 to 19250,
                16 to 17500,
                17 to 15750,
                18 to 5250
            )
        ),

        resultHash = "8f7d3b2a1e5c9f4d6a8b2c5e7",
        verificationStatus = VerificationStatus.VERIFIED,
        lastUpdatedTimestamp = 1709337900000,
        isFinalized = true
    )
    )
    val state: StateFlow<Result> = _state.asStateFlow()
}