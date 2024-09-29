package com.example.week_3_visual_programming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.week_3_visual_programming.ui.theme.Week3VisualProgrammingTheme
import com.example.week_3_visual_programming.ui.views.ReactionTimeTest
import com.example.week_3_visual_programming.ui.views.VerySimpleNote

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3VisualProgrammingTheme {
                // VerySimpleNote()
                ReactionTimeTest()
            }
        }
    }
}
