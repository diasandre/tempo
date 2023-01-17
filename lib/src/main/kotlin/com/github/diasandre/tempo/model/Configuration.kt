package com.github.diasandre.tempo.model

import com.github.diasandre.tempo.model.enums.OUTPUT_TYPE
import com.github.diasandre.tempo.model.enums.TIMEZONE
import com.github.diasandre.tempo.model.update.UpdateOperation

data class Configuration<VALUE>(val pattern: Pattern<VALUE>, val updates: List<UpdateOperation>, val timezone: TIMEZONE, val type: OUTPUT_TYPE)