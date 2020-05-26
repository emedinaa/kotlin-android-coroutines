package com.emedinaa.kotlincoroutines

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Course(val id:Int, @SerializedName("name") val title:String, @SerializedName("mode") val modality:String,
                  @SerializedName("startdate") val date:String, val desc:String, @SerializedName("image") val photo:String):Parcelable

@Parcelize
data class Review(val id:Int, val author:String, val comment:String, val rate:Int):Parcelable
