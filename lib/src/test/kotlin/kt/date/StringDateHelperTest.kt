package kt.date

import kt.date.model.enums.OUTPUT_TYPE
import kt.date.utils.LOCAL_DATE_PATTERN
import kt.date.utils.YEAR_MONTH_PATTERN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class StringDateHelperTest {

    @Test
    fun `should convert string yyyy-MM-dd into local date`() {
        val actual: LocalDate = "2022-01-01".handle {
            input {
                pattern = LOCAL_DATE_PATTERN
            }
            output {
                type = OUTPUT_TYPE.LOCAL_DATE
            }
        }

        assertThat(actual).isEqualTo(LocalDate.of(2022, 1, 1))
    }

    @Test
    fun `should convert string yyyy-MM into local date`() {
        val actual: LocalDate = "2022-01".handle {
            input {
                pattern = YEAR_MONTH_PATTERN
            }
            output {
                type = OUTPUT_TYPE.LOCAL_DATE
            }
        }

        assertThat(actual).isEqualTo(LocalDate.of(2022, 1, 1))
    }

    @Test
    fun `should convert string string 10 digits into local date`() {
        val actual: LocalDate = "1673730483".handle {
            output {
                type = OUTPUT_TYPE.LOCAL_DATE
            }
        }

        assertThat(actual).isEqualTo(LocalDate.of(2023, 1, 14))
    }

    @Test
    fun `should convert string string 13 digits into local date`() {
        val actual: LocalDate = "1673730483000".handle {
            output {
                type = OUTPUT_TYPE.LOCAL_DATE
            }
        }

        assertThat(actual).isEqualTo(LocalDate.of(2023, 1, 14))
    }

    @Test
    fun `should fail to convert string yyyy-MM-dd into local date`() {
        val assertThrows = assertThrows<Exception> {
            "202-1-01".handle {
                input {
                    pattern = LOCAL_DATE_PATTERN
                }
                output {
                    type = OUTPUT_TYPE.LOCAL_DATE
                }
            }
        }

        assertThat(assertThrows).hasMessage("input is not following the selected pattern")
    }
}