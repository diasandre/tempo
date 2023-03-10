package com.github.diasandre.tempo.utils

import com.github.diasandre.tempo.extensions.toLocalDate
import com.github.diasandre.tempo.model.Configuration
import java.time.Instant
import java.time.LocalDate

object LocalDateTypeHelper {
    fun parseFromString(
        value: String,
        configuration: Configuration<String>
    ): LocalDate = value.toLocalDate(configuration).handleUpdateIfNecessary(configuration)

    fun parseFromInstant(
        value: Instant,
        configuration: Configuration<Instant>
    ): LocalDate = value.toLocalDate(configuration.timezone).handleUpdateIfNecessary(configuration)
}