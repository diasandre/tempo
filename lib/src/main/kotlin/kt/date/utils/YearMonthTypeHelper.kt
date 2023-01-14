package kt.date.utils

import kt.date.extensions.toYearMonth
import kt.date.model.InstantOutputConfig
import kt.date.model.StringInputConfig
import kt.date.model.StringOutputConfig
import java.time.Instant
import java.time.YearMonth

object YearMonthTypeHelper {
    fun parseFromString(value: String, inputConfig: StringInputConfig, outputConfig: StringOutputConfig): YearMonth = value.toYearMonth(inputConfig.pattern)

    fun parseFromInstant(value: Instant, outputConfig: InstantOutputConfig): YearMonth = value.toYearMonth(outputConfig.timezone)
}