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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gym.GymActivity
import com.example.gym.GymMetric
import com.example.gym.Profile
import com.example.gym.R
import com.example.gym.ViewModel
import com.example.gym.theme.GymTheme
import com.example.gym.theme.searchFieldColors
import com.example.gym.ui.navigation.Screen

@Composable
fun HomeScreen(
    viewModel: ViewModel = ViewModel(),
    navController: NavHostController = rememberNavController()
) {
    var search by remember {
        mutableStateOf("")
    }
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
        ) {
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
}

@Composable
fun HomeContent(
    user: Profile,
    search: String,
    modifier: Modifier = Modifier,
    onSearchTextChange: (String) -> Unit,
) {
    LazyColumn(
        modifier,
        verticalArrangement = spacedBy(16.dp)
    ) {
        item { Profile(user) }
        item { SearchBar(search, onSearchTextChange) }
        item { ExerciseMetrics(user.metrics) }
        item { LastSeenActivity(user.lastSeen) }
        item { OtherWorkouts(user.activities) }
    }
}

@Composable
private fun Profile(
    user: Profile,
    modifier: Modifier = Modifier,
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
                painter = painterResource(id = user.profilePic),
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
    Box(modifier = modifier.fillMaxSize()) {
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
    Column(modifier.fillMaxSize(), verticalArrangement = spacedBy(16.dp)) {
        Text(
            text = stringResource(id = R.string.monitoring),
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
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
    Box(modifier = modifier.fillMaxSize()) {
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

    Column(modifier.heightIn(min = 200.dp, max = 320.dp), verticalArrangement = spacedBy(16.dp)) {
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
                    Column(Modifier.padding(16.dp), verticalArrangement = spacedBy(8.dp)) {
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
                        Column(Modifier.weight(0.2f), verticalArrangement = spacedBy(8.dp)) {
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
private fun BottomNavigationBar(navController: NavHostController) {
    Column {
        HorizontalDivider()

        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.primary,
            tonalElevation = 8.dp
        ) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            Screen.entries.filter { it != Screen.Launcher }.forEach {
                val selected = currentRoute == it.route
                NavigationBarItem(selected = selected, onClick = { /*TODO*/ }, icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = stringResource(id = it.label),

                        modifier = Modifier.size(24.dp),
                    )
                }, label = {
                    NavLabelTextItem(text = stringResource(id = it.label))
                },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                        unselectedTextColor = MaterialTheme.colorScheme.surfaceDim,
                        indicatorColor = Color.Transparent
                    )
                )
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
private fun NavLabelTextItem(text: String, modifier: Modifier = Modifier) {

    Box(modifier = modifier) {
        Text(
            text = text,

            style = MaterialTheme.typography.bodySmall
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


