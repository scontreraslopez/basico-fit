package net.iessochoa.sergiocontreras.basicofit.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.iessochoa.sergiocontreras.basicofit.data.Exercise
import net.iessochoa.sergiocontreras.basicofit.data.Serie

class BasicoFitViewModel: ViewModel() {

    private val start: List<Serie> =
        Exercise.entries.map {
            Serie(
                exercise = it,
                repes = 0,
                peso = 0.0
            )
        }

    private val _uiState = MutableStateFlow(
        BasicoFitUiState(start)
    )
    val uiState: StateFlow<BasicoFitUiState> = _uiState.asStateFlow()

    fun onActualizarValores(
        weight: Double,
        reps: Int
    ) {
        _uiState.update { currentState ->


        }

    }


}