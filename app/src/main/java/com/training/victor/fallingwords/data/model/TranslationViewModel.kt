package com.training.victor.fallingwords.data.model

import com.google.gson.annotations.SerializedName

data class TranslationViewModel(@SerializedName("key") val key: String,
                                @SerializedName("translation") val translation: String)