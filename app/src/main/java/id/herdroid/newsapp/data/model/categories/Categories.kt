package id.herdroid.newsapp.data.model.categories

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Categories(
        var id: String?,
        var title: String?,
        var icon: String?,
        var color: String?
) : Parcelable
