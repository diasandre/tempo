package kt.date.extensions

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.ZoneOffset

fun LocalDate.toYearMonth(): YearMonth = YearMonth.from(this)

fun LocalDate.toInstant(timezone: ZoneOffset = ZoneOffset.UTC): Instant = Instant.from(this.atStartOfDay().atZone(timezone))

fun LocalDateTime.toInstant(timezone: ZoneOffset = ZoneOffset.UTC): Instant = Instant.from(this.atZone(timezone))