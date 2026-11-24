package net.iessochoa.sergiocontreras.basicofit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.sergiocontreras.basicofit.ui.BasicoFitViewModel
import net.iessochoa.sergiocontreras.basicofit.ui.HomeScreen
import net.iessochoa.sergiocontreras.basicofit.ui.theme.BasicoFitTheme

@Composable
fun BasicoFitApp(
    viewModel: BasicoFitViewModel = viewModel()
) {
    BasicoFitTheme {
        // TODO: navegación pendiente (Navigation 3). Por ahora solo se muestra la pantalla principal.

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        HomeScreen(
            onExerciseClick = { },
            uiState = uiState
        )
    }
}