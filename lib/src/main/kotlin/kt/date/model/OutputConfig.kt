package kt.date.model

import kt.date.model.enums.OUTPUT_TYPE
import kt.date.model.enums.TIMEZONE

data class OutputConfig(
    var type: OUTPUT_TYPE? = null,
    var timezone: TIMEZONE = TIMEZONE.UTC,
    var update: OutputUpdateConfig? = null
)