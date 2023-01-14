package kt.date.model.enums

import kt.date.model.InstantOutputConfig
import kt.date.utils.LocalDateTypeHelper
import kt.date.utils.YearMonthTypeHelper
import java.time.Instant

enum class INSTANT_OUTPUT_TYPE(val parser: (Instant, InstantOutputConfig) -> Any) {
    LOCAL_DATE(LocalDateTypeHelper::parseFromInstant),
    YEAR_MONTH(YearMonthTypeHelper::parseFromInstant),
}