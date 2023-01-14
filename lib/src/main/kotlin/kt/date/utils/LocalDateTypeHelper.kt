package kt.date.utils

import kt.date.extensions.toLocalDate
import kt.date.model.InstantOutputConfig
import kt.date.model.StringInputConfig
import kt.date.model.StringOutputConfig
import java.time.Instant
import java.time.LocalDate

object LocalDateTypeHelper {
    fun parseFromString(value: String, inputConfig: StringInputConfig, outputConfig: StringOutputConfig): LocalDate = LocalDate.parse(value, inputConfig.pattern)

    fun parseFromInstant(value: Instant, outputConfig: InstantOutputConfig): LocalDate = value.toLocalDate(outputConfig.timezone)
}