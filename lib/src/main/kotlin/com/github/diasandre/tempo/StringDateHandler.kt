package com.github.diasandre.tempo

import com.github.diasandre.tempo.model.Configuration
import com.github.diasandre.tempo.model.InputConfig
import com.github.diasandre.tempo.model.OutputConfig
import com.github.diasandre.tempo.model.OutputUpdateConfig
import com.github.diasandre.tempo.model.Pattern
import com.github.diasandre.tempo.model.enums.INPUT_TYPE.DATE
import com.github.diasandre.tempo.model.enums.INPUT_TYPE.DATE_TIME
import com.github.diasandre.tempo.model.enums.INPUT_TYPE.YEAR_MONTH
import com.github.diasandre.tempo.utils.ParserChooser
import com.github.diasandre.tempo.utils.UpdateConfigHelper
import com.github.diasandre.tempo.utils.constants.ErrorMessages.REQUIRED_INPUT_PATTERN
import com.github.diasandre.tempo.utils.constants.ErrorMessages.REQUIRED_OUTPUT_TYPE

class StringDateHelper<RESULT>(
    private val value: String,
    val inputConfig: InputConfig = InputConfig(),
    val outputConfig: OutputConfig = OutputConfig()
) : com.github.diasandre.tempo.DateHandler<RESULT> {

    private val requiredPatternTypes = listOf(DATE, DATE_TIME, YEAR_MONTH)

    override fun validateAndBuildConfiguration(): Configuration<String> {
        val type = requireNotNull(outputConfig.type) { REQUIRED_OUTPUT_TYPE }

        val configuration = Configuration(
            pattern = Pattern(value, inputConfig),
            timezone = outputConfig.timezone,
            updates = UpdateConfigHelper.build(outputConfig.update),
            type = type
        )

        if (requiredPatternTypes.contains(configuration.pattern.type)) {
            requireNotNull(configuration.pattern.formatter) { REQUIRED_INPUT_PATTERN }
        }

        return configuration
    }

    override fun run(): RESULT = validateAndBuildConfiguration().let { configuration ->
        val parser: (String, Configuration<String>) -> Any = ParserChooser.chooseFromString(configuration)
        parser(value, configuration)
    } as RESULT
}

fun <RESULT> String.handle(block: StringDateHelper<RESULT>.() -> Unit): RESULT = StringDateHelper<RESULT>(this)
    .also {
        block(it)
    }.run()

fun <RESULT> StringDateHelper<RESULT>.input(block: InputConfig.() -> Unit) {
    inputConfig.apply(block)
}

fun <RESULT> StringDateHelper<RESULT>.output(block: OutputConfig.() -> Unit) {
    outputConfig.apply(block)
}

fun OutputConfig.update(block: OutputUpdateConfig.() -> Unit) {
    update = OutputUpdateConfig().apply(block)
}