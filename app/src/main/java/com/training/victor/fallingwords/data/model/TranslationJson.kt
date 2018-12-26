package com.training.victor.fallingwords.data.model

import com.google.gson.annotations.SerializedName

data class TranslationJson(@SerializedName("text_eng") val key: String,
                           @SerializedName("text_spa") val translation: String)