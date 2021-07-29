package com.example.mvpkotlinandroid.API.Models

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*
import java.time.format.DateTimeFormatter

class Article(
    val created_at: Date,
    val title: String,
    val url: String,
    val author: String,
    val points: Int,
    val story_text: String,
    val comment_text: String,
    val num_comments: Int,
    val story_id: Int,
    val story_title: String,
    val story_url: String,
    val parent_id: Int,
    val created_at_i: Int,
    val _tags: List<String>,
    val objectID: String,
    val _highlightResult: Result
): Serializable {
    @RequiresApi(Build.VERSION_CODES.O)

    fun getPublishedDate(): String {
        val result : String = created_at?.let {
            //if published_date is not null

            val currentDate = LocalDateTime.now(ZoneId.systemDefault())
            val publishDate = LocalDateTime.ofInstant(it.toInstant(), ZoneId.systemDefault())

            val minutesBetweenDates = ChronoUnit.MINUTES.between(publishDate, currentDate).toInt()
            val hoursBetweenDates = ChronoUnit.HOURS.between(publishDate, currentDate).toInt()
            val daysBetweenDates = ChronoUnit.DAYS.between(publishDate, currentDate).toInt()

            var dateText:String = ""
            if (hoursBetweenDates <= 24) {
                if (hoursBetweenDates == 1) {
                    dateText = "Hace $hoursBetweenDates horas"
                } else if (hoursBetweenDates > 1) {
                    dateText = "Hace $hoursBetweenDates horas"
                } else {
                    if (minutesBetweenDates <= 1) {
                        dateText = "Hace $minutesBetweenDates minutos"
                    } else {
                        dateText = "Hace $minutesBetweenDates minutos"
                    }
                }
            } else {
                if (daysBetweenDates <= 1) {
                    dateText = "Hace $daysBetweenDates día"
                } else {
                    dateText = "Hace $daysBetweenDates días"
                }
            }
            return@let dateText
        }
        return result
    }
}