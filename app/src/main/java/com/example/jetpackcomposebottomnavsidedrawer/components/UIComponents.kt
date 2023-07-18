package com.example.jetpackcomposebottomnavsidedrawer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpackcomposebottomnavsidedrawer.navigation.Screens
import com.example.jetpackcomposebottomnavsidedrawer.ui.theme.Purple700

@Composable
fun TopBar(title: String = "",
           sideNavIcon: Painter,
           notificationIcon: Painter,
           searchIcon: Painter,
           navigationIcon: Painter,
           onNavigationIconClicked: () -> Unit,
           searchIconClicked: () -> Unit,
           notificationIconClicked: () -> Unit,
           onActionsClicked: () -> Unit,) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClicked() }) {
                Image(navigationIcon, contentDescription = "Back")
            }
        },
        actions = {
            Row(modifier = Modifier.height(30.dp),verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { searchIconClicked() }) {
                    Image(searchIcon, contentDescription = "search")
                }
                IconButton(onClick = { notificationIconClicked()}) {
                    Image(notificationIcon, contentDescription = "notifications")
                }
                IconButton(onClick = { onActionsClicked() }) {
                    Image(sideNavIcon, contentDescription = "side nav")
                }
            }
        }
    )
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    screens: List<Screens.HomeScreens>,
    navController: NavController
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Purple700
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = "") },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo = navController.graph.startDestinationId
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

