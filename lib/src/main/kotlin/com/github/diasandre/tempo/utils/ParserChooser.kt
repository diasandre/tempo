package com.github.diasandre.tempo.utils

import com.github.diasandre.tempo.model.Configuration
import com.github.diasandre.tempo.model.enums.OUTPUT_TYPE
import com.github.diasandre.tempo.utils.constants.ErrorMessages.OUTPUT_IS_EQUAL_TO_INPUT
import java.time.Instant
import java.time.LocalDate

object ParserChooser {
    fun chooseFromString(configuration: Configuration<String>): (String, Configuration<String>) -> (Any) = when (configuration.type) {
        OUTPUT_TYPE.LOCAL_DATE -> LocalDateTypeHelper::parseFromString
        OUTPUT_TYPE.YEAR_MONTH -> YearMonthTypeHelper::parseFromString
        OUTPUT_TYPE.INSTANT -> InstantTypeHelper::parseFromString
    }

    fun chooseFromLocalDate(configuration: Configuration<LocalDate>): (LocalDate, Configuration<LocalDate>) -> (Any) = when (configuration.type) {
        OUTPUT_TYPE.YEAR_MONTH -> YearMonthTypeHelper::parseFromLocalDate
        OUTPUT_TYPE.INSTANT -> InstantTypeHelper::parseFromLocalDate
        OUTPUT_TYPE.LOCAL_DATE -> error(OUTPUT_IS_EQUAL_TO_INPUT)
    }

    fun chooseFromInstant(configuration: Configuration<Instant>): (Instant, Configuration<Instant>) -> (Any) = when (configuration.type) {
        OUTPUT_TYPE.LOCAL_DATE -> LocalDateTypeHelper::parseFromInstant
        OUTPUT_TYPE.YEAR_MONTH -> YearMonthTypeHelper::parseFromInstant
        OUTPUT_TYPE.INSTANT -> error(OUTPUT_IS_EQUAL_TO_INPUT)
    }
}