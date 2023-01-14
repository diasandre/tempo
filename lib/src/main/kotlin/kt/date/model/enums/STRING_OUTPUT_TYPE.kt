package kt.date.model.enums

import kt.date.model.StringInputConfig
import kt.date.model.StringOutputConfig
import kt.date.utils.InstantTypeHelper
import kt.date.utils.LocalDateTypeHelper
import kt.date.utils.YearMonthTypeHelper
import java.time.Instant
import java.time.LocalDate
import java.time.YearMonth

enum class STRING_OUTPUT_TYPE(val type: Class<*>, val parser: (String, StringInputConfig, StringOutputConfig) -> Any) {
    LOCAL_DATE(LocalDate::class.java, LocalDateTypeHelper::parseFromString),
    YEAR_MONTH(YearMonth::class.java, YearMonthTypeHelper::parseFromString),
    INSTANT(Instant::class.java, InstantTypeHelper::parseFromString)
}