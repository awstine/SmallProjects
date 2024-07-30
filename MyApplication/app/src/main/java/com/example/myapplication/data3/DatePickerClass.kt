package com.example.myapplication.data3

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.sql.Date
import java.text.SimpleDateFormat

class DatePickerClass {
    companion object {
        fun openLink(
            eContext: Context,
            url: String,
        ) {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(url)
            eContext.startActivity(openUrl)
        }

        fun convertToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("dd MMM YYYY")
            return format.format(date)
        }
    }
}
