package com.training.victor.fallingwords.data

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.training.victor.fallingwords.data.room.TranslationDataBase
import com.training.victor.fallingwords.data.room.TranslationDto
import io.reactivex.Completable
import io.reactivex.Observable
import java.nio.charset.Charset

class DataManager(private val assetManager: AssetManager, private val translationDataBase: TranslationDataBase) {

    fun loadDataFromJsonAndFeedDataBase(): Completable {
        loadJsonFromAssets()
            .flatMap {
                val gson = Gson()
                gson.toJson(it, TranslationDto[].class)
            }
    }

    fun loadJsonFromAssets(): Observable<String> {
        return Observable.create {
            var jsonContent: String

            val inputStream = assetManager.open("words_v2.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonContent = String(buffer, Charset.forName("UTF-8"))

            it.onNext(jsonContent)
        }
    }
}