package kt.date.utils

import kt.date.extensions.toInstant
import kt.date.extensions.toLocalDate
import kt.date.model.Configuration
import java.time.Instant
import java.time.LocalDate

object InstantTypeHelper {

    fun parseFromString(
        value: String,
        configuration: Configuration<String>
    ): Instant = value.toLocalDate(configuration).toInstant(configuration.timezone.zoneOffset)

    fun parseFromLocalDate(
        value: LocalDate,
        configuration: Configuration<LocalDate>
    ): Instant = value.toInstant(configuration.timezone.zoneOffset)
}