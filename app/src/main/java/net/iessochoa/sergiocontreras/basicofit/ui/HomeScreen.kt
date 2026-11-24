package net.iessochoa.sergiocontreras.basicofit.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.basicofit.data.Exercise
import net.iessochoa.sergiocontreras.basicofit.data.Serie
import net.iessochoa.sergiocontreras.basicofit.data.SessionStore
import net.iessochoa.sergiocontreras.basicofit.data.formatKg
import net.iessochoa.sergiocontreras.basicofit.ui.theme.BasicoFitTheme

@Composable
fun HomeScreen(
    uiState: BasicoFitUiState,
    onExerciseClick: (Exercise) -> Unit,
    modifier: Modifier = Modifier
) {
    val sessionVolume = uiState.series.sumOf {
        it.repes*it.peso
    }
    Scaffold(

        bottomBar = {
            Surface(tonalElevation = 3.dp, modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Volumen de la sesión", style = MaterialTheme.typography.labelLarge)
                    Text(
                        "${sessionVolume} kg",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Básicos de hoy", style = MaterialTheme.typography.headlineSmall)

            uiState.series.forEach { serie ->
                val exerciseVolume = serie.peso * serie.repes
                Button(
                    onClick = {
                        //Log.d("DEBUG VIEW", serie.exercise.exerciseName)
                        onExerciseClick(serie.exercise)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("${serie.exercise.exerciseName} · ${exerciseVolume.formatKg()} kg")
                }
            }
            /*
            SessionStore.exercises.forEach { exercise ->
                Button(
                    onClick = { onExerciseClick(exercise.id) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("${exercise.name} · ${SessionStore.volumeOf(exercise.id).formatKg()} kg")
                }
            }
            */
        }
    }
}

@Preview(showBackground=true)
@Composable
fun HomeScreenPreview() = BasicoFitTheme() {

    val start: List<Serie> =
        Exercise.entries.map {
            Serie(
                exercise = it,
                repes = 5,
                peso = 70.0
            )
        }

    val previewState = BasicoFitUiState(
        series = start,
        selectedExercise = start.first().exercise
    )

    HomeScreen(
        onExerciseClick = { },
        uiState = previewState
    )
}