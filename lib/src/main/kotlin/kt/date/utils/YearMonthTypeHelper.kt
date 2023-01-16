package kt.date.utils

import kt.date.extensions.toYearMonth
import kt.date.model.Configuration
import java.time.Instant
import java.time.LocalDate
import java.time.YearMonth

object YearMonthTypeHelper {
    fun parseFromString(
        value: String,
        configuration: Configuration<String>
    ): YearMonth = value.toYearMonth(configuration.pattern.formatter).handleUpdateIfNecessary(configuration)

    fun parseFromInstant(
        value: Instant,
        configuration: Configuration<Instant>
    ): YearMonth = value.toYearMonth(configuration.timezone).handleUpdateIfNecessary(configuration)

    fun parseFromLocalDate(
        value: LocalDate,
        configuration: Configuration<LocalDate>
    ): YearMonth = value.handleUpdateIfNecessary(configuration).toYearMonth()

}