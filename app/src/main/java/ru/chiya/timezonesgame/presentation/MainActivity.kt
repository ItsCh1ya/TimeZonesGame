package ru.chiya.timezonesgame.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ru.chiya.timezonesgame.presentation.screen.casual.CasualScreen
import ru.chiya.timezonesgame.presentation.screen.main.MainScreen
import ru.chiya.timezonesgame.presentation.screen.play_for_time.PlayForTimeScreen
import ru.chiya.timezonesgame.presentation.screen.remember.RememberScreen
import ru.chiya.timezonesgame.presentation.screen.result.ResultScreen
import ru.chiya.timezonesgame.presentation.ui.theme.BackgroundColor
import ru.chiya.timezonesgame.presentation.ui.theme.TimeZonesGameTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeZonesGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundColor
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "main"
                    ) {
                        composable(route = "main") {
                            MainScreen(navController = navController)
                        }
                        // destination - screen after remember
                        composable(
                            route = "remember/{destination}",
                            arguments = listOf(navArgument("destination") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            RememberScreen(
                                navController = navController,
                                destination = backStackEntry.arguments?.getString("destination")!! // TODO: null-safety
                            )
                        }
                        composable(route = "casual") { CasualScreen(navController = navController) }
                        composable(route = "play_for_time") { PlayForTimeScreen(navController = navController) }
                        composable(
                            route = "result/{score}",
                            arguments = listOf(navArgument("score") {
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            ResultScreen(
                                navController = navController,
                                score = backStackEntry.arguments?.getInt("score")!! // TODO: null-safety
                            )
                        }
                    }
                }
            }
        }
    }
}