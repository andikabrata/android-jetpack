package com.lazday.news.core.db.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lazday.news.core.db.dao.NewsDao
import com.lazday.news.data.news.ArticleModel
import com.lazday.news.util.SourceConverter


@Database(
    entities = [ArticleModel::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(SourceConverter::class)
abstract class DatabaseClient : RoomDatabase() {
    abstract val newsDao: NewsDao
}