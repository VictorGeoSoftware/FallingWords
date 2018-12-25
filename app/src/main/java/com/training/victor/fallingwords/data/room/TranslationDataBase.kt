package com.training.victor.fallingwords.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [TranslationDto::class], version = 1)
abstract class TranslationDataBase: RoomDatabase() {
    abstract fun postDato(): TranslationDao
}