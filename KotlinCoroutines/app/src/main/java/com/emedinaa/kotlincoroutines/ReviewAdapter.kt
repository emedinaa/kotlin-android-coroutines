package com.emedinaa.kotlincoroutines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_review.view.*

class ReviewAdapter(private var reviews:List<Review>):RecyclerView.Adapter<ReviewAdapter.ReviewHolder>(){

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

    fun update(data:List<Review>){
        reviews= data
        notifyDataSetChanged()
    }

    inner class ReviewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        fun bind(entity:Review){
            view.textViewAuthor.text = entity.author
            view.textViewDesc.text = entity.comment
        }
    }
}