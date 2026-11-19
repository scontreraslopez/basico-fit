package net.iessochoa.sergiocontreras.basicofit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.basicofit.data.SessionStore
import net.iessochoa.sergiocontreras.basicofit.data.WorkSet
import net.iessochoa.sergiocontreras.basicofit.data.formatKg
import net.iessochoa.sergiocontreras.basicofit.ui.theme.BasicoFitTheme

@Composable
fun ExerciseScreen(
    exerciseId: String,
    onBack: () -> Unit
) {
    val exercise = SessionStore.exercises.first { it.id == exerciseId }

    // Estado del formulario dentro del propio composable (stateful).
    var weightText by remember { mutableStateOf("") }
    var repsText by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(exercise.name, style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = weightText,
            onValueChange = { weightText = it },
            label = { Text("Peso (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = repsText,
            onValueChange = { repsText = it },
            label = { Text("Repeticiones") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = {
                val weight = weightText.replace(',', '.').toDoubleOrNull()
                val reps = repsText.toIntOrNull()
                if (weight == null || reps == null || reps <= 0) {
                    error = "Revisa peso y repeticiones"
                } else {
                    SessionStore.sets.add(WorkSet(exercise.id, weight, reps))
                    weightText = ""
                    repsText = ""
                    error = null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Añadir serie")
        }

        HorizontalDivider()

        Text("Series hechas", style = MaterialTheme.typography.titleMedium)
        val mySets = SessionStore.setsOf(exercise.id)
        if (mySets.isEmpty()) {
            Text("Ninguna todavía")
        }
        mySets.forEachIndexed { index, set ->
            Text("${index + 1}. ${set.weightKg.formatKg()} kg × ${set.reps} = ${set.volume.formatKg()} kg")
        }

        Text(
            "Volumen del ejercicio: ${SessionStore.volumeOf(exercise.id).formatKg()} kg",
            style = MaterialTheme.typography.titleSmall
        )

        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Volver")
        }
    }
}

@Preview(showBackground=true)
@Composable
fun ExerciseScreenPreview() = BasicoFitTheme() {
    ExerciseScreen(
        exerciseId = "bench"
    ) { }
}
