package com.rubylichtenstein.outconnect.app

import android.app.Activity
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.rubylichtenstein.outconnect.notifications.di.notificationsModule
import org.koin.android.ext.android.startKoin
import org.koin.log.EmptyLogger
import timber.log.Timber


/**
 * Created by rl98880 on 25/06/2018.
 */
class MyApplication : Application() {
    private var activeActivity: Activity? = null

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin(
            this,
            listOf(
                notificationsModule()
            ),
            logger = EmptyLogger()
        )

        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "default_channel",
                "default_channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            mNotificationManager.createNotificationChannel(channel)
        }
    }

}
