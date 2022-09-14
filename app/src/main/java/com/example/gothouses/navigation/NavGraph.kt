package com.example.gothouses.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gothouses.screens.HomePage
import com.example.gothouses.screens.HouseDetail

@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Route.HomePage){
            HomePage(navController = navController)
        }
        composable(Route.HouseDetail){
            HouseDetail(navController = navController)
        }
    }
}