package kt.date.utils

import kt.date.model.Configuration
import kt.date.model.OutputUpdateConfig
import kt.date.model.enums.OPERATION_TYPE.MINUS
import kt.date.model.enums.OPERATION_TYPE.OVERRIDE
import kt.date.model.enums.OPERATION_TYPE.PLUS
import kt.date.model.enums.VALUE_TYPE.DAY
import kt.date.model.enums.VALUE_TYPE.MONTH
import kt.date.model.enums.VALUE_TYPE.YEAR
import kt.date.model.update.Period
import kt.date.model.update.UpdateOperation
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