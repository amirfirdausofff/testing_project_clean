package com.demo.testingproject.util

import com.demo.testingproject.constant.EmptyValues
import java.math.BigDecimal

fun emptyString(): String = EmptyValues.STRING

fun Int?.orEmpty(): Int = this ?: EmptyValues.INT

fun emptyInt(): Int = EmptyValues.INT

fun Double?.orEmpty(): Double = this ?: EmptyValues.DOUBLE

fun emptyDouble(): Double = EmptyValues.DOUBLE

fun Float?.orEmpty(): Float = this ?: EmptyValues.FLOAT

fun emptyFloat(): Float = EmptyValues.FLOAT

fun Long?.orEmpty(): Long = this ?: EmptyValues.LONG

fun emptyLong(): Long = EmptyValues.LONG

fun Int?.orEmptyIndex(): Int = this ?: EmptyValues.INDEX

fun emptyIndex(): Int = EmptyValues.INDEX

fun Boolean?.orEmpty(): Boolean = this ?: false

fun emptyBoolean(): Boolean = false

fun BigDecimal?.orEmpty(): BigDecimal = this ?: BigDecimal.ZERO

