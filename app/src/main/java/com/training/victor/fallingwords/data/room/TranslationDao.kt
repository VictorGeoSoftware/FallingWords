package com.training.victor.fallingwords.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Maybe

@Dao
interface TranslationDao {

    @Insert(onConflict = REPLACE)
    fun addTranslation(translationDto: TranslationDto)

    @Query("SELECT * FROM TRADUCTION_DB WHERE `key` = :translationKey")
    fun getTranslation(translationKey: String): Maybe<TranslationDto>

    @Query("SELECT * FROM TRADUCTION_DB")
    fun getItemCount(): Int
}