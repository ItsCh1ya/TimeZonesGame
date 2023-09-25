package ru.chiya.domain.usecase

import ru.chiya.domain.model.Timezone
import ru.chiya.domain.repisotory.TimezonesRepository
import javax.inject.Inject

class GetRandomCities @Inject constructor (
    private val repository: TimezonesRepository
) {
    operator fun invoke(amount: Int): List<Timezone> {
        return repository.getTimezones(amount)
    }
}