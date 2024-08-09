package com.example.gym.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gym.GymActivity
import com.example.gym.GymMetric
import com.example.gym.Profile
import com.example.gym.R
import com.example.gym.ViewModel
import com.example.gym.theme.GymTheme
import com.example.gym.theme.searchFieldColors

@Composable
fun HomeScreen(viewModel: ViewModel = ViewModel()) {
    var search by remember {
        mutableStateOf("")
    }
    Scaffold { paddingValues ->
        HomeContent(
            viewModel.getUser(),
            search = search,
            Modifier
                .padding(paddingValues)
                .padding(16.dp),
            onSearchTextChange = { search = it },
        )
    }
}

@Composable
fun HomeContent(
    user: Profile,
    search: String,
    modifier: Modifier = Modifier,
    onSearchTextChange: (String) -> Unit,
) {
    Column(modifier, verticalArrangement = spacedBy(16.dp)) {
        Profile(Modifier.weight(0.6f, false), user)
        SearchBar(search, onSearchTextChange, Modifier.weight(0.4f, false))
        ExerciseMetrics(user.metrics, Modifier.weight(0.8f, false))
        LastSeenActivity(user.activities[3], Modifier.weight(1f))
        OtherWorkouts(user.activities, Modifier.weight(1.4f))
    }
}

@Composable
private fun Profile(
    modifier: Modifier,
    user: Profile
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.welcome_back),
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = user.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Box(modifier = Modifier.size(56.dp)) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = CircleShape)
            )
        }
    }
}

@Composable
private fun SearchBar(
    search: String,
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        OutlinedTextField(
            value = search,
            onValueChange = onSearchTextChange,
            placeholder = {
                Text(text = stringResource(id = R.string.search))
            },
            shape = CircleShape,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search",
                )
            },
            colors = searchFieldColors(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ExerciseMetrics(metrics: List<GymMetric>, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth(), verticalArrangement = spacedBy(16.dp)) {
        Text(
            text = stringResource(id = R.string.monitoring),
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(metrics) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = spacedBy(8.dp)
                ) {
                    Image(painter = painterResource(id = it.image), contentDescription = it.title)
                    Text(text = it.value, style = MaterialTheme.typography.bodyMedium)
                    Text(text = it.title, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun LastSeenActivity(gymActivity: GymActivity, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = gymActivity.imageOverlay),
            contentDescription = gymActivity.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            verticalArrangement = spacedBy(8.dp),
            modifier = Modifier
                .padding(16.dp)
                .widthIn(max = 160.dp)
        ) {
            Text(
                text = stringResource(id = R.string.current_exercise, gymActivity.title),
                style =
                MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.None
            )
            Text(text = gymActivity.time.toString())
        }
    }
}

@Composable
fun OtherWorkouts(workouts: List<GymActivity>, modifier: Modifier = Modifier) {

    Column(modifier.fillMaxSize(), verticalArrangement = spacedBy(16.dp)) {
        Text(
            text = stringResource(id = R.string.other_workouts),
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow(horizontalArrangement = spacedBy(16.dp)) {
            items(workouts) {
                Card(
                    onClick = { /*TODO*/ },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                    modifier = Modifier.width(280.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Box(
                            Modifier
                                .weight(0.8f)
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = it.image),
                                contentDescription = it.title,
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.medium)
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Column(Modifier.weight(0.2f)) {
                            Text(
                                text = it.title,
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Row(horizontalArrangement = spacedBy(8.dp)) {

                                TextItem(
                                    text = it.time.toString(),
                                )

                                TextItem(
                                    text = it.calorieBurns,
                                )

                                TextItem(
                                    text = stringResource(
                                        id = R.string.current_exercise, it.exercises
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TextItem(text: String) {
    Row(horizontalArrangement = spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.surfaceDim,
            style = MaterialTheme.typography.titleSmall
        )

        Box(
            modifier = Modifier
                .size(6.dp)
                .background(MaterialTheme.colorScheme.primary, CircleShape)
        )
    }
}

@Composable
@Preview(showBackground = true, heightDp = 1000)
fun HomeScreenPreview() {
    GymTheme {
        HomeScreen()
    }
}


