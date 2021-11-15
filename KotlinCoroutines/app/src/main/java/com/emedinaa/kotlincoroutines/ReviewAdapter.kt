package com.emedinaa.kotlincoroutines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Eduardo Medina
 */
class ReviewAdapter(private var reviews: List<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ReviewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_review, parent, false)
        return ReviewHolder(view)
    }

    override fun onBindViewHolder(vh: ReviewHolder, position: Int) {
        vh.bind(reviews[position])
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    fun update(data: List<Review>) {
        reviews = data
        notifyDataSetChanged()
    }

    inner class ReviewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textViewAuthor: TextView = view.findViewById(R.id.textViewAuthor)
        private val textViewDesc: TextView = view.findViewById(R.id.textViewDesc)
        fun bind(entity: Review) {
            textViewAuthor.text = entity.author
            textViewDesc.text = entity.comment
        }
    }
}