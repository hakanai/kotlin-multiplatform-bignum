package com.ionspin.kotlin.bignum.decimal.util

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.toBigDecimal

object NaturalLogarithmCalculator : SeriesCalculator() {
    override fun createTermSequence(x: BigDecimal, decimalMode: DecimalMode): Sequence<BigDecimal> {
        // This uses an alternative series which converges for x > 0
        val y = (x - BigDecimal.ONE).divide((x + BigDecimal.ONE), decimalMode)

        // y, y^3, y^5, y^7, ...
        val powerSequence = createAllPowersSequence(y, decimalMode)
            .filterIndexed { i, _ -> i % 2 != 0 }

        // 2, 2/3, 2/5, 2/7, ...
        val factorSequence = generateSequence(1) { n -> n + 2 }
            .map { n -> BigDecimal.ONE.divide(n.toBigDecimal(), decimalMode) * 2 }

        return powerSequence.zip(factorSequence) { a, b -> a.multiply(b, decimalMode) }
    }
}