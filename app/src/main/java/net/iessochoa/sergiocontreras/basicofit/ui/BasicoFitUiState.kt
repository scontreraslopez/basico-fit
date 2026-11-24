package net.iessochoa.sergiocontreras.basicofit.ui

import net.iessochoa.sergiocontreras.basicofit.data.Exercise
import net.iessochoa.sergiocontreras.basicofit.data.Serie

data class BasicoFitUiState(
    val series: List<Serie>,
    val selectedExercise: Exercise
)