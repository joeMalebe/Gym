@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.gym.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.gym.Exercise
import com.example.gym.GymActivity
import com.example.gym.R
import com.example.gym.ViewModel
import com.example.gym.theme.GymTheme

@Composable
fun SharedTransitionScope.WorkoutScreen(
    gymActivityId: Int,
    viewModel: ViewModel = ViewModel(),
    onBackClick: () -> Unit,
    animatedVisibilityScope: AnimatedContentScope
) {
    val gymActivity = viewModel.getUserGymActivities(activityId = gymActivityId)
    val exercises = viewModel.getExercises(gymActivityId)
    Scaffold { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues)
        ) {

            WorkoutScreenContent(
                    modifier = Modifier.padding(16.dp),
                    gymActivity = gymActivity,
                exercises = exercises,
                animatedVisibilityScope = animatedVisibilityScope
                )
            MenuItems(onBackClick = onBackClick)

        }
    }

}

@Composable
private fun MenuItems(onBackClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(onClick = { onBackClick() }) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
            )
        }

        IconButton(onClick = { /*TODO*/ }) {
            Image(
                painter = painterResource(id = R.drawable.fav_icon),
                contentDescription = "Back",
            )
        }
    }
}

@Composable
fun SharedTransitionScope.WorkoutScreenContent(
    gymActivity: GymActivity,
    exercises: List<Exercise>,
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedContentScope,
) {
    Column(Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f, false)
        ) {
            Image(
                painter = painterResource(id = gymActivity.image),
                contentDescription = gymActivity.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/${gymActivity.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(
                                durationMillis = 500,
                                easing = FastOutLinearInEasing
                            )
                        }
                    )
            )

            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colorScheme.background.copy(alpha = 0.06f),
                                MaterialTheme.colorScheme.background,


                                ),
                        )
                    )
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        Column(modifier = modifier.weight(0.6f), verticalArrangement = spacedBy(16.dp)) {
            GymActivityFacts(gymActivity)
            Text(text = gymActivity.description, style = MaterialTheme.typography.bodyMedium)
            Exercises(exercises = exercises, title = gymActivity.title)

        }
    }
}

@Composable
private fun Exercises(exercises: List<Exercise>, modifier: Modifier = Modifier, title: String) {
    Column(modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.exercises),
            style = MaterialTheme.typography.titleMedium
        )
        LazyColumn(verticalArrangement = spacedBy(16.dp)) {
            items(exercises) { exercise ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Row(Modifier.weight(1f), horizontalArrangement = spacedBy(16.dp)) {
                            AsyncImage(
                                model = exercise.image,
                                contentDescription = title,
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(MaterialTheme.shapes.medium),
                            )
                            Column(Modifier.weight(1f), verticalArrangement = spacedBy(4.dp)) {
                                Text(
                                    text = stringResource(
                                        id = R.string.lesson,
                                        exercise.lessonNumber
                                    ),
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = exercise.duration.toString(),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(1f, false)) {
                            Image(
                                painter = painterResource(id = R.drawable.play),
                                contentDescription = "Play",
                                contentScale = ContentScale.Crop, modifier = Modifier.size(36.dp)
                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
private fun GymActivityFacts(gymActivity: GymActivity, modifier: Modifier = Modifier) {
    Column(verticalArrangement = spacedBy(4.dp), modifier = modifier) {
        Text(
            text = gymActivity.level,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = gymActivity.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        ActivityFacts(
            duration = gymActivity.time,
            calorieBurns = gymActivity.calorieBurns,
            numberOfExercises = gymActivity.exercises
        )
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun WorkoutScreenPreview() {
    GymTheme {

        //WorkoutScreen(4, animatedVisibilityScope = this)
    }
}