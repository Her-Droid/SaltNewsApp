package id.herdroid.newsapp.ui.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import id.herdroid.newsapp.data.model.news.DataNews
import id.herdroid.newsapp.databinding.FragmentDetailBinding


@AndroidEntryPoint
class WebViewFragment : BottomSheetDialogFragment() {

    private val binding : FragmentDetailBinding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
    }

    private val data : DataNews? by lazy {
        arguments?.getParcelable("data")
    }

    private lateinit var behavior: BottomSheetBehavior<*>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWebView()
    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(){
        with(binding){
            webView.settings.javaScriptEnabled = true

            progressBar.progress = 0

            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    val url = request?.url.toString()
                    view?.loadUrl(url)
                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun onPageStarted(view: WebView?, urlStart: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, urlStart, favicon)
                }

                override fun onPageFinished(view: WebView?, urlPage: String?) {
                    progressBar.visibility = View.GONE
                    super.onPageFinished(view, urlPage)
                }
            }
            data?.url?.let { webView.loadUrl(it) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}