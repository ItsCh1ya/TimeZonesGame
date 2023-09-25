package ru.chiya.timezonesgame.presentation.screen.remember

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.chiya.domain.usecase.GetRandomCitiesUseCase
import ru.chiya.timezonesgame.di.GlobalCities
import javax.inject.Inject

@HiltViewModel
class RememberViewModel @Inject constructor(
    private val getRandomCitiesUseCase: GetRandomCitiesUseCase,
    private val globalCities: GlobalCities
) : ViewModel() {
    val cities = globalCities.cities

    init {
        getRandomCities(6)
    }

    private fun getRandomCities(amount: Int) {
        globalCities.update(getRandomCitiesUseCase(amount))
    }
}