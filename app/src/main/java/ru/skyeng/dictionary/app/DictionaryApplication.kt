package ru.skyeng.dictionary.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.skyeng.dictionary.di.modules.appModules

class DictionaryApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DictionaryApplication)
            modules(appModules)
        }
    }
}