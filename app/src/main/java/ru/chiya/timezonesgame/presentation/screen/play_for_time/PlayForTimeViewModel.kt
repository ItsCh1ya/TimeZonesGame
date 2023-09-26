package ru.chiya.timezonesgame.presentation.screen.play_for_time

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.chiya.timezonesgame.di.GlobalCities
import javax.inject.Inject

@HiltViewModel
class PlayForTimeViewModel @Inject constructor(
    globalCities: GlobalCities
) : ViewModel() {
    val cities = globalCities.cities

    fun answer(selected: Selected): Boolean {
        val selectedTimezone = selected.toTimezone()
        return selectedTimezone in cities
    }
}