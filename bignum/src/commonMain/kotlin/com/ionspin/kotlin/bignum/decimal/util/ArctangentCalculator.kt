package com.ionspin.kotlin.bignum.decimal.util

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.toBigDecimal

object ArctangentCalculator : SeriesCalculator() {
    private val MINUS_ONE = BigDecimal.parseString("-1")

    override fun createTermSequence(x: BigDecimal, decimalMode: DecimalMode): Sequence<BigDecimal> {
        // x, x^3, x^5, x^7, ...
        val powerSequence = createAllPowersSequence(x, decimalMode)
            .filterIndexed { i, _ -> i % 2 != 0 }

        // 1, -1/3, 1/5, -1/7, ...
        val factorSequence = generateSequence(1) { n -> n + 2 }
            .mapIndexed { i, n ->
                val sign = if (i % 2 == 0) BigDecimal.ONE else MINUS_ONE
                sign.divide(n.toBigDecimal(), decimalMode)
            }

        return powerSequence.zip(factorSequence) { a, b -> a.multiply(b, decimalMode) }
    }
}