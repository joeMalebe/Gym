package com.example.gym

import org.junit.Assert.assertEquals
import org.junit.Test

class ViewModelTest {

    private val viewModel = ViewModel()

    @Test
    fun getActivityDuration_should_return_89() {
        assertEquals(89, viewModel.getActivityDuration(1).inWholeMinutes)
    }
}