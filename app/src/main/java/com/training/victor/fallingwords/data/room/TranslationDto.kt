package com.training.victor.fallingwords.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.training.victor.fallingwords.data.Constants.Companion.TRANSLATION_DB

@Entity(tableName = TRANSLATION_DB)
data class TranslationDto(@SerializedName("key") val key: String,
                     @SerializedName("translation") val translation: String) {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: Int = 0
}