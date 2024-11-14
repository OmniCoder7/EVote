package com.ethyllium.evote.ui.composable

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ethyllium.evote.R
import com.ethyllium.evote.navigation.Destination
import com.ethyllium.home.NoTintIcon
import kotlinx.serialization.Serializable

@SuppressLint("RestrictedApi")
@Composable
fun EVoteBottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val bottomScreens = remember {
        listOf(
            BottomScreens.Home, BottomScreens.Upcoming, BottomScreens.Result, BottomScreens.Invite
        )
    }
    NavigationBar(modifier = modifier) {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination
        bottomScreens.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.hasRoute(
                    screen.route::class.qualifiedName!!, null
                )
            } == true
            NavigationBarItem(icon = {
                if (isSelected) NoTintIcon(id = screen.selectedIcon)
                else NoTintIcon(id = screen.unselectedIcon)
            }, label = { Text(screen.name) }, selected = isSelected, onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
        }
    }
}

@Serializable
sealed class BottomScreens<T>(
    val name: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val route: T,
) {

    @Serializable
    data object Home : BottomScreens<Destination.MainGraph>(
        name = "Home",
        unselectedIcon = R.drawable.home_outline,
        selectedIcon = R.drawable.home_filled,
        route = Destination.MainGraph
    )

    @Serializable
    data object Upcoming : BottomScreens<Destination.UpcomingGraph>(
        name = "Upcoming",
        unselectedIcon = R.drawable.upcoming_outline,
        selectedIcon = R.drawable.upcoming_filled,
        route = Destination.UpcomingGraph
    )

    @Serializable
    data object Result : BottomScreens<Destination.ResultGraph>(
        name = "Result",
        unselectedIcon = R.drawable.result_outline,
        selectedIcon = R.drawable.result_filled,
        route = Destination.ResultGraph
    )

    @Serializable
    data object Invite : BottomScreens<Destination.InviteGraph>(
        name = "Invite",
        unselectedIcon = R.drawable.invite_outline,
        selectedIcon = R.drawable.invite_filled,
        route = Destination.InviteGraph
    )

}