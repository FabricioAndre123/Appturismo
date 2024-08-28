package com.jagoteori.tourismapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
    object AboutMe : Screen("about_me")
    object Login : Screen("login")
    object Register : Screen("register")
}