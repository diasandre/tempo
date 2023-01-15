package kt.date.model

import kt.date.model.enums.INPUT_TYPE
import kt.date.utils.InputTypeHelper
import java.time.format.DateTimeFormatter

data class Pattern<VALUE>(val type: INPUT_TYPE, val formatter: DateTimeFormatter? = null) {
    constructor(value: VALUE, inputConfig: InputConfig) : this(
        formatter = inputConfig.pattern,
        type = inputConfig.inputType ?: InputTypeHelper.get(value)
    )
}