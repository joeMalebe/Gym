package com.example.gym

import androidx.annotation.DrawableRes
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

const val RUNNING = 1
const val STRENGTH = 2
const val YOGA = 3

class ViewModel {
    fun getUser() = Profile(
        "John Doe", R.drawable.profile, metrics =
        listOf(
            GymMetric("Daily calories", "520", R.drawable.monitor1),
            GymMetric("Night runs", "25 km", R.drawable.monitor2),
            GymMetric("Total workouts", "2 w 3 days", R.drawable.monitor3),
        ), activities = listOf(
            GymActivity(
                RUNNING,
                "Running",
                getActivityDuration(RUNNING),
                "4 laps",
                "300 kcal",
                R.drawable.pic_1
            ),
            GymActivity(
                STRENGTH,
                "Strength",
                getActivityDuration(STRENGTH),
                "10 km",
                "500 kcal",
                R.drawable.pic_2
            ),
            GymActivity(
                YOGA, "Yoga", getActivityDuration(YOGA), "60 poses", "200 kcal", R.drawable.pic_3
            )
        )
    )


    fun getUserGymActivities() = getUser().activities

    fun getActivityDuration(activityId: Int) = getExerciseDuration(activityId)

    fun getExercises(activityId: Int) = exercise()[activityId] ?: emptyList()

    private fun getExerciseDuration(activityId: Int) =
        exercise()[activityId]?.fold(0.minutes) { sum, exercise ->
            sum + exercise.duration
        } ?: 0.minutes

    private fun exercise() =
        mapOf(
            1 to listOf(
                Exercise(
                    1,
                    30.minutes,
                    R.drawable.pic_1_1
                ), Exercise(
                    2,
                    37.minutes,
                    R.drawable.pic_1_2
                ), Exercise(
                    3,
                    22.minutes,
                    R.drawable.pic_1_3
                )
            ),
            2 to listOf(
                Exercise(
                    1,
                    12.minutes,
                    R.drawable.pic_2_1
                ), Exercise(
                    2,
                    20.minutes,
                    R.drawable.pic_2_2
                ), Exercise(
                    3,
                    15.minutes,
                    R.drawable.pic_2_3
                ),
                Exercise(
                    4,
                    25.minutes,
                    R.drawable.pic_2_4
                )
            ),
            3 to listOf(
                Exercise(
                    1,
                    45.minutes,
                    R.drawable.pic_3_1
                ), Exercise(
                    2,
                    31.minutes,
                    R.drawable.pic_3_2
                ), Exercise(
                    3,
                    43.minutes,
                    R.drawable.pic_3_3
                ),
                Exercise(
                    4,
                    40.minutes,
                    R.drawable.pic_3_4
                )
            )
        )

}

data class Profile(
    val name: String,
    @DrawableRes val profilePic: Int,
    val metrics: List<GymMetric>,
    val activities: List<GymActivity>
)

data class GymMetric(val title: String, val value: String, @DrawableRes val image: Int)

data class GymActivity(
    val id: Int,
    val title: String,
    val time: Duration,
    val repetition: String,
    val calorieBurns: String,
    @DrawableRes val image: Int
)

data class Exercise(
    val lessonNumber: Int,
    val duration: Duration,
    @DrawableRes val image: Int,
)

