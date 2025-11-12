package com.example.hydroagrosense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hydroagrosense.ui.screens.Dashboard
import com.example.hydroagrosense.ui.screens.IrrigationDetails
import com.example.hydroagrosense.ui.screens.IrrigationHistory
import com.example.hydroagrosense.ui.theme.HydroAgroSenseTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
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
                            )
                        }
                        composable(
                            route = "history",
                        ) {
                            IrrigationHistory(navController = navController)
                        }
                        composable(
                            route = "details"
                        ) {
                            IrrigationDetails(
                                navController = navController,
                            )
                        }
                    }
                }
            }

        }
    }
}