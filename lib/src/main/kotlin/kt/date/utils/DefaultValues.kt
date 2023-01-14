package kt.date.utils

import java.time.format.DateTimeFormatter

val LOCAL_DATE_PATTERN: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE
val YEAR_MONTH_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM")
val INSTANT_PATTERN = DateTimeFormatter.ISO_INSTANT