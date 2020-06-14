package com.sachtech.datingapp.data

import androidx.annotation.Keep

@Keep
data class UserPreference(
    val name:String,
    val minAge:String,
    val maxAge:String,
    var nationalty: List<String>,
    val origin: List<String>,
    val language: ArrayList<String>,
    val sect: ArrayList<String>,
    val marital: ArrayList<String>,
    val livingArrangement: ArrayList<String>,
    val height:String,
    val build: ArrayList<String>,
    val complexion:String,
    val eyeColor:String,
    val hairColor:String,
    val education:String,
    val profession:String,
    val marriagePlan:String,
    val livingArrangementAfterMarriage: String,
    val wears: String,
    val beard: String,
    val revert: String, val halal:String,
    val prays: String,
    val completedQuran:String
)