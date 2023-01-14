package kt.date.model

import kt.date.model.enums.INSTANT_OUTPUT_TYPE
import kt.date.model.enums.TIMEZONE

data class InstantOutputConfig(var type: INSTANT_OUTPUT_TYPE? = null, var timezone: TIMEZONE = TIMEZONE.UTC)