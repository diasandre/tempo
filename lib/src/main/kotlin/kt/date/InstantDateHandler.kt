package kt.date

import kt.date.model.Configuration
import kt.date.model.InputConfig
import kt.date.model.OutputConfig
import kt.date.model.Pattern
import kt.date.utils.ParserChooser
import kt.date.utils.UpdateConfigHelper
import kt.date.utils.constants.ErrorMessages.REQUIRED_OUTPUT_TYPE
import java.time.Instant

class InstantDateHandler<RESULT>(
    private val value: Instant,
    private val inputConfig: InputConfig = InputConfig(),
    val outputConfig: OutputConfig = OutputConfig()
) : DateHandler<RESULT> {

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