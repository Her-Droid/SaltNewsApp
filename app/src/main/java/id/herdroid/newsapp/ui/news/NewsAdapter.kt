package id.herdroid.newsapp.ui.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.herdroid.newsapp.R
import id.herdroid.newsapp.data.model.news.DataNews
import id.herdroid.newsapp.databinding.AdapterNewsBinding
import id.herdroid.newsapp.utils.Utils.dateFormat
import id.herdroid.newsapp.utils.Utils.dateTimeHourAgo

class NewsAdapter (
        private val showDetail: (DataNews) -> Unit
) : PagedListAdapter<DataNews, NewsAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            AdapterNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val item = getItem(position)
            if (item?.author.isNullOrEmpty()) {
                tvNameSource.text = item?.modelSource?.name
            } else {
                tvNameSource.text = item?.author + " \u2022 " + item?.modelSource?.name
            }

            tvTitle.text = item?.title

            if(item?.publishedAt != null){
                val dateTime = item.publishedAt?.substring(0, 19)
                tvDateTime.text = dateFormat(dateTime)
                tvTime.text = dateTimeHourAgo(dateTime)
            }

            if(item?.urlToImage!= null){
                imgThumbnail.load(item.urlToImage){
                    placeholder(R.drawable.bg_placeholder)
                    error(R.drawable.bg_placeholder)
                }
            } else {
                imgThumbnail.load(R.drawable.bg_placeholder)
            }
            root.setOnClickListener {
                item?.let { it1 -> showDetail(it1) }
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<DataNews>(){
            override fun areContentsTheSame(oldItem: DataNews, newItem: DataNews): Boolean {
                return oldItem.modelSource?.id == newItem.modelSource?.id
            }

            override fun areItemsTheSame(oldItem: DataNews, newItem: DataNews): Boolean {
                return oldItem == newItem
            }
        }
    }
    class ViewHolder(val view: AdapterNewsBinding) : RecyclerView.ViewHolder(view.root)
}