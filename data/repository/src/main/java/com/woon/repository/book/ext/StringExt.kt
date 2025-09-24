package com.woon.repository.book.ext

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal fun String.toDate(): Date {
    if (this.isBlank()) {
        return Date()
    }

    return try {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        format.parse(this) ?: Date()
    } catch (e: ParseException) {
        Date()
    }
}
