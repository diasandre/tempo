package kt.date.model.update

import kt.date.model.enums.OPERATION_TYPE
import kt.date.model.enums.VALUE_TYPE.YEAR

data class Year(
    override val value: Int,
    override val updateOperation: UpdateOperation = UpdateOperation(value.toLong(), YEAR, OPERATION_TYPE.OVERRIDE)
) : Period {
    constructor(value: Int, operation: OPERATION_TYPE) : this(
        value = value,
        updateOperation = UpdateOperation(value.toLong(), YEAR, operation)
    )
}