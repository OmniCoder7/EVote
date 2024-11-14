package com.ethyllium.evote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.ethyllium.domain.model.Voter
import com.ethyllium.evote.navigation.Destination
import com.ethyllium.evote.ui.composable.EVoteBottomNavigation
import com.ethyllium.evote.ui.composable.EVoteTopBar
import com.ethyllium.evote.ui.theme.EVoteTheme
import com.ethyllium.evote.util.VoterType
import com.ethyllium.home.HomeScreen
import com.ethyllium.home.HomeViewModel
import com.ethyllium.invite.InviteListScreen
import com.ethyllium.invite.InviteViewModel
import com.ethyllium.invite.state.InviteReaction
import com.ethyllium.login.LoginScreen
import com.ethyllium.login.LoginViewModel
import com.ethyllium.login.state.LoginReaction
import com.ethyllium.register.RegisterReaction
import com.ethyllium.register.RegisterScreen
import com.ethyllium.register.RegisterViewModel
import com.ethyllium.result.ResultScreen
import com.ethyllium.result.ResultViewModel
import com.ethyllium.root.OAuthClient
import com.ethyllium.root.RootReaction
import com.ethyllium.root.RootScreen
import com.ethyllium.root.RootViewModel
import com.ethyllium.root.SplashScreen
import com.ethyllium.upcoming.UpcomingScreen
import com.ethyllium.upcoming.UpcomingViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.typeOf

class EVoteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EVoteTheme {
                val eVoteViewModel = koinViewModel<EVoteViewModel>()
                val state by eVoteViewModel.state.collectAsStateWithLifecycle(UiState.Loading)
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.navigationBars), bottomBar = {
                    if (state is UiState.Authenticated) EVoteBottomNavigation(navController = navController)
                }, topBar = {
                    if (state is UiState.Authenticated) EVoteTopBar(title = (state as UiState.Authenticated).voter.displayName)
                }) { innerPadding ->
                    NavHost(navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        startDestination = state.startDestination,
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(300)
                            ) + fadeIn(animationSpec = tween(300))
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(300)
                            ) + fadeOut(animationSpec = tween(300))
                        }) {

                        composable<Destination.Splash> {
                            SplashScreen()
                        }

                        navigation<Destination.AuthGraph>(startDestination = Destination.Root) {

                            composable<Destination.Root> {
                                val viewModel = koinViewModel<RootViewModel> {
                                    parametersOf(
                                        OAuthClient(
                                            context = this@EVoteActivity, get()
                                        ), RootReaction(onHome = { voter ->
                                            navController.navigate(Destination.Home(voter)) {
                                                popUpTo(Destination.AuthGraph) {
                                                    inclusive = true
                                                }
                                            }
                                        }, onLogin = {
                                            navController.navigate(Destination.Login)
                                        }, onRegister = {
                                            navController.navigate(Destination.Register)
                                        })
                                    )
                                }
                                RootScreen(rootAction = viewModel::onAction)
                            }
                            composable<Destination.Login> {
                                val viewModel = koinViewModel<LoginViewModel> {
                                    parametersOf(
                                        LoginReaction(onRegister = {
                                            navController.navigate(Destination.Register)
                                        }, onHome = { user ->
                                            navController.navigate(Destination.Home(user)) {
                                                popUpTo(Destination.AuthGraph) {
                                                    inclusive = true
                                                }
                                            }
                                        }, onForgotPassword = {
                                            navController.navigate(Destination.ForgotPassword)
                                        })
                                    )
                                }
                                LoginScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    state = viewModel.state.collectAsStateWithLifecycle().value,
                                    onAction = viewModel::onAction
                                )
                            }
                            composable<Destination.Register> {
                                val viewModel = koinViewModel<RegisterViewModel> {
                                    parametersOf(
                                        RegisterReaction(onLogin = {
                                            navController.navigate(Destination.Login)
                                        }, onHome = { user ->
                                            navController.navigate(Destination.Home(user)) {
                                                popUpTo(Destination.AuthGraph) {
                                                    inclusive = true
                                                }
                                            }
                                        }, onForgotPassword = {
                                            navController.navigate(Destination.ForgotPassword)
                                        })
                                    )
                                }
                                RegisterScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    state = viewModel.state.collectAsStateWithLifecycle().value,
                                    onAction = viewModel::onAction
                                )
                            }
                        }

                        navigation<Destination.ResultGraph>(startDestination = Destination.Result) {
                            composable<Destination.Result> {
                                val viewModel = koinViewModel<ResultViewModel>()
                                ResultScreen(result = viewModel.state.collectAsStateWithLifecycle().value)
                            }
                        }
                        navigation<Destination.UpcomingGraph>(startDestination = Destination.Upcoming) {
                            composable<Destination.Upcoming> {
                                koinViewModel<UpcomingViewModel>()
                                UpcomingScreen()
                            }
                        }
                        navigation<Destination.InviteGraph>(startDestination = Destination.Invite) {
                            composable<Destination.Invite> {
                                val viewModel = koinViewModel<InviteViewModel> {
                                    parametersOf(
                                        InviteReaction(
                                            toDetailedInvite = { invite ->
                                                // TODO: Navigate to detailed invite
                                            }
                                        )
                                    )
                                }
                                val state by viewModel.state.collectAsStateWithLifecycle()
                                InviteListScreen(
                                    inviteListState = state, onAction = viewModel::onAction
                                )
                            }
                        }
                        navigation<Destination.MainGraph>(startDestination = Destination.Home::class) {
                            composable<Destination.Home>(
                                typeMap = mapOf(typeOf<Voter>() to VoterType)
                            ) { backStackEntry: NavBackStackEntry ->
                                val viewModel = koinViewModel<HomeViewModel>()
                                val homeData: Destination.Home = backStackEntry.toRoute()
                                eVoteViewModel.setUser(homeData.voter)
                                val state by viewModel.state.collectAsStateWithLifecycle()
                                HomeScreen(
                                    state = state,
                                    voter = homeData.voter,
                                    copyToClipboard = viewModel::copyToClipboard
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}