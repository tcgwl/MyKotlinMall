package com.kotlin.base.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides

/**
 * Activity级别Module
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun providesActivity():Activity {
        return activity
    }
}