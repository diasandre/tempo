package kt.date.model

import kt.date.model.update.Day
import kt.date.model.update.Month
import kt.date.model.update.Year

data class OutputUpdateConfig(
    var day: Day? = null,
    var month: Month? = null,
    var year: Year? = null
)