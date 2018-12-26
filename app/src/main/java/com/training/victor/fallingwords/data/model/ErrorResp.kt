package com.training.victor.fallingwords.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResp (@SerializedName("error") val error: String,
                      @SerializedName("error_description") val errorDescription: String)