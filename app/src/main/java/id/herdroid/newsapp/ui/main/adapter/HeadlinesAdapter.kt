package id.herdroid.newsapp.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.herdroid.newsapp.R
import id.herdroid.newsapp.data.model.news.DataNews
import id.herdroid.newsapp.databinding.AdapterHeadlineBinding
import id.herdroid.newsapp.utils.Utils

class HeadlinesAdapter (
    private val showDetail: (DataNews) -> Unit
) : RecyclerView.Adapter<HeadlinesAdapter.ViewHolder>() {

    private var data = ArrayList<DataNews>()

    fun setData(itemList: List<DataNews>) {
        if (itemList.isNullOrEmpty()) return
        data.clear()
        data.addAll(itemList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val item = data[position]
            if (item.author.isNullOrEmpty()) {
                tvNameSource.text = item.modelSource?.name
            } else {
                tvNameSource.text = item.author + " \u2022 " + item.modelSource?.name
            }

            tvTitle.text = item.title
            if(item.publishedAt != null){
                val dateTime = item.publishedAt?.substring(0, 19)
                tvDateTime.text = Utils.dateFormat(dateTime)
                tvTime.text = Utils.dateTimeHourAgo(dateTime)
            }

            if(item.urlToImage != null){
                imgThumbnail.load(item.urlToImage){
                    placeholder(R.drawable.bg_placeholder)
                    error(R.drawable.bg_placeholder)
                }
            } else {
                imgThumbnail.load(R.drawable.bg_placeholder)
            }
            root.setOnClickListener {
                showDetail(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterHeadlineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterHeadlineBinding) : RecyclerView.ViewHolder(view.root)

}