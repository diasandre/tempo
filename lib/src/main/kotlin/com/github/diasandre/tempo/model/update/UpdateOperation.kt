package com.github.diasandre.tempo.model.update

import com.github.diasandre.tempo.model.enums.OPERATION_TYPE
import com.github.diasandre.tempo.model.enums.VALUE_TYPE

data class UpdateOperation(
    val value: Long,
    val type: VALUE_TYPE,
    val operation: OPERATION_TYPE
)