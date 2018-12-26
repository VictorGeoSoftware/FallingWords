package com.training.victor.fallingwords.utils

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.training.victor.fallingwords.MainApplication
import com.training.victor.fallingwords.R
import com.training.victor.fallingwords.data.model.ErrorResp
import retrofit2.adapter.rxjava2.HttpException

fun ViewGroup.inflate(layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun Context.getDpFromValue(value: Int): Int =
    Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), this.resources.displayMetrics))

fun Any.myTrace(message: String) {
    System.out.println("victor - ${this.javaClass.name} - $message")
}

fun Activity.showRequestErrorMessage(cause: String) {
    val errorMessage = String.format(this.getString(R.string.error_message), cause)
    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
}

fun Activity.mainApplication(): MainApplication = this.application as MainApplication

fun Throwable.getErrorMessage(): String {
    return if (this is HttpException) {
        val responseBody = this.response().errorBody()
        Gson().fromJson<ErrorResp>(responseBody?.string(), ErrorResp::class.java).errorDescription
    } else {
        this.localizedMessage
    }
}