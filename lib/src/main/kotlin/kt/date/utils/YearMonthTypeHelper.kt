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
    ): YearMonth = value.toYearMonth(configuration.pattern.formatter)

    fun parseFromInstant(
        value: Instant,
        configuration: Configuration<Instant>
    ): YearMonth = value.toYearMonth(configuration.timezone)

    fun parseFromLocalDate(
        value: LocalDate,
        configuration: Configuration<LocalDate>
    ): YearMonth = value.toYearMonth()

}