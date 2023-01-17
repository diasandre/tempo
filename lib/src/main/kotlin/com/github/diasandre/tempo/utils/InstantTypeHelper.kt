package com.github.diasandre.tempo.utils

import com.github.diasandre.tempo.extensions.toInstant
import com.github.diasandre.tempo.extensions.toLocalDate
import com.github.diasandre.tempo.extensions.toLocalDateTime
import com.github.diasandre.tempo.model.Configuration
import com.github.diasandre.tempo.model.enums.INPUT_TYPE
import java.time.Instant
import java.time.LocalDate

object InstantTypeHelper {

    fun parseFromString(
        value: String,
        configuration: Configuration<String>
    ): Instant = when (configuration.pattern.type) {
        INPUT_TYPE.DATE_TIME -> value.toLocalDateTime(configuration).handleUpdateIfNecessary(configuration).toInstant(configuration.timezone.zoneOffset)
        else -> value.toLocalDate(configuration).handleUpdateIfNecessary(configuration).toInstant(configuration.timezone.zoneOffset)

    }

    fun parseFromLocalDate(
        value: LocalDate,
        configuration: Configuration<LocalDate>
    ): Instant = value.handleUpdateIfNecessary(configuration).toInstant(configuration.timezone.zoneOffset)
}