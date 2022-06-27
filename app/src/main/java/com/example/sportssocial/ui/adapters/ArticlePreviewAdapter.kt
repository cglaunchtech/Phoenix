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
import java.lang.Exception

class ArticlePreviewAdapter(val context: Context, val dataSource: ArrayList<NewsArticle>): BaseAdapter() {
    //inflater
    val inflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val holder: ViewHolder
        view = inflater.inflate(R.layout.layout_article_preview, parent, false)

        holder = ViewHolder()
        holder.imageView = view.findViewById(R.id.image_view)
        holder.title = view.findViewById(R.id.title_text)
        holder.publishedAt = view.findViewById(R.id.published_at)
        holder.source = view.findViewById(R.id.source)
        holder.description = view.findViewById(R.id.description_text)
        holder.articleText = view.findViewById(R.id.article_text)
        holder.articleUrl = view.findViewById(R.id.article_url)
//
//        view.tag = holder
//
        val article = getItem(position) as NewsArticle
        try {
            Glide.with(context).load(article.urlToImage).into(holder.imageView)
        } catch (e: Exception) {
            //TODO: Timber log null pointer exception for article image
        }
        holder.apply {
            try {
            source.text = article.source
                } catch (e: Exception){
                    source.text = ""
                //TODO: Tiber log null pointer exception for image source
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
        lateinit var publishedAt: TextView
        lateinit var source: TextView
        lateinit var description: TextView
        lateinit var articleText: TextView
        lateinit var articleUrl: TextView
    }
}









// : RecyclerView.Adapter<ArticlePreviewAdapter.ArticleViewHolder>() {
//
//    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
//
//    // differCallback ensures that view is updated only if there's new data
//    private val differCallback = object : DiffUtil.ItemCallback<NewsArticle>() {
//        override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
//            return oldItem.url == newItem.url
//        }
//
//        override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    val differ = AsyncListDiffer(this, differCallback)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
//        return ArticleViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.activity_article_preview,
//                parent,
//                false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
//
//        val article = differ.currentList[position]
//        holder.itemView.apply {
//            Glide.with(this).load(article.urlToImage).into(imageView)
//            try {
//            source.text = article.source?.name
//                } catch (e: Exception){
//                    source.text = ""
//                }
//            title_text.text = article.title
//            description_text.text = article.description
//            article_text.text = article.content
//            article_url.text = article.url
//
//            setOnClickListener{
//                onItemClickListener?.let { it(article)}
//            }
//        }
//    }
//
//    private var onItemClickListener: ((NewsArticle) -> Unit)? = null
//
//    fun setOnItemClickListener(listener: (NewsArticle) -> Unit) {
//        onItemClickListener = listener
//    }
//
//    override fun getItemCount(): Int {
//        return differ.currentList.size
//    }
//}