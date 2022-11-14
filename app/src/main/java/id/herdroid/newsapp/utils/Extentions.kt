package id.herdroid.newsapp.utils

import android.content.Context

object Extentions {

    fun Context.loadJSONFromAssets(fileName: String): String {
        return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
            reader.readText()
        }
    }
}