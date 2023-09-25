package ru.chiya.data.repository

import ru.chiya.data.fakedb.CityTimezones
import ru.chiya.domain.model.Timezone
import ru.chiya.domain.repisotory.TimezonesRepository

class TimezonesRepositoryImpl : TimezonesRepository {
    override fun getTimezones(amount: Int): List<Timezone> {
        return CityTimezones.shuffled().take(amount)
    }
}