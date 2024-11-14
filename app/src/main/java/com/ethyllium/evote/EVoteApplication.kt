package com.ethyllium.evote

import android.app.Application
import com.ethyllium.data.di.dataModule
import com.ethyllium.evote.di.viewModelModule
import com.ethyllium.root.OAuthClient
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.slf4j.LoggerFactory

class EVoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EVoteApplication)
            androidLogger()
            modules(dataModule, viewModelModule, oAuthModule)
        }
        Logger.addLogAdapter(AndroidLogAdapter(PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodCount(0)
            .methodOffset(7)
        .build()))
    }
}

val oAuthModule = module {
    single { OAuthClient(context = androidContext(), authService = get()) }
}