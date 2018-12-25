package com.training.victor.fallingwords.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.training.victor.fallingwords.data.Constants.Companion.TRADUCTION_DB

@Entity(tableName = TRADUCTION_DB)
class TranslationDto(@PrimaryKey
                     @SerializedName("key") val key: String,
                     @SerializedName("translation") val translation: String)