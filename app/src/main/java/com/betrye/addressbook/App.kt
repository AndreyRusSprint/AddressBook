package com.betrye.addressbook

import android.app.Application
import com.betrye.addressbook.di.dataModule
import com.betrye.addressbook.di.domainModule
import com.betrye.addressbook.di.fragmentModule
import com.betrye.addressbook.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.KoinExperimentalAPI
import org.koin.core.context.startKoin

@KoinExperimentalAPI
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)
            fragmentFactory()
            modules(listOf(
                domainModule,
                dataModule,
                viewModelModule,
                fragmentModule,
            ))
        }
    }
}
