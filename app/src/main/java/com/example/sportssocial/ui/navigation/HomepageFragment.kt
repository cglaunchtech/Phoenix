package com.example.sportssocial.ui.navigation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.ui.adapters.ArticleThumbnailAdapter
import com.example.sportssocial.ui.view.ArticlePreview
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_profile.*
import timber.log.Timber

@AndroidEntryPoint
class HomepageFragment : Fragment() {

    var articleList = ArrayList<NewsArticle>()
    lateinit var articleThumbnailAdapter: ArticleThumbnailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_homepage, container, false)

        this.articleList.clear()
        val viewModel : ArticleViewModel by viewModels()
        var recyclerView: RecyclerView = view.findViewById(R.id.newsRecyclerView)

        viewModel.getAllArticles()
        viewModel.allArticles
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeBy(
                onNext = {
                    getArticles(it)
                },
                onComplete = {
                    articleThumbnailAdapter.setItems(articleList)
                },
                onError = {e -> Timber.e(e)}
            )

        articleThumbnailAdapter =
            ArticleThumbnailAdapter(requireContext(), articleList) { position -> onCardClick(position) }
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView.adapter = articleThumbnailAdapter
        //Page Elements
        //TODO: SET TOOLBAR TO HIDDEN IF VIEWER MODE == TRUE

        return view

    }
    private fun onCardClick(position: Int) {
        val myIntent = Intent(requireContext(), ArticlePreview::class.java)
    }

    private fun getArticles(articleList: List<NewsArticle>) {
        this.articleList.clear()
        this.articleList.addAll(articleList)
        articleThumbnailAdapter.notifyDataSetChanged()
    }
}



