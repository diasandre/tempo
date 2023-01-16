package kt.date

import kt.date.model.enums.OPERATION_TYPE
import kt.date.model.enums.OUTPUT_TYPE
import kt.date.model.update.Day
import kt.date.model.update.Month
import kt.date.model.update.Year
import kt.date.utils.LOCAL_DATE_PATTERN
import kt.date.utils.YEAR_MONTH_PATTERN
import kt.date.utils.constants.ErrorMessages.FAILED_TO_CONVERT_FOR_PATTERN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Instant
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class StringDateHelperTest {

    @Test
    fun `should convert string yyyy-MM-dd into year month`() {
        val actual: YearMonth = "2022-01-01".handle {
            input {
                pattern = LOCAL_DATE_PATTERN
            }
            output {
                type = OUTPUT_TYPE.YEAR_MONTH
                update {
                    day = Day(1)
                    month = Month(2, OPERATION_TYPE.PLUS)
                    year = Year(2020)
                }
            }
        }

        assertThat(actual).isEqualTo(YearMonth.of(2020, 3))
    }

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
    fun `should convert string dd MM yyyy into local date`() {
        val actual: LocalDate = "20/01/2023".handle {
            input {
                pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            }
            output {
                type = OUTPUT_TYPE.LOCAL_DATE
            }
        }

        assertThat(actual).isEqualTo(LocalDate.of(2023, 1, 20))
    }

    @Test
    fun `should convert string local date time into local date`() {
        val actual: LocalDate = "20/01/2023 10:10:10".handle {
            input {
                pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            }
            output {
                type = OUTPUT_TYPE.LOCAL_DATE
            }
        }

        assertThat(actual).isEqualTo(LocalDate.of(2023, 1, 20))
    }

    @Test
    fun `should convert string local date time into instant`() {
        val actual: Instant = "20/01/2023 10:10:10".handle {
            input {
                pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            }
            output {
                type = OUTPUT_TYPE.INSTANT
            }
        }

        assertThat(actual).isEqualTo(Instant.ofEpochMilli(1674209410000))
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

        assertThat(assertThrows).hasMessage(FAILED_TO_CONVERT_FOR_PATTERN)
    }
}