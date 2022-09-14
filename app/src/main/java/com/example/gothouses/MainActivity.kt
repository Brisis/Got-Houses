package com.example.gothouses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.example.gothouses.navigation.Route
import com.example.gothouses.navigation.SetupNavGraph
import com.example.gothouses.ui.theme.GotHousesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GotHousesTheme {
                val navController = rememberNavController()
                Scaffold(
                    content = {
                        SetupNavGraph(navController = navController, startDestination = Route.HomePage)
                    }
                )
            }
        }
    }
}
