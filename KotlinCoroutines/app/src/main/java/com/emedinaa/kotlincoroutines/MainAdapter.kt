package com.emedinaa.kotlincoroutines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * @author Eduardo Medina
 */
class MainAdapter(private var courses: List<Course>, val itemAction: (item: Course) -> Unit) :
    RecyclerView.Adapter<MainAdapter.CourseViewHolder>() {

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

    fun update(data: List<Course>) {
        courses = data
        notifyDataSetChanged()
    }

    inner class CourseViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val textViewName: TextView = view.findViewById(R.id.textViewName)
        private val textViewModality: TextView = view.findViewById(R.id.textViewModality)
        private val textViewDate: TextView = view.findViewById(R.id.textViewDate)
        private val textViewDesc: TextView = view.findViewById(R.id.textViewDesc)
        private val imageView: ImageView = view.findViewById(R.id.imageView)
        fun bind(entity: Course) {
            textViewName.text = entity.title
            textViewModality.text = entity.modality
            textViewDate.text = "Fecha de inicio: ".plus(entity.date)
            textViewDesc.text = entity.desc
            Glide.with(imageView.context).load(entity.photo).into(imageView)
            view.setOnClickListener {
                itemAction(entity)
            }
        }
    }
}