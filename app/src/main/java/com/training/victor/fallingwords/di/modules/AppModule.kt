package com.training.victor.fallingwords.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule(private val application: Context) {

    @Provides
    @Singleton
    open fun provideApplicationContext(): Context = application
}