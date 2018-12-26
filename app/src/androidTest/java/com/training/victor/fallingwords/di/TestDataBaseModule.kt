package com.training.victor.fallingwords.di

import android.arch.persistence.room.Room
import android.content.Context
import com.training.victor.fallingwords.di.modules.DataBaseModule
import com.training.victor.fallingwords.data.room.TranslationDataBase

class TestDataBaseModule: DataBaseModule() {
    override fun provideAppDataBase(context: Context): TranslationDataBase {
        return Room.databaseBuilder(context, TranslationDataBase::class.java, "app_db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}