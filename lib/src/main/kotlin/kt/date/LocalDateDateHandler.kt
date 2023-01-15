package kt.date

import kt.date.model.Configuration
import kt.date.model.InputConfig
import kt.date.model.OutputConfig
import kt.date.model.Pattern
import kt.date.utils.ErrorMessages.REQUIRED_OUTPUT_TYPE
import kt.date.utils.ParserChooser
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