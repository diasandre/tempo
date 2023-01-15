package kt.date.utils

import kt.date.model.enums.INPUT_TYPE

object InputTypeHelper {
    fun <VALUE> get(value: VALUE): INPUT_TYPE {
        if (value is String) {
            val isLocalDatePattern = "\\d{4}-\\d{2}-\\d{2}".toRegex().containsMatchIn(value)
            val isYearMonthPattern = "\\d{4}-\\d{2}".toRegex().containsMatchIn(value)
            val isEpochSecondPattern = "\\d{10}".toRegex().containsMatchIn(value)
            val isEpochMilliPattern = "\\d{13}".toRegex().containsMatchIn(value)

            return when {
                isLocalDatePattern -> INPUT_TYPE.DATE
                isYearMonthPattern -> INPUT_TYPE.YEAR_MONTH
                isEpochMilliPattern -> INPUT_TYPE.MILLIS
                isEpochSecondPattern -> INPUT_TYPE.SECONDS
                else -> error("input is not following the selected pattern")
            }
        }

        return INPUT_TYPE.DATE
    }
}