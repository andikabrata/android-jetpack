package com.lazday.news.core.network

import com.lazday.news.data.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("")
    suspend fun fetchNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("q") query: String,
        @Query("page") page: Int, /// max page from totalSize = 20 /page
    ): NewsModel
}