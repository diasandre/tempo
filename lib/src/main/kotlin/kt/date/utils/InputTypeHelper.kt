package kt.date.utils

import kt.date.model.enums.INPUT_TYPE
import kt.date.utils.ErrorMessages.NOT_ACCEPTABLE_INPUT_PATTERN

object InputTypeHelper {
    fun <VALUE> get(value: VALUE): INPUT_TYPE {
        if (value is String) {
            val isLocalDatePattern = "\\d+[-./]\\d+[-./]\\d+".toRegex().containsMatchIn(value)
            val isLocalDateTimePattern = "\\d+[-./]\\d+[-./]\\d+[T\\s]\\d{2}:\\d{2}:\\d+".toRegex().containsMatchIn(value)
            val isYearMonthPattern = "\\d{4}-\\d{2}".toRegex().containsMatchIn(value)
            val isEpochSecondPattern = "\\d{10}".toRegex().containsMatchIn(value)
            val isEpochMilliPattern = "\\d{13}".toRegex().containsMatchIn(value)

            return when {
                isLocalDateTimePattern -> INPUT_TYPE.DATE_TIME
                isLocalDatePattern -> INPUT_TYPE.DATE
                isYearMonthPattern -> INPUT_TYPE.YEAR_MONTH
                isEpochMilliPattern -> INPUT_TYPE.MILLIS
                isEpochSecondPattern -> INPUT_TYPE.SECONDS
                else -> error(NOT_ACCEPTABLE_INPUT_PATTERN)
            }
        }

        return INPUT_TYPE.DATE
    }
}