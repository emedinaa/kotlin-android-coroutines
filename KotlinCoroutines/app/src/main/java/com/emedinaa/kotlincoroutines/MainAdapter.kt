package com.emedinaa.kotlincoroutines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_course.view.*

class MainAdapter(private var courses:List<Course>, val itemAction:(item:Course)->Unit):RecyclerView.Adapter<MainAdapter.CourseViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(vh: CourseViewHolder, position: Int) {
        vh.bind(courses[position])
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    fun update(data:List<Course>){
        courses= data
        notifyDataSetChanged()
    }

    inner class CourseViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        fun bind(entity:Course){
            view.textViewName.text = entity.title
            view.textViewModality.text = entity.modality
            view.textViewDate.text = "Fecha de inicio: ".plus(entity.date)
            view.textViewDesc.text = entity.desc
            Glide.with(view.imageView.context).load(entity.photo).into(view.imageView)
            view.setOnClickListener {
                itemAction(entity)
            }
        }
    }
}