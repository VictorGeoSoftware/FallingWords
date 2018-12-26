package com.training.victor.fallingwords.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.training.victor.fallingwords.data.room.TranslationDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DataBaseModule {

    @Provides
    @Singleton
    open fun provideAppDataBase(context: Context): TranslationDataBase
            = Room.databaseBuilder(context, TranslationDataBase::class.java, "app_db")
        .fallbackToDestructiveMigration()
        .build()
}