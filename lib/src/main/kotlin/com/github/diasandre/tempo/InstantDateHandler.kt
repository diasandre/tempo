package com.github.diasandre.tempo

import com.github.diasandre.tempo.model.Configuration
import com.github.diasandre.tempo.model.InputConfig
import com.github.diasandre.tempo.model.OutputConfig
import com.github.diasandre.tempo.model.Pattern
import com.github.diasandre.tempo.utils.ParserChooser
import com.github.diasandre.tempo.utils.UpdateConfigHelper
import com.github.diasandre.tempo.utils.constants.ErrorMessages.REQUIRED_OUTPUT_TYPE
import java.time.Instant

class InstantDateHandler<RESULT>(
    private val value: Instant,
    private val inputConfig: InputConfig = InputConfig(),
    val outputConfig: OutputConfig = OutputConfig()
) : com.github.diasandre.tempo.DateHandler<RESULT> {

    override fun validateAndBuildConfiguration(): Configuration<Instant> {
        val type = requireNotNull(outputConfig.type) { REQUIRED_OUTPUT_TYPE }

        return Configuration(
            pattern = Pattern(value, inputConfig),
            timezone = outputConfig.timezone,
            updates = UpdateConfigHelper.build(outputConfig.update),
            type = type
        )
    }

    override fun run(): RESULT = validateAndBuildConfiguration().let { configuration ->
        val parser: (Instant, Configuration<Instant>) -> Any = ParserChooser.chooseFromInstant(configuration)
        parser(value, configuration)
    } as RESULT
}

fun <RESULT> Instant.handle(block: InstantDateHandler<RESULT>.() -> Unit): RESULT = InstantDateHandler<RESULT>(this)
    .also {
        block(it)
    }.run()

fun <RESULT> InstantDateHandler<RESULT>.output(block: OutputConfig.() -> Unit) {
    outputConfig.apply(block)
}