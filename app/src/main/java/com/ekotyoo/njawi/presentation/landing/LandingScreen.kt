package com.ekotyoo.njawi.presentation.landing

import android.media.Image
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ekotyoo.njawi.presentation.theme.NjawiTheme

class LandingScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NjawiTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Vrizas")
                    Greeting("Izza")
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NjawiTheme {
        Column {
            Greeting("Vrizas")
            Greeting("Izza")
        }
    }
}