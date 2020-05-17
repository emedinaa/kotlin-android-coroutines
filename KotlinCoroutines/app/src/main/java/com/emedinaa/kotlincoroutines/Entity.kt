package com.emedinaa.kotlincoroutines

import com.google.gson.annotations.SerializedName

data class Course(val id:Int, @SerializedName("name") val title:String, @SerializedName("mode") val modality:String,
                  @SerializedName("startdate") val date:String, val desc:String, @SerializedName("image") val photo:String)