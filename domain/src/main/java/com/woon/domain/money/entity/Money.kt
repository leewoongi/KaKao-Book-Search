package com.woon.domain.money.entity

import java.text.DecimalFormat

data class Money(
    val value: Double
){
    // 연산자 오버로딩
    operator fun plus(other: Money): Money = Money(value + other.value)
    operator fun minus(other: Money): Money = Money(value - other.value)
    operator fun times(multiplier: Double): Money = Money(value * multiplier)
    operator fun div(divisor: Double): Money = Money(value / divisor)

    // 비교 연산자
    operator fun compareTo(other: Money): Int = value.compareTo(other.value)

    // 유틸리티 함수들
    fun abs(): Money = Money(kotlin.math.abs(value))

    // 포맷팅 (정수로 표시)
    fun toFormattedString(): String {
        return DecimalFormat("#,###").format(value.toLong())
    }

    fun toCurrencyString(symbol: String = "₩"): String {
        return if (value <= 0.0) {
            "${symbol}0원"
        } else {
            "$symbol${DecimalFormat("#,###").format(value.toLong())}원"
        }
    }

    // 백분율 계산
    fun changeRate(from: Money): Double {
        if (from.value == 0.0) return 0.0
        return ((value - from.value) / value * 100)
    }

    fun changeRateString(from: Money): String {
        val rate = changeRate(from)
        return "${rate.toInt()}%"
    }

    // 변환
    fun toDouble(): Double = value
    fun toLong(): Long = value.toLong()
    fun toInt(): Int = value.toInt()

    // 정수로 표시 (소수점 제거)
    override fun toString(): String = value.toLong().toString()

    companion object {
        val ZERO = Money(0.0)

        fun max(a: Money, b: Money): Money = if (a >= b) a else b
        fun min(a: Money, b: Money): Money = if (a <= b) a else b

        fun fromCurrencyString(value: String, symbol: String = "₩"): Money {
            val numeric = value
                .replace(symbol, "")
                .replace("원", "")
                .replace(",", "")
                .trim()

            return Money(numeric.toDoubleOrNull() ?: 0.0)
        }
    }
}
