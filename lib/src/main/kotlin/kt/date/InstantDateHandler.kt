package kt.date

import kt.date.model.Configuration
import kt.date.model.InputConfig
import kt.date.model.OutputConfig
import kt.date.model.Pattern
import kt.date.utils.ParserChooser
import java.time.Instant

class InstantDateHandler<RESULT>(
    private val value: Instant,
    private val inputConfig: InputConfig = InputConfig(),
    val outputConfig: OutputConfig = OutputConfig()
) : DateHandler<RESULT> {

    override fun validateAndBuildConfiguration(): Configuration<Instant> {
        val type = requireNotNull(outputConfig.type) { "output type is required" }

        return Configuration(
            pattern = Pattern(value, inputConfig.pattern),
            timezone = outputConfig.timezone,
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