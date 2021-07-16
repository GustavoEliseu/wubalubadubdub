package com.gustavo.wubalubadubdub.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class DateFormatUtil {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    private val dateFormat2 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    fun parse(s: String): Date? {
        return try {
            dateFormat.parse(s)
        } catch (parseException: ParseException) {
            try {
                dateFormat2.parse(s)
            } catch (parseException: ParseException) {

                val dateTimestamp = s.toLongOrNull()
                if (dateTimestamp == null) return null else Date(dateTimestamp)
            }
        }
    }

    fun format(d: Date): String {
        return dateFormat.format(d)
    }

}