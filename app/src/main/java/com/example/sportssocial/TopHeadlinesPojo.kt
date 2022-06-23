package com.example.sportssociallayouts

data class TopHeadlinesPojo (

    val status: String?,
    val totalResults: Int?,
    val articles: ArrayList<Articles>?
)

data class Articles (
    val source: Source?,
    val author: String?,
    val title:String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt : String?,
    val content: String?
)

data class Source(
    val id: String?,
    val name: String?
)
