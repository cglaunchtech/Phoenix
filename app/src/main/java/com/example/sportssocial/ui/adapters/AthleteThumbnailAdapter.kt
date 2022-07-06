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
import com.example.sportssocial.data.model.db.entities.Athlete
import timber.log.Timber
import java.lang.Exception

class AthleteThumbnailAdapter (
    private var context: Context,
    private var athleteList: List<Athlete>,
    private var onCardClick: (position: Int) -> Unit
) : RecyclerView.Adapter<AthleteThumbnailAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_screen_card, parent, false)

        return ViewHolder(viewInflater, onCardClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            firstName.text = athleteList[position].first
            lastName.text = athleteList[position].last
        }

        try {
            Glide.with(context).load(athleteList[position].profilePhoto).into(holder.imageView)
        } catch (e: Exception) {
            Timber.e("ArticleThumbnailAdapter: Line 43. Exception: $e")
            holder.imageView.setImageResource(R.drawable.ic_baseline_person_24)
        }
    }

    override fun getItemCount(): Int {
        return athleteList.count()
    }

    class ViewHolder(view: View, private val onCardClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        var firstName: TextView = view.findViewById(R.id.editFirstName1)
        var lastName: TextView = view.findViewById(R.id.lastName1)
        var imageView: ImageView = view.findViewById(R.id.image_view)

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            onCardClick(position)
        }
    }

    fun setItems(itemList: List<Athlete>) {
        this.athleteList = itemList
        notifyDataSetChanged()
    }
}