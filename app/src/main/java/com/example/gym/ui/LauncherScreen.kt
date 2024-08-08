package com.example.gym.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gym.R
import com.example.gym.theme.GymTheme
import com.example.gym.theme.onBackgroundSecondary

@Composable
fun LauncherScreen() {

    Scaffold {
            Box(Modifier.padding(it)) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally, modifier = Modifier
                        .fillMaxSize().padding(vertical = 16.dp)

                ) {
                    Box(
                        modifier = Modifier
                            .weight(10f)
                            .padding(top = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.launcher_pic),
                            contentDescription = "gym",
                            contentScale = ContentScale.Inside
                        )
                    }
                    Footer(
                        Modifier
                            .weight(6f)
                            .padding(16.dp)
                    )
                }
                Text(
                    text = stringResource(id = R.string.welcome_to_gym),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Center)
                        .widthIn(max = 220.dp)
                        .padding(top = 48.dp)
                )
            }

    }

}

@Composable
private fun Footer(modifier: Modifier) {
    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        //Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            text = stringResource(id = R.string.disclamer),
            color = onBackgroundSecondary,
            textAlign = TextAlign.Center
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth().height(48.dp)) {
                Text(text = stringResource(id = R.string.lets_get_started), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            Row(verticalAlignment = Alignment.Bottom) {

                Text(text = stringResource(id = R.string.already_have_an_account))
                Text(
                    text = stringResource(id = R.string.signin),
                    modifier = Modifier.padding(start = 4.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LauncherScreenPreview() {
    GymTheme {
        LauncherScreen()
    }
}