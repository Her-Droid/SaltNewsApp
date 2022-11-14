package id.herdroid.newsapp.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.webkit.WebViewFragment
import androidx.appcompat.app.AppCompatActivity
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import id.herdroid.newsapp.data.model.news.DataNews
import id.herdroid.newsapp.databinding.ActivityDetailBinding
import id.herdroid.newsapp.utils.Utils

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding : ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val data : DataNews? by lazy {
        intent.getParcelableExtra("data")
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            if (data != null){
                if(data?.title != null){
                    tvTitle.text = data?.title
                }
                if (data?.content != null){
                    tvContent.text = Html.fromHtml(data?.content)
                }
                if(data?.publishedAt != null){
                    tvDateTime.text = Utils.dateFormat(data?.publishedAt)
                }
                if (data?.author != null && data?.modelSource?.name != null) {
                    tvNameSource.text = data?.author + " \u2022 " + data?.modelSource?.name
                } else {
                    tvNameSource.text = "-"
                }

                if(data?.urlToImage != null){
                    imgThumbnail.load(data?.urlToImage)
                }

                imgBack.setOnClickListener { finish() }
                imgShare.setOnClickListener { shareLink(data) }

            }
        }
    }

    private fun shareLink(data: DataNews?){
        startActivity(Intent.createChooser(Intent(),"Share To:").also {
            it.action = Intent.ACTION_SEND
            it.putExtra(Intent.EXTRA_TEXT,data?.url)
            it.type="text/plain"
        })
    }
}