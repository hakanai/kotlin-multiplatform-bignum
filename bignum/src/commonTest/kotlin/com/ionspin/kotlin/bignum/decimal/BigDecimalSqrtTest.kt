package com.ionspin.kotlin.bignum.decimal

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BigDecimalSqrtTest {

    @Test
    fun sqrtTest() {
        val decimalMode = DecimalMode(decimalPrecision = 20, roundingMode = RoundingMode.TOWARDS_ZERO)
        val examples = listOf(
            "0" to "0",
            "0.5" to "0.70710678118654752440",
            "1" to "1",
            "2" to "1.4142135623730950488",
            "4" to "2",
            "12" to "3.4641016151377545870",
            "6440728303881" to "2537859",
        )

        for ((input, expected) in examples) {
            assertEquals(BigDecimal.parseString(expected), BigDecimal.parseString(input).sqrt(decimalMode))
        }
    }

    @Test
    fun illegalSqrtTest() {
        val decimalMode = DecimalMode(decimalPrecision = 20, roundingMode = RoundingMode.TOWARDS_ZERO)
        assertFailsWith(ArithmeticException::class) {
            BigDecimal.parseString("-1").sqrt(decimalMode)
        }
    }
}