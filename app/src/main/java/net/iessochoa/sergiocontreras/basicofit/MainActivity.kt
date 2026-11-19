package net.iessochoa.sergiocontreras.basicofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.iessochoa.sergiocontreras.basicofit.ui.HomeScreen
import net.iessochoa.sergiocontreras.basicofit.ui.theme.BasicoFitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicoFitTheme {
                // TODO: navegación pendiente (Navigation 3). Por ahora solo se muestra la pantalla principal.
                HomeScreen(onExerciseClick = { })
            }
        }
    }
}
