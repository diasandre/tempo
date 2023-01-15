package kt.date.utils

import kt.date.model.Configuration
import kt.date.model.enums.OUTPUT_TYPE
import java.time.Instant
import java.time.LocalDate

object ParserChooser {
    fun chooseFromString(configuration: Configuration<String>): (String, Configuration<String>) -> (Any) = when (configuration.type) {
        OUTPUT_TYPE.LOCAL_DATE -> LocalDateTypeHelper::parseFromString
        OUTPUT_TYPE.YEAR_MONTH -> YearMonthTypeHelper::parseFromString
        OUTPUT_TYPE.INSTANT -> InstantTypeHelper::parseFromString
        else -> error("")
    }

    fun chooseFromLocalDate(configuration: Configuration<LocalDate>): (LocalDate, Configuration<LocalDate>) -> (Any) = when (configuration.type) {
        OUTPUT_TYPE.YEAR_MONTH -> YearMonthTypeHelper::parseFromLocalDate
        OUTPUT_TYPE.INSTANT -> InstantTypeHelper::parseFromLocalDate
        else -> error("")
    }

    fun chooseFromInstant(configuration: Configuration<Instant>): (Instant, Configuration<Instant>) -> (Any) = when (configuration.type) {
        OUTPUT_TYPE.LOCAL_DATE -> LocalDateTypeHelper::parseFromInstant
        OUTPUT_TYPE.YEAR_MONTH -> YearMonthTypeHelper::parseFromInstant
        else -> error("")
    }
}