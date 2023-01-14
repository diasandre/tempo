package kt.date.utils

import kt.date.extensions.toInstant
import kt.date.extensions.toLocalDate
import kt.date.model.StringInputConfig
import kt.date.model.StringOutputConfig
import java.time.Instant

object InstantTypeHelper {
    fun parseFromString(value: String, inputConfig: StringInputConfig, outputConfig: StringOutputConfig): Instant = value.toLocalDate(inputConfig.pattern).toInstant(outputConfig.timezone.zoneOffset)
}