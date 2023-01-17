package com.github.diasandre.tempo.model

import com.github.diasandre.tempo.model.enums.OUTPUT_TYPE
import com.github.diasandre.tempo.model.enums.TIMEZONE

data class OutputConfig(
    var type: OUTPUT_TYPE? = null,
    var timezone: TIMEZONE = TIMEZONE.UTC,
    var update: OutputUpdateConfig? = null
)