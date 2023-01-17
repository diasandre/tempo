package com.github.diasandre.tempo

import com.github.diasandre.tempo.model.Configuration
import com.github.diasandre.tempo.model.InputConfig
import com.github.diasandre.tempo.model.OutputConfig
import com.github.diasandre.tempo.model.Pattern
import com.github.diasandre.tempo.utils.ParserChooser
import com.github.diasandre.tempo.utils.UpdateConfigHelper
import com.github.diasandre.tempo.utils.constants.ErrorMessages.REQUIRED_OUTPUT_TYPE
import java.time.LocalDate

class LocalDateDateHandler<RESULT>(
    private val value: LocalDate,
    private val inputConfig: InputConfig = InputConfig(),
    val outputConfig: OutputConfig = OutputConfig()
) : DateHandler<RESULT> {

    override fun validateAndBuildConfiguration(): Configuration<LocalDate> {
        val type = requireNotNull(outputConfig.type) { REQUIRED_OUTPUT_TYPE }

        return Configuration(
            pattern = Pattern(value, inputConfig),
            timezone = outputConfig.timezone,
            updates = UpdateConfigHelper.build(outputConfig.update),
            type = type
        )
    }

    override fun run(): RESULT = validateAndBuildConfiguration().let { configuration ->
        val parser: (LocalDate, Configuration<LocalDate>) -> Any = ParserChooser.chooseFromLocalDate(configuration)
        parser(value, configuration)
    } as RESULT
}

fun <RESULT> LocalDate.handle(block: LocalDateDateHandler<RESULT>.() -> Unit): RESULT = LocalDateDateHandler<RESULT>(this)
    .also {
        block(it)
    }.run()

fun <RESULT> LocalDateDateHandler<RESULT>.output(block: OutputConfig.() -> Unit) {
    outputConfig.apply(block)
}