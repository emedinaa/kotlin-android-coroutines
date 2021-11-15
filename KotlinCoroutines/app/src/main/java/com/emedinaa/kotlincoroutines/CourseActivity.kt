package com.emedinaa.kotlincoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.emedinaa.kotlincoroutines.data.RemoteDataSource
import com.emedinaa.kotlincoroutines.data.Repository
import com.emedinaa.kotlincoroutines.databinding.ActivityCourseBinding
import com.emedinaa.kotlincoroutines.executor.KAppExecutors

/**
 * @author Eduardo Medina
 */
class CourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseBinding

    private val adapter: ReviewAdapter by lazy {
        ReviewAdapter(emptyList())
    }

    private val repository: Repository by lazy {
        Repository(RemoteDataSource())
    }

    private val appExecutor: KAppExecutors by lazy {
        KAppExecutors()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.content.recyclerView.adapter = adapter

        populate()
        fetchReviews()
    }

    private fun populate() {
        intent?.extras?.getParcelable<Course>("COURSE")?.let {
            binding.collapToolbarLayout.title = it.nickname
            binding.content.textViewName.text = it.title
            binding.content.textViewModality.text = it.modality
            binding.content.textViewDate.text = "Fecha de inicio: ".plus(it.date)
            binding.content.textViewDesc.text = it.desc
            Glide.with(binding.imageView.context).load(it.photo).into(binding.imageView)
        }
    }

    private fun fetchReviews() {
        appExecutor.networkIO.execute {
            val result = repository.fetchReviews()
            appExecutor.mainThread.execute {
                adapter.update(result)
            }
        }
    }

}
