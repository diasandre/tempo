package com.github.diasandre.tempo

import com.github.diasandre.tempo.model.enums.OUTPUT_TYPE
import com.github.diasandre.tempo.utils.constants.ErrorMessages.OUTPUT_IS_EQUAL_TO_INPUT
import com.github.diasandre.tempo.utils.constants.ErrorMessages.REQUIRED_OUTPUT_TYPE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Instant
import java.time.LocalDate
import java.time.YearMonth

class InstantDateHelperTest {

    private val defaultValue = Instant.ofEpochMilli(1672531200000L)

    @Test
    fun `should convert instant into local date`() {
        val actual: LocalDate = defaultValue.handle {
            output {
                type = OUTPUT_TYPE.LOCAL_DATE
            }
        }

        assertThat(actual).isEqualTo(LocalDate.of(2023, 1, 1))
    }

    @Test
    fun `should convert instant into year month`() {
        val actual: YearMonth = defaultValue.handle {
            output {
                type = OUTPUT_TYPE.YEAR_MONTH
            }
        }

        assertThat(actual).isEqualTo(YearMonth.of(2023, 1))
    }

    @Test
    fun `should fail to convert local date`() {
        val assertThrows = assertThrows<Exception> {
            defaultValue.handle {}
        }

        assertThat(assertThrows).hasMessage(REQUIRED_OUTPUT_TYPE)
    }

    @Test
    fun `should fail to convert instant to instant`() {
        val assertThrows = assertThrows<Exception> {
            defaultValue.handle {
                output {
                    type = OUTPUT_TYPE.INSTANT
                }
            }
        }

        assertThat(assertThrows).hasMessage(OUTPUT_IS_EQUAL_TO_INPUT)
    }
}