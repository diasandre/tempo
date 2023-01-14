package kt.date

import kt.date.model.StringInputConfig
import kt.date.model.StringOutputConfig
import kt.date.model.enums.STRING_OUTPUT_TYPE
import kt.date.utils.LOCAL_DATE_PATTERN
import java.time.LocalDate

class StringDateHelper<RESULT>(private val value: String, val inputConfig: StringInputConfig = StringInputConfig(), val outputConfig: StringOutputConfig = StringOutputConfig()) {

    private fun checkConfigs(): StringDateHelper<RESULT> {
        requireNotNull(outputConfig.type) { "output type is required" }
        requireNotNull(inputConfig.pattern) { "input pattern is required" }

        return this
    }

    fun run(): RESULT = checkConfigs().let {
        val type: STRING_OUTPUT_TYPE = requireNotNull(outputConfig.type)
        val result = type.parser(value, inputConfig, outputConfig)
    } as RESULT
}

fun <RESULT> String.handle(block: StringDateHelper<RESULT>.() -> Unit): RESULT = StringDateHelper<RESULT>(this)
    .also {
        block(it)
    }.run()

fun <RESULT> StringDateHelper<RESULT>.input(block: StringInputConfig.() -> Unit) {
    inputConfig.apply(block)
}

fun <RESULT> StringDateHelper<RESULT>.output(block: StringOutputConfig.() -> Unit) {
    outputConfig.apply(block)
}

fun main() {
    val date: LocalDate = "2022-01-01".handle {
        input {
            pattern = LOCAL_DATE_PATTERN
        }
        output {
            type = STRING_OUTPUT_TYPE.LOCAL_DATE
        }
    }

    println(date)
}