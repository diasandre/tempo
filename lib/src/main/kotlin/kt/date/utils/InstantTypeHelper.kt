package kt.date.utils

import kt.date.extensions.toInstant
import kt.date.extensions.toLocalDate
import kt.date.extensions.toLocalDateTime
import kt.date.model.Configuration
import kt.date.model.enums.INPUT_TYPE
import java.time.Instant
import java.time.LocalDate

object InstantTypeHelper {

    fun parseFromString(
        value: String,
        configuration: Configuration<String>
    ): Instant = when (configuration.pattern.type) {
        INPUT_TYPE.DATE_TIME -> value.toLocalDateTime(configuration).toInstant(configuration.timezone.zoneOffset)
        else -> value.toLocalDate(configuration).toInstant(configuration.timezone.zoneOffset)

    }

    fun parseFromLocalDate(
        value: LocalDate,
        configuration: Configuration<LocalDate>
    ): Instant = value.toInstant(configuration.timezone.zoneOffset)
}