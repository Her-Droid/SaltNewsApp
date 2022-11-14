package id.herdroid.newsapp.data.model.source

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataSource(

	@field:SerializedName("country")
	val country: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("language")
	val language: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("url")
	val url: String
) : Parcelable