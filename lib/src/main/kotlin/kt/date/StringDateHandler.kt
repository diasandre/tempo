package kt.date

import kt.date.model.Configuration
import kt.date.model.InputConfig
import kt.date.model.OutputConfig
import kt.date.model.Pattern
import kt.date.model.enums.INPUT_TYPE
import kt.date.utils.ParserChooser

class StringDateHelper<RESULT>(
    private val value: String,
    val inputConfig: InputConfig = InputConfig(),
    val outputConfig: OutputConfig = OutputConfig()
) : DateHandler<RESULT> {

    override fun validateAndBuildConfiguration(): Configuration<String> {
        val type = requireNotNull(outputConfig.type) { "output type is required" }

        val configuration = Configuration(
            pattern = Pattern(value, inputConfig.pattern),
            timezone = outputConfig.timezone,
            type = type
        )

        if (configuration.pattern.type == INPUT_TYPE.DATE || configuration.pattern.type == INPUT_TYPE.YEAR_MONTH) {
            requireNotNull(configuration.pattern.formatter) { "input pattern is required" }
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