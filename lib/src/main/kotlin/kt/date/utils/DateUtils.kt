package kt.date.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone

object DateUtils {

    fun fromInstantToLocalDateByTimezone(pointInTime: Instant, timezone: ZoneId): LocalDate {
        return LocalDateTime.ofInstant(pointInTime, timezone).toLocalDate()
    }

    fun fromInstantToLocalDateByTimezone(pointInTime: Instant, timezone: String): LocalDate {
        return LocalDateTime.ofInstant(pointInTime, TimeZone.getTimeZone(timezone).toZoneId()).toLocalDate()
    }

    fun fromInstantToLocalDateByTimezoneOrDefault(pointInTime: Instant?, timezone: String? = null): LocalDate {
        requireNotNull(pointInTime) { "instant cannot be null" }
        val selectedTimezone = if (timezone == null) ZoneId.systemDefault() else TimeZone.getTimeZone(timezone).toZoneId()
        return LocalDateTime.ofInstant(pointInTime, selectedTimezone).toLocalDate()
    }

    fun fromInstantToLocalDateByZoneID(pointInTime: Instant, zoneId: ZoneId? = null): LocalDate {
        val zone = zoneId ?: ZoneId.systemDefault()
        return LocalDateTime.ofInstant(pointInTime, zone).toLocalDate()
    }
}