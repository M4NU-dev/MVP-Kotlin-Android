package com.example.mvpkotlinandroid.API.Models

import java.io.Serializable

class Result(
    val author: Values,
    val comment_text: Values,
    val story_title: Values,
    val story_url: Values
): Serializable {

}