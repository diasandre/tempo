package kt.date.extensions

import kt.date.model.Configuration
import kt.date.model.enums.INPUT_TYPE.DATE
import kt.date.model.enums.INPUT_TYPE.DATE_TIME
import kt.date.model.enums.INPUT_TYPE.MILLIS
import kt.date.model.enums.INPUT_TYPE.SECONDS
import kt.date.model.enums.INPUT_TYPE.YEAR_MONTH
import kt.date.utils.ErrorMessages.FAILED_TO_CONVERT_FOR_PATTERN
import kt.date.utils.LOCAL_DATE_PATTERN
import kt.date.utils.YEAR_MONTH_PATTERN
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter

fun String.toLocalDate(configuration: Configuration<String>): LocalDate = runCatching {
    return when (configuration.pattern.type) {
        DATE, DATE_TIME -> LocalDate.parse(this, requireNotNull(configuration.pattern.formatter ?: LOCAL_DATE_PATTERN))
        YEAR_MONTH -> YearMonth.parse(
            this,
            requireNotNull(configuration.pattern.formatter ?: YEAR_MONTH_PATTERN)
        ).let { yearMonth -> LocalDate.of(yearMonth.year, yearMonth.month, 1) }

        SECONDS -> Instant.ofEpochSecond(this.toLong()).toLocalDate(configuration.timezone)
        MILLIS -> Instant.ofEpochMilli(this.toLong()).toLocalDate(configuration.timezone)
    }
}.onFailure { error(FAILED_TO_CONVERT_FOR_PATTERN) }.getOrThrow()

fun String.toLocalDateTime(configuration: Configuration<String>): LocalDateTime = runCatching {
    return LocalDateTime.parse(this, requireNotNull(configuration.pattern.formatter ?: LOCAL_DATE_PATTERN))
}.onFailure { error(FAILED_TO_CONVERT_FOR_PATTERN) }.getOrThrow()

fun String.toYearMonth(pattern: DateTimeFormatter?): YearMonth = YearMonth.parse(this, pattern)

fun String.toInstant(): Instant = Instant.parse(this)