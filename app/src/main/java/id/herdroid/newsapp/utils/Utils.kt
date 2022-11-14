package id.herdroid.newsapp.utils

import android.annotation.SuppressLint
import android.content.Context
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun dateTimeHourAgo(dateTime: String?): String? {
        val prettyTime = PrettyTime(Locale.US)
        var isTime: String? = null
        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
            val date = simpleDateFormat.parse(dateTime!!)
            isTime = prettyTime.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return isTime
    }

    @SuppressLint("SimpleDateFormat")
    fun dateFormat(dateNews: String?): String? {
        val isDate: String?
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy - HH:mm:ss", Locale(getCountry()))
        isDate = try {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateNews!!)
            dateFormat.format(date!!)
        } catch (e: ParseException) {
            e.printStackTrace()
            dateNews
        }
        return isDate
    }

    private fun getCountry(): String {
        val locale = Locale.getDefault()
        val strCountry = locale.country
        return strCountry.toLowerCase(Locale.ROOT)
    }

    fun getDrawableIdFromFileName(context: Context, nameOfDrawable: String): Int {
        return context.resources.getIdentifier(nameOfDrawable, "drawable", context.packageName)
    }
}