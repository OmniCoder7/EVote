package com.ethyllium.result

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.ethyllium.result.composable.LinearSlider
import com.ethyllium.result.composable.NoTintIcon
import com.ethyllium.result.composable.TComposable

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier, result: Result
) {
    var selectedRowIndex = remember { mutableIntStateOf(0) }
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(result = result)
        Info(result = result)
        TabRow(selectedTabIndex = selectedRowIndex.intValue) {
            tabList.forEach { tab ->
                Tab(onClick = {
                    selectedRowIndex.intValue = tabList.indexOf(tab)
                },
                    selected = tabList.indexOf(tab) == selectedRowIndex.intValue,
                    text = { Text(text = tab.title) })
            }
        }
        when (selectedRowIndex.intValue) {
            0 -> Overview(result = result)
            1 -> Demographics(result = result)
            2 -> Timeline(result = result)
        }
    }
}

@Composable
fun Overview(
    modifier: Modifier = Modifier, result: Result
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.feature_result_candidate_results),
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 24.sp),
            modifier = Modifier.padding(top = 8.dp, start = 16.dp)
        )
        result.candidates.forEachIndexed { index: Int, candidate: Candidates ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
            ) {
                NoTintIcon(id = medals[index].icon)
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text(text = candidate.candidateName, style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp))
                        Text(
                            text = candidate.votesReceived.toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = candidate.partyAffiliation,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF7E7E7E)
                        )
                        Text(
                            text = candidate.votePercentage.toString() + "%",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF7E7E7E)
                        )
                    }
                    LinearSlider(fraction = candidate.votePercentage.toFloat() / 100, progressColor = medals[index].progressColor,
                        modifier = Modifier.padding(vertical = 8.dp),
                        strokeWidth = 15f)
                }
            }
        }
    }
}

@Composable
fun Demographics(
    modifier: Modifier = Modifier, result: Result
) {

}

@Composable
fun Timeline(
    modifier: Modifier = Modifier, result: Result
) {

}

@Composable
fun Info(result: Result) {
    TComposable(containerColor = Color(0xFFB1CCF6), start = {
        NoTintIcon(id = R.drawable.group, tint = Color.Blue)
    }, endTop = {
        Text(
            text = stringResource(R.string.feature_result_total_votes_cast), color = Color.Blue
        )
    }, endBottom = {
        Text(
            text = result.totalVotesCast.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold
        )
    })
    TComposable(containerColor = Color(0xFFC7A0EF),
        start = {
            NoTintIcon(id = R.drawable.gold)
        },
        endTop = { Text(text = stringResource(R.string.feature_result_voter_turnout)) },
        endBottom = {
            Text(
                text = (result.totalVotesCast.toDouble() / result.totalRegisteredVoters.toDouble()).times(
                    100
                ).toString() + "%",
                color = Color(0xFF6819D7),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        })
    TComposable(containerColor = Color(0xFFADEA97), start = {
        NoTintIcon(id = R.drawable.group, tint = Color(0xFF079D26))
    }, endTop = {
        Text(
            text = stringResource(R.string.feature_result_registered_voter),
            color = Color(0xFF079D26)
        )
    }, endBottom = {
        Text(
            text = result.totalRegisteredVoters.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    })
}

@Composable
private fun Header(result: Result) {
    val resultStatusColor = ResultStatusColors.getStatusColor(result.status)
    Text(
        text = result.electionTitle,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center
    )
    Text(
        text = "Status: ${result.status.name}",
        fontSize = 18.sp,
        color = resultStatusColor.textColor,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(
                color = resultStatusColor.backgroundColor, shape = RoundedCornerShape(40)
            )
            .padding(8.dp)
    )

}


@Preview
@Composable
private fun ResultScreen_Preview() {
    ResultScreen(
        result = Result(
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
}

object ResultStatusColors {
    fun getStatusColor(status: ResultStatus): StatusColorInfo {
        return when (status) {
            ResultStatus.PARTIAL -> StatusColorInfo(
                backgroundColor = Color(0xffFEF3C7),
                textColor = Color(0xffD97706),
            )

            ResultStatus.COMPLETE -> StatusColorInfo(
                backgroundColor = Color(0xffDCFCE7),
                textColor = Color(0xff16A34A),
            )

            ResultStatus.UNDER_REVIEW -> StatusColorInfo(
                backgroundColor = Color(0xffFEE2E2),
                textColor = Color(0xffDC2626),
            )

            ResultStatus.CERTIFIED -> StatusColorInfo(
                backgroundColor = Color(0xffE0E7FF),
                textColor = Color(0xff4F46E5),
            )
        }
    }
}

data class StatusColorInfo(
    val backgroundColor: Color,
    val textColor: Color,
)

data class TabState(
    val title: String
)

val tabList = listOf(
    TabState("Results Overview"),
    TabState("Demographics"),
    TabState("Timeline"),
)

val medals = listOf(
    Position(
        icon = R.drawable.gold,
        progressColor = Color(0xFFD4AF37)
    ),
    Position(
        icon = R.drawable.silver,
        progressColor = Color(0xFFC0C0C0)
    ),
    Position(
        icon = R.drawable.bronze,
        progressColor = Color(0xFFCD7F32)
    )
)

data class Position(
    @DrawableRes val icon: Int,
    val progressColor: Color
)