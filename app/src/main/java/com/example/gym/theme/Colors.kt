package com.example.gym.theme

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Transition
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

val background = Color(0xFF1F2434)
val lightBackground = Color(0xFF2f374f)
val primary = Color(0xFFFF6F61)
val onBackground = Color(0xFFE0E0E0)
val onBackgroundSecondary = Color(0xC3E0E0E0)


@Composable
fun searchFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
    focusedLabelColor = MaterialTheme.colorScheme.onBackground,
    focusedTextColor = MaterialTheme.colorScheme.onBackground,
    focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
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