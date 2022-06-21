package com.example.coway.core

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.coway.di.networkModule
import com.example.coway.di.repositoryModule
import com.example.coway.di.useCaseModule
import com.example.coway.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class CowayApp : Application() {

    @SuppressLint("HardwareIds")
    override fun onCreate() {
        super.onCreate()
        mInstance = this

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@CowayApp)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

    fun getInstance(): CowayApp? {
        if (mInstance == null) {
            synchronized(CowayApp::class.java) { mInstance = CowayApp() }
        }
        return mInstance
    }

    companion object {

        private var mInstance: CowayApp? = null

        fun getContext(): Context? {
            return mInstance?.applicationContext
        }
    }
}