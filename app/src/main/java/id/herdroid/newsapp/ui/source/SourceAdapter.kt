package id.herdroid.newsapp.ui.source

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.herdroid.newsapp.data.model.source.DataSource
import id.herdroid.newsapp.databinding.AdapterSourceBinding

class SourceAdapter (
    private val showDetail: (DataSource) -> Unit
) : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    private var data = ArrayList<DataSource>()

    fun setData(itemList: List<DataSource>?) {
        if (itemList.isNullOrEmpty()) return
        data.clear()
        data.addAll(itemList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val item = data[position]
            tvTitle.text = item.name
            tvDescription.text = item.description
            root.setOnClickListener {
                showDetail(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterSourceBinding) : RecyclerView.ViewHolder(view.root)

}