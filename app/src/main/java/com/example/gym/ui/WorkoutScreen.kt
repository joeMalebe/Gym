package com.example.gym.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gym.GymActivity
import com.example.gym.R
import com.example.gym.ViewModel
import com.example.gym.theme.GymTheme

@Composable
fun WorkoutScreen(gymActivityId: Int, viewModel: ViewModel = ViewModel()) {
    val gymActivity = viewModel.getUserGymActivities().find { it.id == gymActivityId }
    Scaffold { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            when {
                gymActivity != null -> WorkoutScreenContent(
                    modifier = Modifier.padding(16.dp),
                    gymActivity = gymActivity
                )

                else -> Text("Gym Activity not found")
            }
            /*IconButton(onClick = { *//*TODO*//* }, colors = IconButtonDefaults.outlinedIconButtonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onSurface)) {

                Icon(painter = painterResource(id = R.drawable.back) , contentDescription = "Back",)
            }*/

        }
    }

}

@Composable
fun WorkoutScreenContent(gymActivity: GymActivity, modifier: Modifier = Modifier) {
    Column {
        Text(text = gymActivity.title)
    }
}


@Composable
@Preview(showBackground = true)
fun WorkoutScreenPreview() {
    GymTheme {

        WorkoutScreen(1)
    }
}