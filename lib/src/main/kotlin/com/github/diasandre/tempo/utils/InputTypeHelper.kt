package com.github.diasandre.tempo.utils

import com.github.diasandre.tempo.model.enums.INPUT_TYPE
import com.github.diasandre.tempo.model.enums.INPUT_TYPE.*
import com.github.diasandre.tempo.utils.constants.ErrorMessages.NOT_ACCEPTABLE_INPUT_PATTERN

object InputTypeHelper {
    fun <VALUE> get(value: VALUE): INPUT_TYPE {
        if (value is String) {
            val isLocalDatePattern = "\\d+[-./]\\d+[-./]\\d+".toRegex().containsMatchIn(value)
            val isLocalDateTimePattern = "\\d+[-./]\\d+[-./]\\d+[T\\s]\\d{2}:\\d{2}:\\d+".toRegex().containsMatchIn(value)
            val isYearMonthPattern = "\\d{4}-\\d{2}".toRegex().containsMatchIn(value)
            val isEpochSecondPattern = "\\d{10}".toRegex().containsMatchIn(value)
            val isEpochMilliPattern = "\\d{13}".toRegex().containsMatchIn(value)

            return when {
                isLocalDateTimePattern -> DATE_TIME
                isLocalDatePattern -> DATE
                isYearMonthPattern -> YEAR_MONTH
                isEpochMilliPattern -> MILLIS
                isEpochSecondPattern -> SECONDS
                else -> error(NOT_ACCEPTABLE_INPUT_PATTERN)
            }
        }

        return DATE
    }
}