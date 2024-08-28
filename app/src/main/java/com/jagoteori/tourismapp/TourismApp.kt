package com.jagoteori.tourismapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.jagoteori.tourismapp.ui.navigation.Screen
import com.jagoteori.tourismapp.ui.screen.about_me.AboutMeScreen
import com.jagoteori.tourismapp.ui.screen.detail.DetailScreen
import com.jagoteori.tourismapp.ui.screen.home.HomeScreen
import com.jagoteori.tourismapp.ui.screen.login.LoginScreen
import com.jagoteori.tourismapp.ui.screen.login.RegisterScreen

@Composable
fun TourismApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val startDestination = if (auth.currentUser != null) {
        Screen.Home.route
    } else {
        Screen.Login.route
    }

    Scaffold(scaffoldState = scaffoldState) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier.padding(paddingValues)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    navController = navController,
                    onLoginSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Register.route) {
                RegisterScreen(
                    navController = navController,
                    onRegisterSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Register.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    },
                    navigateToAboutMe = {
                        navController.navigate(Screen.AboutMe.route)
                    },
                    onLogout = {
                        auth.signOut()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.AboutMe.route) {
                AboutMeScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("id") ?: -1
                DetailScreen(
                    id = id,
                    scaffoldState = scaffoldState,
                    scope = scope,
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}