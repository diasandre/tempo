package com.github.diasandre.tempo.model.update

import com.github.diasandre.tempo.model.enums.DATE_PERIOD
import com.github.diasandre.tempo.model.enums.OPERATION_TYPE
import com.github.diasandre.tempo.model.enums.VALUE_TYPE.DAY

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