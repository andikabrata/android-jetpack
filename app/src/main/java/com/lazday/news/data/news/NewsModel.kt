package com.lazday.news.data.news

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class NewsModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleModel>
)

/**
 * @Entity inisialisasi untuk membuat database Room
 */
@Entity(tableName = "tableArticle")
data class ArticleModel(
    @PrimaryKey(autoGenerate = false)
    val source: SourceModel?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
) : Serializable

data class SourceModel(
    val id: String?,
    val name: String?
) : Serializable
