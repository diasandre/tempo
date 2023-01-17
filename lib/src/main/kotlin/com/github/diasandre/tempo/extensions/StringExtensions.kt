package com.github.diasandre.tempo.extensions

import com.github.diasandre.tempo.model.Configuration
import com.github.diasandre.tempo.model.enums.INPUT_TYPE.DATE
import com.github.diasandre.tempo.model.enums.INPUT_TYPE.DATE_TIME
import com.github.diasandre.tempo.model.enums.INPUT_TYPE.MILLIS
import com.github.diasandre.tempo.model.enums.INPUT_TYPE.SECONDS
import com.github.diasandre.tempo.model.enums.INPUT_TYPE.YEAR_MONTH
import com.github.diasandre.tempo.utils.constants.ErrorMessages.FAILED_TO_CONVERT_FOR_PATTERN
import com.github.diasandre.tempo.utils.constants.LOCAL_DATE_PATTERN
import com.github.diasandre.tempo.utils.constants.YEAR_MONTH_PATTERN
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