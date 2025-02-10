package com.fetchrewards

import android.app.Application
import com.fetchrewards.di.modules.ApiModule
import com.fetchrewards.di.modules.NetworkModule
import com.fetchrewards.di.modules.RepositoryModule
import com.fetchrewards.di.modules.UseCaseModule
import com.fetchrewards.di.modules.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(loadKoinModules())
        }
    }

    private fun loadKoinModules() =
        listOf(
            ApiModule.modules,
            NetworkModule.modules,
            RepositoryModule.modules,
            ViewModelModule.modules,
            UseCaseModule.modules,
        )
}
