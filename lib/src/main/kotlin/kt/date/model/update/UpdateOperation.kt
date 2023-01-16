package kt.date.model.update

import kt.date.model.enums.OPERATION_TYPE
import kt.date.model.enums.VALUE_TYPE

data class UpdateOperation(
    val value: Long,
    val type: VALUE_TYPE,
    val operation: OPERATION_TYPE
)