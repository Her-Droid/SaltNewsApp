package id.herdroid.newsapp.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.herdroid.newsapp.data.model.news.DataNews
import id.herdroid.newsapp.data.state.NewsState
import id.herdroid.newsapp.databinding.ActivitySearchBinding
import id.herdroid.newsapp.ui.detail.DetailActivity
import id.herdroid.newsapp.ui.news.NewsAdapter

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val binding : ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    private val adapter : NewsAdapter by lazy {
        NewsAdapter { item -> detailNews(item)}
    }

    private val viewModel : SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupAdapter()
        setupViewModel()
    }

    private fun setupToolbar(){
        with(binding){
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
        viewModel.state.observe(this@SearchActivity){
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
        viewModel.data.observe(this@SearchActivity, adapter::submitList)
        viewModel.setupSearchNews(binding.etSearch, this)
    }

    private fun setupLoading(loading : Boolean){
        with(binding) {
            if (loading) {
                shNews.startShimmer()
                rvNews.visibility = View.INVISIBLE
                shNews.visibility = View.VISIBLE
                emptyLottie.visibility = View.GONE
            }else {
                shNews.stopShimmer()
                rvNews.visibility = View.VISIBLE
                shNews.visibility = View.INVISIBLE
                emptyLottie.visibility = View.GONE
            }
        }
    }

    private fun setupError(error : NewsState.Error){
        setupLoading(false)
        if (error.error.message != null) Toast.makeText(
            applicationContext,
            error.error.message!!,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun detailNews(item: DataNews) {
        startActivity(Intent(this, DetailActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

}