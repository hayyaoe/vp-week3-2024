package com.example.week_3_visual_programming.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week_3_visual_programming.R
import kotlinx.coroutines.delay
import kotlin.random.Random


@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReactionTimeTest (){

    var bestScore by rememberSaveable { mutableLongStateOf(100000L) }
    var countdownTime by remember { mutableLongStateOf( 0L) }
    var reactionTime by remember { mutableLongStateOf(0L) }
    var isStarted by remember { mutableStateOf(false) }
    var heading by remember { mutableStateOf("Reaction\n\nTest") }
    var iconValue by remember { mutableIntStateOf(R.drawable.baseline_bolt_24) }
    var message by remember { mutableStateOf("Click to Start") }
    var backgroundColor by remember { mutableLongStateOf(0xFFadd8e6) }

    fun resetStates(){
        countdownTime = Random.nextLong(3000, 6001)
        reactionTime = 0L
    }

    LaunchedEffect(isStarted) {
        if (isStarted){

            resetStates()

            backgroundColor = 0xFFFF6961
            heading = "Be Prepared"
            message = "Wait Till Green"
            iconValue = R.drawable.baseline_warning_24

            while (true){
                delay(1L)
                countdownTime--

                if (countdownTime == 0L){
                    break
                }
            }

            while (true){
                delay(1L)
                reactionTime++

                backgroundColor = 0xFF77dd77
                heading = "It Is Time"
                message = "Press The Button Now"
                iconValue = R.drawable.baseline_directions_run_24

                if(!isStarted){
                    backgroundColor = if (reactionTime < 200 ) 0xFFadd8e6 else if (reactionTime < 250) 0xFF779ECB else 0xFFD5B60A
                    heading = if (reactionTime < 200 ) "You Are Fast" else if (reactionTime < 250) "You Are Average" else "You Are Slow"
                    message = "Reaction ${reactionTime}ms\n\nClick to Retry"
                    iconValue = if (reactionTime < 200 ) R.drawable.baseline_bolt_24 else if (reactionTime < 250 ) R.drawable.baseline_thumb_up_24 else R.drawable.baseline_thumb_down_alt_24

                    if (bestScore > reactionTime){
                        bestScore = reactionTime
                    }

                    break
                }
            }
        }
    }

    Scaffold (
        bottomBar = {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                if (bestScore < 100000L && !isStarted ){
                    Text(
                        text = "Best Score\n${bestScore}",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF),
                        modifier = Modifier
                            .padding(32.dp)
                    )
                }
            }
        },
        content = {
            Column (
                modifier = Modifier.fillMaxSize()
            ){
               Button(
                   modifier = Modifier
                       .fillMaxSize(),
                   shape = RectangleShape,
                   colors = ButtonDefaults.buttonColors(
                       containerColor = Color(backgroundColor)
                   ),
                   enabled = countdownTime == 0L,
                   onClick = {
                       isStarted = !isStarted
                   }
               ) {
                   Column (
                       horizontalAlignment = Alignment.CenterHorizontally
                   ){
                       Text(
                           text = heading,
                           textAlign = TextAlign.Center,
                           fontWeight = FontWeight.Bold,
                           fontSize = 32.sp,
                           color = Color(0xFFFFFFFF)
                       )

                       Spacer(
                           modifier = Modifier.height(24.dp)
                       )

                       Image(
                           painter = painterResource(iconValue),
                           contentDescription = "Icon",
                           contentScale = ContentScale.Fit,
                           modifier = Modifier
                               .size(42.dp)
                       )

                       Spacer(
                           modifier = Modifier.height(24.dp)
                       )

                       Text(
                           text = message,
                           textAlign = TextAlign.Center,
                           fontSize = 24.sp,
                           color = Color(0xFFFFFFFF)
                       )

                   }
               }
            }
        }
    )

}

@Preview
@Composable
fun ReactionTimeTestPreview () {
    ReactionTimeTest()
}