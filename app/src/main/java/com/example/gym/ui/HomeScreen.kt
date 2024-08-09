package com.example.gym.ui

import android.health.connect.datatypes.ActiveCaloriesBurnedRecord
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gym.R
import com.example.gym.theme.GymTheme

@Composable
fun HomeScreen() {
    Scaffold {
        HomeContent(Modifier.padding(it))
    }

}

@Composable
fun HomeContent(modifier: Modifier) {
    Text(text = stringResource(id = R.string.welcome_back))

}

@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
    GymTheme {
        HomeScreen()
    }
}


