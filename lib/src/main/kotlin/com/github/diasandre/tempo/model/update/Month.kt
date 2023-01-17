package com.github.diasandre.tempo.model.update

import com.github.diasandre.tempo.model.enums.OPERATION_TYPE
import com.github.diasandre.tempo.model.enums.VALUE_TYPE.MONTH

data class Month(
    override val value: Int,
    override val updateOperation: UpdateOperation = UpdateOperation(value.toLong(), MONTH, OPERATION_TYPE.OVERRIDE)
) : Period {
    constructor(value: Int, operation: OPERATION_TYPE) : this(
        value = value,
        updateOperation = UpdateOperation(value.toLong(), MONTH, operation)
    )
}