package kt.date.model

import kt.date.model.enums.OUTPUT_TYPE
import kt.date.model.enums.TIMEZONE

data class Configuration<VALUE>(val pattern: Pattern<VALUE>, val timezone: TIMEZONE, val type: OUTPUT_TYPE)