package com.lazday.news.core.db.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lazday.news.core.db.dao.NewsDao
import com.lazday.news.data.news.ArticleModel


@Database(
    entities = [ArticleModel::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseClient : RoomDatabase() {
    abstract val newsDao: NewsDao
}