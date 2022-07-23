package com.lazday.news.data.news

import com.lazday.news.BuildConfig
import com.lazday.news.core.network.ApiClient
import org.koin.dsl.module

val repositoryModule = module {
    factory { NewsRepository(get()) }
}

class NewsRepository(
    private val api: ApiClient
) {
    suspend fun fetch(
        category: String,
        query: String,
        page: Int
    ): NewsModel {
        return api.fetchNews(
            BuildConfig.API_KEY,
            "id",
            category,
            query,
            page
        )
    }
}