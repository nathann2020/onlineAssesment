package com.workday.codingtask

import android.app.Application
import com.workday.codingtask.di.appModule
import org.koin.core.context.startKoin

/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}