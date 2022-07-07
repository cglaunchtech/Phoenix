package com.example.sportssocial

import android.app.Application
import com.example.sportssocial.data.api.GlobalResponseOperator
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.skydoves.sandwich.SandwichInitializer
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //Sandwich initializer
        SandwichInitializer.sandwichOperator = GlobalResponseOperator<Any>(this)

        val TAG = "tag"
        val PRIORITY = "priority"

        //if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    FirebaseCrashlytics.getInstance().also{
                        it.setCustomKey(PRIORITY, priority )
                        tag?.let{_->it.setCustomKey(TAG, tag)}
                        it.log(message)
                        t?.let{e-> it.recordException(e)}
                        //ToDo: Log user account && phone type
                        //it.setUserId()
                    }.sendUnsentReports()
                }
            })


        //}
    }
}