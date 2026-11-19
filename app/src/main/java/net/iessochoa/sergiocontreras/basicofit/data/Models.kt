package net.iessochoa.sergiocontreras.basicofit.data

import androidx.compose.runtime.mutableStateListOf

data class WorkSet(val exerciseId: String, val weightKg: Double, val reps: Int) {
    val volume: Double get() = weightKg * reps
}

fun Double.formatKg(): String =
    if (this % 1.0 == 0.0) toInt().toString() else "%.1f".format(this)

/**
 * MAL A PROPÓSITO (versión 1):
 * Estado global mutable en un singleton. Cualquier pantalla lee y escribe aquí directamente.
 * No hay ViewModel, no hay repositorio, no hay elevación: las pantallas se acoplan a este objeto.
 */
object SessionStore {

    val sets = mutableStateListOf<WorkSet>()

    fun setsOf(exerciseId: String): List<WorkSet> = sets.filter { it.exerciseId == exerciseId }

    fun volumeOf(exerciseId: String): Double = setsOf(exerciseId).sumOf { it.volume }

    fun totalVolume(): Double = sets.sumOf { it.volume }
}
