package id.herdroid.newsapp.ui.bookmarked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.herdroid.newsapp.constants.AppConstants
import id.herdroid.newsapp.data.model.Article
import id.herdroid.newsapp.data.util.Resource
import id.herdroid.newsapp.data.util.Status
import id.herdroid.newsapp.extension.gone
import id.herdroid.newsapp.extension.md5Hash
import id.herdroid.newsapp.extension.showMessage
import id.herdroid.newsapp.extension.visible
import id.herdroid.newsapp.listeners.OnItemClickListener
import id.herdroid.newsapp.ui.base.BaseFragment
import id.herdroid.newsapp.ui.newsList.NewsAdapter
import id.herdroid.newsapp.ui.newsList.NewsViewModel
import com.pixplicity.easyprefs.library.Prefs
import id.herdroid.newsapp.R
import id.herdroid.newsapp.databinding.FragmentBookmarkedBinding
import javax.inject.Inject

class BookmarkedFragment: BaseFragment<NewsViewModel, FragmentBookmarkedBinding>(NewsViewModel::class.java) {

    @Inject
    internal lateinit var adapter: NewsAdapter

    override val layoutRes: Int
        get() = R.layout.fragment_bookmarked

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding.rvMain.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }

        deleteSwipeHandling()

        return dataBinding.root
    }

    private fun deleteSwipeHandling() {
        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = dataBinding.rvMain.adapter as NewsAdapter
                adapter.removeAt(viewHolder.adapterPosition) { article ->
                    deleteBookMark(article)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(dataBinding.rvMain)
    }

    private fun deleteBookMark(article: Article) {
        viewModel.deleteBookmarkedArticle(article)
        deleteFromPreference(article)
    }

    private fun deleteFromPreference(article: Article) {

        val list: Set<String> = Prefs.getOrderedStringSet(AppConstants.BOOKMARKED_PREF_KEY, setOf())

        val hashSet = hashSetOf<String>()

        list.forEach { e -> hashSet.add(e) }

        article.title?.md5Hash()?.let { hashSet.remove(it) }

        Prefs.putOrderedStringSet(AppConstants.BOOKMARKED_PREF_KEY, hashSet)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    private fun setup(view: View) {

        navController = Navigation.findNavController(view)

        // Item click listener
        adapter.setOnItemClickListener(onNewsItemClickListener())

        // Observe
        viewModel.bookmarkedResponse.observe(viewLifecycleOwner) { state -> handleState(state) }

        // Trigger call
        viewModel.getAllBookmarkedArticles()
    }

    private fun handleState(state: Resource<List<Article>>) {
        when (state.status) {
            Status.SUCCESS -> loadNews(state.data)
            Status.LOADING -> showLoading()
            Status.ERROR -> showError(state.message ?: "Something went wrong ¯\\_(ツ)_/¯")
        }
    }

    private fun onNewsItemClickListener() = object : OnItemClickListener {
        override fun onItemClick(item: Article?) {
            item?.let {
                val bundle = bundleOf("ARTICLE" to it)
                navController.navigate(
                    R.id.action_bookmarkedFragment_to_newsDetailsFragment,
                    bundle)
            }
        }
    }

    private fun showError(errorMessage: String) {
        dataBinding.loadingIndicator.gone()
        showMessage(errorMessage)
    }

    private fun showLoading() = dataBinding.loadingIndicator.visible()

    private fun loadNews(data: List<Article>?) {
        dataBinding.loadingIndicator.gone()
        data?.let { adapter.setArticlesList(it) }
    }
}