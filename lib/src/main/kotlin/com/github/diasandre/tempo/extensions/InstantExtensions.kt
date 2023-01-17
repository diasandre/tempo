package com.github.diasandre.tempo.extensions

import com.github.diasandre.tempo.model.enums.TIMEZONE
import java.time.Instant
import java.time.LocalDateTime
import java.time.YearMonth

fun Instant.toLocalDate(timezone: TIMEZONE) = LocalDateTime.ofInstant(this, timezone.zoneId).toLocalDate()

fun Instant.toYearMonth(timezone: TIMEZONE): YearMonth = toLocalDate(timezone).let(YearMonth::from)