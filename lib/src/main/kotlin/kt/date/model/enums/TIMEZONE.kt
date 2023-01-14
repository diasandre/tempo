package kt.date.model.enums

import java.time.ZoneId
import java.time.ZoneOffset
import java.util.TimeZone

enum class TIMEZONE(private val value: String, val zoneOffset: ZoneOffset, val zoneId: ZoneId = TimeZone.getTimeZone(value).toZoneId()) {
    UTC("UTC", ZoneOffset.UTC)
}