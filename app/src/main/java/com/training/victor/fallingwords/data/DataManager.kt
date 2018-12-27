package com.training.victor.fallingwords.data

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.training.victor.fallingwords.BuildConfig
import com.training.victor.fallingwords.data.model.TranslationJson
import com.training.victor.fallingwords.data.model.TranslationViewModel
import com.training.victor.fallingwords.data.room.TranslationDataBase
import com.training.victor.fallingwords.data.room.TranslationDto
import com.training.victor.fallingwords.utils.myTrace
import io.reactivex.Completable
import io.reactivex.Observable
import java.io.IOException
import java.nio.charset.Charset
import kotlin.random.Random

class DataManager(private val assetManager: AssetManager, private val translationDataBase: TranslationDataBase) {

    fun checkDataBaseStatus(): Observable<Boolean> {
        return Observable.fromCallable {
            val items = getDataBaseItemCount()
            myTrace("checkDataBaseStatus - items :: $items")
            items > 0
        }
    }

    fun loadDataFromJsonAndFeedDataBase(): Completable {
        return Completable.fromObservable(convertJsonToList()
            .map { translationJsonList ->
                myTrace("loadDataFromJsonAndFeedDataBase - map1 :: $translationJsonList")
                translationJsonList.map {
                    myTrace("loadDataFromJsonAndFeedDataBase - map2 :: $it")
                    translationDataBase.postDato().addTranslation(TranslationDto(it.key, it.translation))
                }
            }.map {
                myTrace("loadDataFromJsonAndFeedDataBase - items :: ${getDataBaseItemCount()}")
            })
    }

    private fun convertJsonToList(): Observable<ArrayList<TranslationJson>> {
        return loadJsonFileContent().flatMap { json ->
            val translationJsonList: ArrayList<TranslationJson>
                    = Gson().fromJson(json, object : TypeToken<List<TranslationJson>>() { }.type)
            Observable.just(translationJsonList)
        }
    }

    private fun loadJsonFileContent(): Observable<String> {
        return Observable.create<String> {
            try {
                val inputStream = assetManager.open(BuildConfig.JSON_FILE_NAME)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer, 0, size)
                inputStream.close()
                val content = String(buffer, Charset.forName("UTF-8"))
                it.onNext(content)
                it.onComplete()
            } catch (e: IOException) {
                e.printStackTrace()
                it.onError(e)
            }
        }
    }

    fun getDataBaseItemCount(): Int {
        return translationDataBase.postDato().getItemCount()
    }

    fun getNewKeyWord(): Observable<TranslationViewModel> {
        return Observable.fromCallable {
            getDataBaseItemCount()
        }.flatMap { randomId ->
            val randomInt = Random.nextInt(0, randomId)
            translationDataBase.postDato().getTranslationById(randomInt).flatMapObservable { translation ->
                Observable.just(TranslationViewModel(translation.key, translation.translation))
            }
        }
    }
}