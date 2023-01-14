package kt.date.extensions

import kt.date.model.enums.TIMEZONE
import kt.date.utils.DateUtils
import java.time.Instant
import java.time.YearMonth

fun Instant.toLocalDate(timezone: String) = DateUtils.fromInstantToLocalDateByTimezone(this, timezone)

fun Instant.toLocalDate(timezone: TIMEZONE) = DateUtils.fromInstantToLocalDateByTimezone(this, timezone.zoneId)

fun Instant.toYearMonth(timezone: TIMEZONE): YearMonth = toLocalDate(timezone).let(YearMonth::from)