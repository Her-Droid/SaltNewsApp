package id.herdroid.newsapp.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.herdroid.newsapp.data.model.news.DataNews
import id.herdroid.newsapp.data.model.source.DataSource
import id.herdroid.newsapp.data.state.NewsState
import id.herdroid.newsapp.databinding.ActivityNewsBinding
import id.herdroid.newsapp.ui.detail.DetailActivity

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private val binding : ActivityNewsBinding by lazy {
        ActivityNewsBinding.inflate(layoutInflater)
    }

    private val adapter : NewsAdapter by lazy {
        NewsAdapter { item -> detailNews(item)}
    }

    private val viewModel : NewsViewModel by viewModels()

    private val source : DataSource? by lazy {
        intent.getParcelableExtra("data")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupAdapter()
        setupViewModel()
    }

    private fun setupToolbar(){
        with(binding){
            tvTitle.text = source?.name
            imgBack.setOnClickListener{finish()}
        }
    }

    private fun setupAdapter(){
        with(binding){
            rvNews.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(applicationContext)
            }
        }
    }

    private fun setupViewModel(){
        viewModel.state.observe(this@NewsActivity){
            when(it){
                is NewsState.Loading -> {
                    setupLoading(true)
                }
                is NewsState.Result -> {
                    setupLoading(false)
                }
                is NewsState.Error -> {
                    setupError(it)
                }
            }
        }
        viewModel.data.observe(this@NewsActivity, adapter::submitList)
        viewModel.getNews(source?.id!!, this@NewsActivity)
    }

    private fun setupLoading(loading : Boolean){
        with(binding) {
            if (loading) {
                shNews.startShimmer()
                rvNews.visibility = View.INVISIBLE
                shNews.visibility = View.VISIBLE
            }else {
                rvNews.visibility = View.VISIBLE
                shNews.visibility = View.INVISIBLE
                shNews.stopShimmer()
            }
        }
    }


    private fun setupError(error : NewsState.Error){
        with(binding){
            setupLoading(false)
            if (error.error.message != null) Toast.makeText(
                    applicationContext,
                    error.error.message!!,
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun detailNews(item: DataNews?) {
        startActivity(Intent(this, DetailActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

}