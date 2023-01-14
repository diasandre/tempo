package kt.date.model

import kt.date.model.enums.STRING_OUTPUT_TYPE
import kt.date.model.enums.TIMEZONE
import java.time.format.DateTimeFormatter

data class StringInputConfig(var pattern: DateTimeFormatter? = null)

data class StringOutputConfig(var type: STRING_OUTPUT_TYPE? = null, var timezone: TIMEZONE = TIMEZONE.UTC)