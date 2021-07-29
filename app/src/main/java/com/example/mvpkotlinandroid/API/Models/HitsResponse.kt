package com.example.mvpkotlinandroid.API.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class HitsResponse(
    @SerializedName("hits")
    val articulos: List<Article>,
    val nbHits: Int,
    val page: Int,
    val nbPages: Int,
    val hitsPerPage: Int,
    val exhaustiveNbHits: Boolean,
    val query: String,
    val params: String,
    val processingTimeMS: Int
): Serializable {

}