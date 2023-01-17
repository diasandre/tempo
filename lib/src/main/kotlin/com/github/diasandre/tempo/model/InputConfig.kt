package com.github.diasandre.tempo.model

import com.github.diasandre.tempo.model.enums.INPUT_TYPE
import java.time.format.DateTimeFormatter

data class InputConfig(var pattern: DateTimeFormatter? = null, var inputType: INPUT_TYPE? = null)