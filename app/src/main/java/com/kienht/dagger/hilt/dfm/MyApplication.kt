package com.kienht.dagger.hilt.dfm

import android.app.Application
import com.kienht.dagger.hilt.core.UserModel
import com.kienht.dagger.hilt.core.di.UserModelSingletonQualifier
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

/**
 * @author kienht
 * @since 15/09/2020
 */
@HiltAndroidApp
class MyApplication : Application() {

    @Inject
    @UserModelSingletonQualifier
    lateinit var singletonUserModel: UserModel

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        singletonUserModel.value += "MyApplication"
    }

}