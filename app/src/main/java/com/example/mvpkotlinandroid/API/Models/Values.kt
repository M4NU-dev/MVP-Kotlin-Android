package com.example.mvpkotlinandroid.API.Models

import java.io.Serializable

class Values(
    val value: String,
    val matchLevel: String,
    val fullyHighlighted: Boolean,
    val matchedWords: List<String>
): Serializable {
}