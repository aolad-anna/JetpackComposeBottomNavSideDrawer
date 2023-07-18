package com.arvind.bottomnavwithsidebar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposebottomnavsidedrawer.components.BottomBar
import com.example.jetpackcomposebottomnavsidedrawer.components.Drawer
import com.example.jetpackcomposebottomnavsidedrawer.components.TopBar
import com.arvind.bottomnavwithsidebar.components.dialog.LogoutDialog
import com.arvind.bottomnavwithsidebar.navigation.*
import com.example.jetpackcomposebottomnavsidedrawer.ui.theme.BottomNavWithSideBarTheme
import com.example.jetpackcomposebottomnavsidedrawer.utils.BackPressHandler
import com.example.jetpackcomposebottomnavsidedrawer.utils.LocalBackPressedDispatcher
import com.example.jetpackcomposebottomnavsidedrawer.view.bottom.FavoriteScreen
import com.example.jetpackcomposebottomnavsidedrawer.view.bottom.NearbyScreen
import com.example.jetpackcomposebottomnavsidedrawer.view.bottom.ReservedScreen
import com.example.jetpackcomposebottomnavsidedrawer.view.bottom.SavedScreen
import com.arvind.bottomnavwithsidebar.view.sidebar.*
import com.example.jetpackcomposebottomnavsidedrawer.viewmodel.MainViewModel
import com.example.jetpackcomposebottomnavsidedrawer.navigation.Screens
import com.example.jetpackcomposebottomnavsidedrawer.navigation.screensInHomeFromBottomNav
import com.example.jetpackcomposebottomnavsidedrawer.view.sidebar.AboutUsScreen
import com.example.jetpackcomposebottomnavsidedrawer.view.sidebar.AppSettingsScreen
import com.example.jetpackcomposebottomnavsidedrawer.view.sidebar.HelpScreen
import com.example.jetpackcomposebottomnavsidedrawer.view.sidebar.HomeScreen
import com.example.jetpackcomposebottomnavsidedrawer.view.sidebar.MyProfileScreen
import com.example.jetpackcomposebottomnavsidedrawer.view.sidebar.MyReviewsScreen
import com.example.jetpackcomposebottomnavsidedrawer.view.sidebar.NotificationsScreen
import com.example.jetpackcomposebottomnavsidedrawer.view.sidebar.QRCodeScreen
import com.example.jetpackcomposebottomnavsidedrawer.view.sidebar.VisitHistoryScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavWithSideBarTheme {
                // A surface container using the 'background' color from the theme
                CompositionLocalProvider(LocalBackPressedDispatcher provides this.onBackPressedDispatcher) {
                    AppScaffold()
                }
            }
        }
    }

    @Composable
    fun AppScaffold() {
        val viewModel: MainViewModel = viewModel()
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        val currentScreen by viewModel.currentScreen.observeAsState()

        if (scaffoldState.drawerState.isOpen) {
            BackPressHandler {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }

        var topBar: @Composable () -> Unit = {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr ) {
                TopBar(
                    title = currentScreen!!.title,
                    sideNavIcon = painterResource(id = R.drawable.collect),
                    navigationIcon = painterResource(id = R.drawable.strawberry),
                    searchIcon = painterResource(id = R.drawable.search),
                    notificationIcon = painterResource(id = R.drawable.notification),
                    onNavigationIconClicked = {
                        navController.navigate(route = "home")
                    },
                    onActionsClicked = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    },
                    searchIconClicked = { navController.navigate(route = "qr-code") },
                    notificationIconClicked = { navController.navigate(route = "notifications") },
                )
            }
        }
        if (currentScreen == Screens.DrawerScreens.QRCode) {
            topBar = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    TopBar(
                        title = currentScreen!!.title,
                        sideNavIcon = painterResource(id = R.drawable.collect),
                        navigationIcon = painterResource(id = R.drawable.strawberry),
                        searchIcon = painterResource(id = R.drawable.search),
                        notificationIcon = painterResource(id = R.drawable.notification),
                        onNavigationIconClicked = {
                            scope.launch {
                                navController.popBackStack()
                            }
                        },
                        onActionsClicked = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        },
                        searchIconClicked = { navController.navigate(route = "qr-code") },
                        notificationIconClicked = { navController.navigate(route = "notifications") },
                    )
                }
            }
        }

        val bottomBar: @Composable () -> Unit = {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr ) {
                if (currentScreen == Screens.DrawerScreens.Home || currentScreen is Screens.HomeScreens) {
                    BottomBar(
                        navController = navController,
                        screens = screensInHomeFromBottomNav
                    )
                }
            }
        }

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Scaffold(
                topBar = {
                    topBar()
                },
                bottomBar = {
                    bottomBar()
                },
                scaffoldState = scaffoldState,
                drawerContent = {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr ) {
                        Drawer { route ->
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            navController.navigate(
                                route,
                                NavOptions.Builder()
                                    .setPopUpTo(navController.graph.startDestinationId, inclusive = true)
                                    .setLaunchSingleTop(true)
                                    .build()
                            )
                        }
                    }
                },
                drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
            ) {
                NavigationHost(navController = navController, viewModel = viewModel)
            }
        }
    }

    @Composable
    fun NavigationHost(navController: NavController, viewModel: MainViewModel) {
        NavHost(
            navController = navController as NavHostController,
            startDestination = Screens.DrawerScreens.Home.route
        ) {
            composable(Screens.DrawerScreens.Home.route) { HomeScreen(viewModel = viewModel) }
            composable(Screens.HomeScreens.Favorite.route) { FavoriteScreen(viewModel = viewModel) }
            composable(Screens.HomeScreens.NearBy.route) { NearbyScreen(viewModel = viewModel) }
            composable(Screens.HomeScreens.Reserved.route) { ReservedScreen(viewModel = viewModel) }
            composable(Screens.HomeScreens.Saved.route) { SavedScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.MyProfile.route) { MyProfileScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.MyReviews.route) { MyReviewsScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.VisitsHistory.route) { VisitHistoryScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.Notifications.route) { NotificationsScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.AppSettings.route) { AppSettingsScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.QRCode.route) { QRCodeScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.Help.route) { HelpScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.AboutUs.route) { AboutUsScreen(viewModel = viewModel) }
            composable(Screens.DrawerScreens.Logout.route) { LogoutDialog() }

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        BottomNavWithSideBarTheme {
            AppScaffold()
        }
    }
}

