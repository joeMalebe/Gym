package com.example.gym

import kotlin.time.Duration.Companion.minutes

const val RUNNING = 1
const val STRENGTH = 2
const val YOGA = 3
const val CORE = 4

class ViewModel {
    fun getUser() = Profile(
        "Zoe Dube", R.drawable.profile_2, metrics =
        listOf(
            GymMetric("Daily calories", "520", R.drawable.monitor1),
            GymMetric("Night runs", "25 km", R.drawable.monitor2),
            GymMetric("Total workouts", "2 w 3 days", R.drawable.monitor3),
        ),
        lastSeen = gymActivities().last(),
        activities = gymActivities()
    )

    private fun gymActivities() = listOf(
        GymActivity(
            RUNNING,
            "Running",
            getActivityDuration(RUNNING),
            getNumberOfExercises(
                RUNNING
            ),
            "300 kcal",
            R.drawable.pic_1,
            R.drawable.pic_1_ov,
            "Intermediate",
            "Boost endurance in ${getActivityDuration(RUNNING)} of steady-paced running. Clear your mind, strengthen your body, and feel the freedom."
        ),
        GymActivity(
            STRENGTH,
            "Strength",
            getActivityDuration(STRENGTH),
            getNumberOfExercises(
                STRENGTH
            ),
            "500 kcal",
            R.drawable.pic_2,
            R.drawable.pic_2_ov,
            "Advanced",
            "Challenge your limits with ${getActivityDuration(STRENGTH)}of intense strength training. Build muscle, burn fat, and elevate your performance."
        ),
        GymActivity(
            YOGA,
            "Yoga",
            getActivityDuration(YOGA),
            getNumberOfExercises(
                YOGA
            ),
            "200 kcal",
            R.drawable.pic_3,
            R.drawable.pic_3_ov,
            "Novice",
            "Relax and stretch with ${getActivityDuration(YOGA)} of gentle yoga. Perfect for beginners to find balance and inner peace."
        ),
        GymActivity(
            CORE,
            "Core",
            getActivityDuration(CORE),
            getNumberOfExercises(
                CORE
            ),
            "150 kcal",
            R.drawable.pic_4,
            R.drawable.pic_4_ov,
            "Intermediate",
            "Strengthen your core in just ${getActivityDuration(CORE)}. Build stability and enhance every movement with this focused, intermediate workout."
        )
    )


    fun getNumberOfExercises(
        activityId: Int
    ) = getExercises(activityId).count()

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
            ),
            4 to listOf(
                Exercise(
                    1,
                    20.minutes,
                    R.drawable.pic_4_1
                ),
                Exercise(
                    2,
                    15.minutes,
                    R.drawable.pic_4_2
                ),
                Exercise(
                    3,
                    30.minutes,
                    R.drawable.pic_4_3
                ),
            )
        )

}

