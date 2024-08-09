package com.example.gym

import androidx.annotation.DrawableRes
import kotlin.time.Duration

data class Profile(
    val name: String,
    @DrawableRes val profilePic: Int,
    val metrics: List<GymMetric>,
    val activities: List<GymActivity>,
    val lastSeen: GymActivity
)

data class GymMetric(val title: String, val value: String, @DrawableRes val image: Int)

data class GymActivity(
    val id: Int,
    val title: String,
    val time: Duration,
    val exercises: Int,
    val calorieBurns: String,
    @DrawableRes val image: Int,
    @DrawableRes val imageOverlay: Int,
    val level: String,
    val description: String
    )

data class Exercise(
    val lessonNumber: Int,
    val duration: Duration,
    @DrawableRes val image: Int,
)

