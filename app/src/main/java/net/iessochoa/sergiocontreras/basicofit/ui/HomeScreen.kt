package net.iessochoa.sergiocontreras.basicofit.ui

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
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.basicofit.data.SessionStore
import net.iessochoa.sergiocontreras.basicofit.data.formatKg

@Composable
fun HomeScreen(onExerciseClick: (String) -> Unit) {
    Scaffold(
        bottomBar = {
            Surface(tonalElevation = 3.dp, modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Volumen de la sesión", style = MaterialTheme.typography.labelLarge)
                    Text(
                        "${SessionStore.totalVolume().formatKg()} kg",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Básicos de hoy", style = MaterialTheme.typography.headlineSmall)

            SessionStore.exercises.forEach { exercise ->
                Button(
                    onClick = { onExerciseClick(exercise.id) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("${exercise.name} · ${SessionStore.volumeOf(exercise.id).formatKg()} kg")
                }
            }
        }
    }
}
