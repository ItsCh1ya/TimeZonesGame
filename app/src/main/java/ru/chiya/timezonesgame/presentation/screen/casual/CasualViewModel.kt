package ru.chiya.timezonesgame.presentation.screen.casual

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.chiya.domain.model.Timezone
import ru.chiya.timezonesgame.di.GlobalCities
import javax.inject.Inject

@HiltViewModel
class CasualViewModel @Inject constructor(
    private val globalCities: GlobalCities
) : ViewModel() {
    private val cities = globalCities.cities.toMutableList()
    var questionsLeft = cities.size
    var currentCity: Timezone = cities.random()

    fun answer(inputCity: String): Boolean {
        val isCorrect = inputCity == currentCity.city
        updateCurrentCity()
        return isCorrect
    }

    private fun updateCurrentCity() {
        cities.remove(currentCity)
        currentCity = cities.random()
        questionsLeft--
    }
}