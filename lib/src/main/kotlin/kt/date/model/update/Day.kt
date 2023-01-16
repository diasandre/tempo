package kt.date.model.update

import kt.date.model.enums.DATE_PERIOD
import kt.date.model.enums.OPERATION_TYPE
import kt.date.model.enums.VALUE_TYPE.DAY

data class Day(
    override val value: Int,
    override val updateOperation: UpdateOperation = UpdateOperation(value.toLong(), DAY, OPERATION_TYPE.OVERRIDE),
    val to: DATE_PERIOD? = null
) : Period {
    constructor(value: Int, operation: OPERATION_TYPE) : this(
        value = value,
        updateOperation = UpdateOperation(value.toLong(), DAY, operation)
    )
}