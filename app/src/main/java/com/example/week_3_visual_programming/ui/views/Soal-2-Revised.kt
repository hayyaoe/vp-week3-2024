package com.example.week_3_visual_programming.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.*


@Composable
fun CalculatorApp() {
    var input by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(Color(0xFFECEFF1))
            .padding(16.dp)
            .fillMaxSize(), // Light background
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = input,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 32.sp,
                color = Color(0xFF37474F) // Darker color for text
            )
        }

        // Operation buttons with softer colors
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { input = calculate(input, "sin") },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3E5FC)) // Soft blue
            ) {
                Text("sin", color = Color(0xFF0D47A1)) // Darker blue for text
            }
            Button(
                onClick = { input = calculate(input, "cos") },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3E5FC))
            ) {
                Text("cos", color = Color(0xFF0D47A1))
            }
            Button(
                onClick = { input = calculate(input, "tan") },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3E5FC))
            ) {
                Text("tan", color = Color(0xFF0D47A1))
            }
            Button(
                onClick = { input = calculate(input, "sqrt") },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3E5FC))
            ) {
                Text("sqrt", color = Color(0xFF0D47A1))
            }
        }
        

        // Clear button with custom styling
        Button(
            onClick = { input = "" },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF9A9A)) // Soft red
        ) {
            Text("Clear", color = Color.White) // White text for clear button
        }

        // Number buttons
        for (row in 0..2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (col in 0..2) {
                    val number = row * 3 + col + 1
                    if (number <= 9) {
                        Button(
                            shape = CircleShape,
                            onClick = { input += number.toString() },
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1.5f), // Ensures the button is circular
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3E5FC)) // Soft blue
                        ) {
                            Text(
                                text = number.toString(),
                                fontSize = 42.sp,
                                color = Color(0xFF0D47A1) // Darker blue for number text
                            )
                        }
                    }
                }
            }
        }

        // Add the 0 button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                shape = CircleShape,
                onClick = { input += "0" },
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(3f), // Ensures the button is circular
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB3E5FC)) // Soft blue
            ) {
                Text(
                    text = "0",
                    fontSize = 34.sp,
                    color = Color(0xFF0D47A1) // Darker blue for number text
                )
            }
        }
    }
}

fun calculate(expression: String, func: String): String {
    return try {
        val value = expression.toDoubleOrNull() ?: return "Error"
        when (func) {
            "sin" -> sin(Math.toRadians(value)).toString()
            "cos" -> cos(Math.toRadians(value)).toString()
            "tan" -> tan(Math.toRadians(value)).toString()
            "sqrt" -> sqrt(value).toString()
            else -> value.toString()
        }
    } catch (e: Exception) {
        "Error"
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatorApp()
}