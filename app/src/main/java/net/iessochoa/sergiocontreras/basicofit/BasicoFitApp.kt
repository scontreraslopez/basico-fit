package net.iessochoa.sergiocontreras.basicofit

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import net.iessochoa.sergiocontreras.basicofit.navigation.Exercise
import net.iessochoa.sergiocontreras.basicofit.navigation.Home
import net.iessochoa.sergiocontreras.basicofit.ui.BasicoFitViewModel
import net.iessochoa.sergiocontreras.basicofit.ui.ExerciseScreen
import net.iessochoa.sergiocontreras.basicofit.ui.HomeScreen
import net.iessochoa.sergiocontreras.basicofit.ui.theme.BasicoFitTheme

@Composable
fun BasicoFitApp(
    viewModel: BasicoFitViewModel = viewModel()
) {
    BasicoFitTheme {
        // TODO: navegación pendiente (Navigation 3). Por ahora solo se muestra la pantalla principal.

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val backStack = rememberNavBackStack(Home)

        Scaffold { innerPadding ->
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                modifier = Modifier.padding(innerPadding),
                entryProvider = entryProvider {
                    entry<Home> {
                        HomeScreen(
                            uiState = uiState,
                            onExerciseClick = {
                                viewModel::onSelectExercise
                                backStack.add(Exercise)
                            }
                        )

                    }
                    entry<Exercise> {
                        ExerciseScreen(
                            uiState = uiState,
                            onActualizarValores = viewModel::onActualizarValores,
                        )

                    }


                }

            )
        }

        /*
        HomeScreen(
            onExerciseClick = { },
            uiState = uiState
        )

         */
    }
}