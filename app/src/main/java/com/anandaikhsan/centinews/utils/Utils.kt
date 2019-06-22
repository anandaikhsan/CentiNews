package com.anandaikhsan.centinews.utils

import org.joda.time.DateTime
import java.util.*
import org.joda.time.Interval
import org.joda.time.Period

class Utils {
    companion object {
        val apiKey: String = "61934c51fa4e40138484f9c13d8d0578"
        fun dateSpan(start: DateTime, end: DateTime): String{
            val interval: Interval = Interval(start, end)
            val period: Period = interval.toPeriod()
            var message: String = ""
            when {
                period.years > 0 -> {
                    message = Utils.generateMessage(period.years, "tahun")
                }
                period.months > 0 -> {
                    message = Utils.generateMessage(period.months, "bulan")
                }
                period.days > 0 -> {
                    message = Utils.generateMessage(period.days, "hari")
                }
                period.hours > 0 -> {
                    message = Utils.generateMessage(period.hours, "jam")
                }
                period.minutes > 0 -> {
                    message = Utils.generateMessage(period.minutes, "menit")
                }
                else -> {
                    message = "Beberapa saat yang lalu"
                }
            }

            return message
        }

        fun generateMessage(period: Any, type: String): String{
            return String.format("%d %s yang lalu", period, type)
        }

    }
}