package ru.chiya.timezonesgame.di

import ru.chiya.domain.model.Timezone
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalCities @Inject constructor() {
    private val _cities = mutableListOf<Timezone>()
    val cities = _cities
    fun update(newList: List<Timezone>) {
        with(_cities) {
            clear()
            addAll(newList)
        }
    }
}