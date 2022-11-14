package id.herdroid.newsapp.data.model.source

import com.google.gson.annotations.SerializedName
import id.herdroid.newsapp.data.model.source.DataSource

data class ResponseSource(

    @field:SerializedName("sources")
	val sources: List<DataSource>,

    @field:SerializedName("status")
	val status: String
)