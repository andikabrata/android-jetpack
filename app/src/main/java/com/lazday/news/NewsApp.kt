package com.lazday.news

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.lazday.news.core.network.networkModule
import com.lazday.news.data.news.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.e("run base application")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger()
            androidContext(this@NewsApp)
            modules(
                networkModule,
                repositoryModule
            )
        }
    }
}