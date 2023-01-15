package kt.date

import kt.date.model.Configuration
import kt.date.model.InputConfig
import kt.date.model.OutputConfig
import kt.date.model.Pattern
import kt.date.model.enums.INPUT_TYPE.DATE
import kt.date.model.enums.INPUT_TYPE.DATE_TIME
import kt.date.model.enums.INPUT_TYPE.YEAR_MONTH
import kt.date.utils.ErrorMessages.REQUIRED_INPUT_PATTERN
import kt.date.utils.ErrorMessages.REQUIRED_OUTPUT_TYPE
import kt.date.utils.ParserChooser

class StringDateHelper<RESULT>(
    private val value: String,
    val inputConfig: InputConfig = InputConfig(),
    val outputConfig: OutputConfig = OutputConfig()
) : DateHandler<RESULT> {

    private val requiredPatternTypes = listOf(DATE, DATE_TIME, YEAR_MONTH)

    override fun validateAndBuildConfiguration(): Configuration<String> {
        val type = requireNotNull(outputConfig.type) { REQUIRED_OUTPUT_TYPE }

        val configuration = Configuration(
            pattern = Pattern(value, inputConfig),
            timezone = outputConfig.timezone,
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