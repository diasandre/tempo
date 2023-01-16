package kt.date.model.update

import kt.date.model.enums.OPERATION_TYPE
import kt.date.model.enums.VALUE_TYPE.MONTH

data class Month(
    override val value: Int,
    override val updateOperation: UpdateOperation = UpdateOperation(value.toLong(), MONTH, OPERATION_TYPE.OVERRIDE)
) : Period {
    constructor(value: Int, operation: OPERATION_TYPE) : this(
        value = value,
        updateOperation = UpdateOperation(value.toLong(), MONTH, operation)
    )
}