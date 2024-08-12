@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.gym.constants

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Transition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class DefaultAnimationVisibilityScope : AnimatedVisibilityScope {
    override val transition: Transition<EnterExitState>
        get() = throw NotImplementedError()
}


class DefaultTransitionScope : SharedTransitionScope {
    override val isTransitionActive: Boolean
        get() = throw NotImplementedError()

    @ExperimentalComposeUiApi
    override val Placeable.PlacementScope.lookaheadScopeCoordinates: LayoutCoordinates
        get() = throw NotImplementedError()

    override fun OverlayClip(clipShape: Shape): SharedTransitionScope.OverlayClip {
        throw NotImplementedError()
    }

    @Composable
    override fun rememberSharedContentState(key: Any): SharedTransitionScope.SharedContentState {

        return TODO("Provide the return value")
    }

    @SuppressLint("ModifierFactoryUnreferencedReceiver")
    override fun Modifier.renderInSharedTransitionScopeOverlay(
        renderInOverlay: () -> Boolean,
        zIndexInOverlay: Float,
        clipInOverlayDuringTransition: (LayoutDirection, Density) -> Path?
    ): Modifier {
        throw NotImplementedError()
    }

    @SuppressLint("ModifierFactoryUnreferencedReceiver")
    override fun Modifier.sharedBounds(
        sharedContentState: SharedTransitionScope.SharedContentState,
        animatedVisibilityScope: AnimatedVisibilityScope,
        enter: EnterTransition,
        exit: ExitTransition,
        boundsTransform: BoundsTransform,
        resizeMode: SharedTransitionScope.ResizeMode,
        placeHolderSize: SharedTransitionScope.PlaceHolderSize,
        renderInOverlayDuringTransition: Boolean,
        zIndexInOverlay: Float,
        clipInOverlayDuringTransition: SharedTransitionScope.OverlayClip
    ): Modifier {
        throw NotImplementedError()
    }

    @SuppressLint("ModifierFactoryUnreferencedReceiver")
    override fun Modifier.sharedElement(
        state: SharedTransitionScope.SharedContentState,
        animatedVisibilityScope: AnimatedVisibilityScope,
        boundsTransform: BoundsTransform,
        placeHolderSize: SharedTransitionScope.PlaceHolderSize,
        renderInOverlayDuringTransition: Boolean,
        zIndexInOverlay: Float,
        clipInOverlayDuringTransition: SharedTransitionScope.OverlayClip
    ): Modifier {
        throw NotImplementedError()
    }

    @SuppressLint("ModifierFactoryUnreferencedReceiver")
    override fun Modifier.sharedElementWithCallerManagedVisibility(
        sharedContentState: SharedTransitionScope.SharedContentState,
        visible: Boolean,
        boundsTransform: BoundsTransform,
        placeHolderSize: SharedTransitionScope.PlaceHolderSize,
        renderInOverlayDuringTransition: Boolean,
        zIndexInOverlay: Float,
        clipInOverlayDuringTransition: SharedTransitionScope.OverlayClip
    ): Modifier {
        throw NotImplementedError()
    }

    @SuppressLint("ModifierFactoryUnreferencedReceiver")
    override fun Modifier.skipToLookaheadSize(): Modifier {
        throw NotImplementedError()
    }

    @ExperimentalComposeUiApi
    override fun LayoutCoordinates.toLookaheadCoordinates(): LayoutCoordinates {
        throw NotImplementedError()
    }
}

val FakeTransitionScopePair = Pair(
    DefaultAnimationVisibilityScope(),
    DefaultTransitionScope()
)

@OptIn(ExperimentalSharedTransitionApi::class)
val AnimationScope =
    compositionLocalOf<Pair<AnimatedVisibilityScope, SharedTransitionScope>> {
        FakeTransitionScopePair
    }
