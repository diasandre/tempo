package kt.date.extensions

import java.time.Instant
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

fun String.toLocalDate(pattern: DateTimeFormatter?): LocalDate = runCatching {
    LocalDate.parse(this, requireNotNull(pattern))
}.onFailure {
    throw Exception("input is not following the selected pattern")
}.getOrThrow()

fun String.toYearMonth(pattern: String = "yyyy-MM"): YearMonth = DateTimeFormatter.ofPattern(pattern)
    .let { formatter -> YearMonth.parse(this, formatter) }

fun String.toYearMonth(pattern: DateTimeFormatter?): YearMonth = YearMonth.parse(this, pattern)

fun String.toInstant(): Instant = Instant.parse(this)