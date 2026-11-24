package net.iessochoa.sergiocontreras.basicofit.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

    private val startExercise = start.first().exercise

    private val _uiState = MutableStateFlow(
        BasicoFitUiState(
            series = start,
            selectedExercise = startExercise
        )
    )
    val uiState: StateFlow<BasicoFitUiState> = _uiState.asStateFlow()

    fun onActualizarValores(
        weight: Double,
        reps: Int
    ) {

        _uiState.update { currentState ->
            val selectedExercise = currentState.selectedExercise
            val selectedSerie = currentState.series.first { it.exercise == selectedExercise}

            val updatedSerie = selectedSerie.copy(
                repes = reps,
                peso = weight
            )

            val seriesActualizadas = currentState.series - selectedSerie + updatedSerie


            currentState.copy(
                series = seriesActualizadas
            )

        }

    }

    fun onSelectExercise(
        selectedExercise: Exercise
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedExercise = selectedExercise
            )

        }

    }


}