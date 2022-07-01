package com.example.sportssocial.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.NewsArticle
import java.lang.Exception

class ArticleThumbnailAdapter(
    private var context: Context,
    private var articleList: List<NewsArticle>,
    private var onCardClick: (position: Int) -> Unit
) : RecyclerView.Adapter<ArticleThumbnailAdapter.ViewHolder>() {

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate a view and return it
        var viewInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_layout, parent, false)

        return ViewHolder(viewInflater, onCardClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // add current item to the holder
        val article = articleList[position]
        holder.source.text = article.source
        holder.title.text = article.title

        try {
            Glide.with(context).load(article.urlToImage).into(holder.imageView)
        } catch (e: Exception) {
            //TODO: Timber log null pointer exception for article image
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    class ViewHolder(view: View, private val onCardClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        var title: TextView = view.findViewById(R.id.item_title)
        var source: TextView = view.findViewById(R.id.item_tag)
        var imageView: ImageView = view.findViewById(R.id.image_view)

        override fun onClick(v: View?) {
           val position = absoluteAdapterPosition
            onCardClick(position)
        }
    }

    fun setItems(itemList: List<NewsArticle>) {
        this.articleList = itemList
        notifyDataSetChanged()
    }
}