package com.example.hydroagrosense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hydroagrosense.domain.HydroViewModel
import com.example.hydroagrosense.domain.SettingsViewModel
import com.example.hydroagrosense.ui.screens.Dashboard
import com.example.hydroagrosense.ui.screens.IrrigationDetails
import com.example.hydroagrosense.ui.screens.IrrigationHistory
import com.example.hydroagrosense.ui.screens.Notifications
import com.example.hydroagrosense.ui.screens.SettingsScreen
import com.example.hydroagrosense.ui.theme.HydroAgroSenseTheme

class MainActivity : ComponentActivity() {

    // import androidx.activity.viewModels
    private val hydroViewModel: HydroViewModel by viewModels()
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HydroAgroSenseTheme {
                val navController = rememberNavController()

                Column(modifier = Modifier.padding(0.dp)) {
                    NavHost(
                        navController = navController,
                        startDestination = "dashboard"
                    ) {
                        composable("dashboard") {
                            Dashboard(
                                navController = navController,
                                viewModel = hydroViewModel
                            )
                        }
                        composable("notifications") {
                            Notifications(navController = navController)
                        }
                        composable("history") {
                            IrrigationHistory(
                                navController = navController,
                                viewModel = hydroViewModel
                            )
                        }
                        composable("details") {
                            IrrigationDetails(
                                navController = navController,
                                viewModel = hydroViewModel
                            )
                        }
                        composable("settings") {
                            SettingsScreen(
                                navController = navController,
                                viewModel = settingsViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}
