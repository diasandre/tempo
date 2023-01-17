package com.github.diasandre.tempo.utils

import com.github.diasandre.tempo.model.Configuration
import com.github.diasandre.tempo.model.OutputUpdateConfig
import com.github.diasandre.tempo.model.enums.OPERATION_TYPE.MINUS
import com.github.diasandre.tempo.model.enums.OPERATION_TYPE.OVERRIDE
import com.github.diasandre.tempo.model.enums.OPERATION_TYPE.PLUS
import com.github.diasandre.tempo.model.enums.VALUE_TYPE.DAY
import com.github.diasandre.tempo.model.enums.VALUE_TYPE.MONTH
import com.github.diasandre.tempo.model.enums.VALUE_TYPE.YEAR
import com.github.diasandre.tempo.model.update.Period
import com.github.diasandre.tempo.model.update.UpdateOperation
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth

object UpdateConfigHelper {

    fun build(outputUpdateConfig: OutputUpdateConfig?): List<UpdateOperation> {
        val updates: List<Period> = listOfNotNull(outputUpdateConfig?.day, outputUpdateConfig?.month, outputUpdateConfig?.year)

        return updates.map { (value, operation) ->
            UpdateOperation(value.toLong(), operation.type, operation.operation)
        }
    }
}

fun YearMonth.handleUpdateIfNecessary(configuration: Configuration<*>): YearMonth {
    if (configuration.updates.isEmpty()) return this

    return configuration.updates
        .filter { it.type != DAY }
        .fold(this) { actual, operation ->
            when (operation.type) {
                MONTH -> actual.handleMonthOperation(operation)
                YEAR -> actual.handleYearOperation(operation)
                else -> error("")
            }
        }
}

fun LocalDateTime.handleUpdateIfNecessary(configuration: Configuration<*>): LocalDateTime {
    if (configuration.updates.isEmpty()) return this

    return configuration.updates.fold(this) { actual, operation ->
        when (operation.type) {
            DAY -> actual.handleDayOperation(operation)
            MONTH -> actual.handleMonthOperation(operation)
            YEAR -> actual.handleYearOperation(operation)
        }
    }
}

fun LocalDate.handleUpdateIfNecessary(configuration: Configuration<*>): LocalDate {
    if (configuration.updates.isEmpty()) return this

    return configuration.updates.fold(this) { actual, operation ->
        when (operation.type) {
            DAY -> actual.handleDayOperation(operation)
            MONTH -> actual.handleMonthOperation(operation)
            YEAR -> actual.handleYearOperation(operation)
        }
    }
}

fun LocalDate.handleDayOperation(operation: UpdateOperation): LocalDate = when (operation.operation) {
    PLUS -> plusDays(operation.value)
    MINUS -> minusDays(operation.value)
    OVERRIDE -> LocalDate.of(year, monthValue, operation.value.toInt())
}

fun LocalDateTime.handleDayOperation(operation: UpdateOperation): LocalDateTime = when (operation.operation) {
    PLUS -> plusDays(operation.value)
    MINUS -> minusDays(operation.value)
    OVERRIDE -> LocalDateTime.of(year, monthValue, operation.value.toInt(), hour, minute, second)
}

fun LocalDate.handleMonthOperation(operation: UpdateOperation): LocalDate = when (operation.operation) {
    PLUS -> plusMonths(operation.value)
    MINUS -> minusMonths(operation.value)
    OVERRIDE -> LocalDate.of(year, operation.value.toInt(), dayOfMonth)
}

fun LocalDateTime.handleMonthOperation(operation: UpdateOperation): LocalDateTime = when (operation.operation) {
    PLUS -> plusMonths(operation.value)
    MINUS -> minusMonths(operation.value)
    OVERRIDE -> LocalDateTime.of(year, operation.value.toInt(), dayOfMonth, hour, minute, second)
}

fun YearMonth.handleMonthOperation(operation: UpdateOperation): YearMonth = when (operation.operation) {
    PLUS -> plusMonths(operation.value)
    MINUS -> minusMonths(operation.value)
    OVERRIDE -> YearMonth.of(year, operation.value.toInt())
}

fun LocalDate.handleYearOperation(operation: UpdateOperation): LocalDate = when (operation.operation) {
    PLUS -> plusYears(operation.value)
    MINUS -> minusYears(operation.value)
    OVERRIDE -> LocalDate.of(operation.value.toInt(), monthValue, dayOfMonth)
}

fun LocalDateTime.handleYearOperation(operation: UpdateOperation): LocalDateTime = when (operation.operation) {
    PLUS -> plusYears(operation.value)
    MINUS -> minusYears(operation.value)
    OVERRIDE -> LocalDateTime.of(dayOfMonth, monthValue, operation.value.toInt(), hour, minute, second)
}

fun YearMonth.handleYearOperation(operation: UpdateOperation): YearMonth = when (operation.operation) {
    PLUS -> plusYears(operation.value)
    MINUS -> minusYears(operation.value)
    OVERRIDE -> YearMonth.of(operation.value.toInt(), monthValue)
}