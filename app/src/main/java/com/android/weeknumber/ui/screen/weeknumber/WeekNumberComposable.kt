package com.android.weeknumber.ui.screen.weeknumber

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.weeknumber.R
import com.android.weeknumber.Utils
import com.android.weeknumber.ui.theme.Ember
import com.android.weeknumber.ui.theme.SecondaryEmber
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun WeekNumberComposable() {

    var animatedProgress by remember { mutableFloatStateOf(0f) }
    val targetProgress = remember { WeekNumberUtils().getCurrentWeekNumber() / 52f }

    // Animate the progress
    LaunchedEffect(Unit) {
        for (progress in 0..(targetProgress * 100).toInt()) {
            animatedProgress = progress / 100f
            delay(10)
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Ember.copy(alpha = 0.9f))
    ) {
        // outer card
        Column(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.4f)
        )
        {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Week Number",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = montserratFont,
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // get the current week number
                val weekNumberUtils = WeekNumberUtils()
                val weekNumber = weekNumberUtils.getCurrentWeekNumber()
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .background(color = SecondaryEmber)
                        .clickable {

                        },
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.size(200.dp)) {
                        drawArc(
                            color = Color.DarkGray,
                            startAngle = -90f,
                            sweepAngle = animatedProgress * 360,
                            useCenter = false,
                            style = Stroke(width = 15.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }
                    Text(
                        text = "$weekNumber",
                        fontSize = 65.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = montserratFont,
                        color = Color.White,
                    )
                }
            }

        }
        // 📌 Bottom Text
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // Align text at the bottom
                .padding(bottom = 50.dp), // Add some spacing
            contentAlignment = Alignment.Center
        ) {
            val utils = Utils()
            val motivation = utils.giveRandomMotivation()
            Text(
                text = motivation,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = montserratFont,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            )
        }


    }


}

val montserratFont = FontFamily(Font(R.font.montserrat, FontWeight.Normal))

@Preview(showSystemUi = true)
@Composable
fun WeekNumber_Preview() {
    WeekNumberComposable()
}