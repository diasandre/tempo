package kt.date

import kt.date.model.InstantOutputConfig
import kt.date.model.enums.INSTANT_OUTPUT_TYPE
import java.time.Instant
import java.time.LocalDate

class InstantDateHelper<RESULT>(private val value: Instant, val outputConfig: InstantOutputConfig = InstantOutputConfig()) {

    private fun checkConfigs(): InstantDateHelper<RESULT> {
        requireNotNull(outputConfig.type) { "output type is required" }

        return this
    }

    fun run(): RESULT = checkConfigs().let { requireNotNull(outputConfig.type).parser(value, outputConfig) } as RESULT
}

fun <RESULT> Instant.handle(block: InstantDateHelper<RESULT>.() -> Unit): RESULT = InstantDateHelper<RESULT>(this)
    .also {
        block(it)
    }.run()

fun <RESULT> InstantDateHelper<RESULT>.output(block: InstantOutputConfig.() -> Unit) {
    outputConfig.apply(block)
}

fun main() {
    val date: LocalDate = Instant.now().handle {
        output {
            type = INSTANT_OUTPUT_TYPE.LOCAL_DATE
        }
    }

    println(date)
}