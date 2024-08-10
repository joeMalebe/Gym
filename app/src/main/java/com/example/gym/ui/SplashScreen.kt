package com.example.gym.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gym.R
import com.example.gym.theme.GymTheme
import kotlinx.coroutines.time.delay
import java.time.Duration

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    alphaAnimation: Animatable<Float, AnimationVector1D> = remember {
        Animatable(0f)
    },
    navHostController: NavHostController = rememberNavController(),
    startApp: () -> Unit
) {

    LaunchedEffect(alphaAnimation) {
        alphaAnimation.animateTo(
            targetValue = 1f,
            animationSpec = androidx.compose.animation.core.tween(
                durationMillis = 1800, easing = FastOutLinearInEasing
            )
        )
        delay(Duration.ofMillis(500))
        startApp()
    }


    Scaffold {
        Box(Modifier.padding(it)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally, modifier = Modifier
                    .fillMaxSize()

            ) {
                Box(
                    modifier = Modifier
                        .alpha(alphaAnimation.value)
                        .size(240.dp)
                        .clip(CircleShape)


                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splash_image),
                        contentDescription = "gym",
                        contentScale = ContentScale.Crop
                    )
                }

            }

            CircularProgressIndicator(
                progress = { alphaAnimation.value },
                modifier = Modifier
                    .align(Center)
                    .size(240.dp),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    GymTheme {
        SplashScreen(startApp = {})
    }
}