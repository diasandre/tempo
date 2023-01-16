package kt.date.model

import kt.date.model.enums.OUTPUT_TYPE
import kt.date.model.enums.TIMEZONE
import kt.date.model.update.UpdateOperation

data class Configuration<VALUE>(val pattern: Pattern<VALUE>, val updates: List<UpdateOperation>, val timezone: TIMEZONE, val type: OUTPUT_TYPE)