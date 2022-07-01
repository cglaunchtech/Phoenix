package com.example.sportssocial.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.example.sportssocial.R

import com.example.sportssocial.data.model.db.entities.NewsArticle
import timber.log.Timber
import java.lang.Exception

class ArticlePreviewAdapter(val context: Context, var articleList: ArrayList<NewsArticle>): BaseAdapter() {
    //inflater
    val inflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return articleList.size
    }

    override fun getItem(position: Int): Any {
        return articleList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val holder: ViewHolder
        view = inflater.inflate(R.layout.article_layout, parent, false)

        holder = ViewHolder()
        holder.imageView = view.findViewById(R.id.image_view)
        holder.title = view.findViewById(R.id.text_view_title)
        holder.author = view.findViewById(R.id.text_view_author)
        holder.publishedAt = view.findViewById(R.id.text_view_published_date)
        holder.source = view.findViewById(R.id.text_view_source)
        holder.description = view.findViewById(R.id.text_view_description)
        holder.articleText = view.findViewById(R.id.text_view_content)
        holder.articleUrl = view.findViewById(R.id.text_view_article_url)

        val article = getItem(position) as NewsArticle
        try {
            Glide.with(context).load(article.urlToImage).into(holder.imageView)
        } catch (e: Exception) {
            Timber.e("ArticlePreviewAdapter: Line 56. Exception: $e")
        }
        holder.apply {
            try {
            source.text = article.source
                } catch (e: Exception){
                    source.text = ""
                Timber.e("ArticlePreviewAdapter: Line 64. Exception: $e")
                }
            try {
                author.text = article.author
            } catch (e: Exception){
                author.text = ""
                Timber.e("ArticlePreviewAdapter: Line 69. Exception: $e")
            }
            title.text = article.title
            publishedAt.text = article.publishedAt
            description.text = article.description
            articleText.text = article.content
            articleUrl.text = article.url
    }

        return view
    }

    class ViewHolder {
        lateinit var imageView: ImageView
        lateinit var title: TextView
        lateinit var author: TextView
        lateinit var publishedAt: TextView
        lateinit var source: TextView
        lateinit var description: TextView
        lateinit var articleText: TextView
        lateinit var articleUrl: TextView
    }

    fun setItems(itemList: List<NewsArticle>){
        this.articleList = itemList as ArrayList<NewsArticle>
        notifyDataSetChanged()
    }
}