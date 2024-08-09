package com.example.gym.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gym.Profile
import com.example.gym.R
import com.example.gym.ViewModel
import com.example.gym.theme.GymTheme

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
    Column(modifier) {
        Profile(Modifier.weight(1f), user)
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
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.surfaceDim,
                focusedPlaceholderColor = MaterialTheme.colorScheme.surfaceDim,
                unfocusedLabelColor = MaterialTheme.colorScheme.surfaceDim,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceDim,
                unfocusedTextColor = MaterialTheme.colorScheme.surfaceDim,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.surfaceDim,
                focusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
                cursorColor = MaterialTheme.colorScheme.onBackground,
                selectionColors = TextSelectionColors(
                    handleColor = MaterialTheme.colorScheme.onBackground,
                    backgroundColor = MaterialTheme.colorScheme.background
                )
            )
        )
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
            Text(text = stringResource(id = R.string.welcome_back))
            Text(
                text = user.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Box(modifier = Modifier.size(48.dp)) {
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
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
    GymTheme {
        HomeScreen()
    }
}


