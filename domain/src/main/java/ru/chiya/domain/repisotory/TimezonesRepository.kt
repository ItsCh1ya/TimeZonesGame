package ru.chiya.domain.repisotory

import ru.chiya.domain.model.Timezone

interface TimezonesRepository {
    fun getTimezones(amount: Int): List<Timezone>
}