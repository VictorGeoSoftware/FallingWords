package com.training.victor.fallingwords.di

import android.content.Context
import com.training.victor.fallingwords.di.modules.AppModule

class TestAppModule(private val context: Context): AppModule(context) {
    override fun provideApplicationContext(): Context {
        return context
    }
}