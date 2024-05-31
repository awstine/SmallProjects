@file:Suppress("ktlint:standard:no-empty-file")

package com.example.tiptime

import com.example.statemanagement2.calculateTip
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {
    @Test
    fun calculateTip_20PercentNoRoundUp() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        calculateTip(amount, tipPercent, false)
        val actualTip = calculateTip(amount, tipPercent, false)

        assertEquals(expectedTip, actualTip)
    }
}
