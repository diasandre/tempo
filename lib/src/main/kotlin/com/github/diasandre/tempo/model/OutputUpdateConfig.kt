package com.github.diasandre.tempo.model

import com.github.diasandre.tempo.model.update.Day
import com.github.diasandre.tempo.model.update.Month
import com.github.diasandre.tempo.model.update.Year

data class OutputUpdateConfig(
    var day: Day? = null,
    var month: Month? = null,
    var year: Year? = null
)