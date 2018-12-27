package com.training.victor.fallingwords.presenter

import com.training.victor.fallingwords.data.DataManager
import com.training.victor.fallingwords.utils.getErrorMessage
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject

class SplashPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                          private val subscriberSchedulers: Scheduler,
                                          private val dataManager: DataManager): Presenter<SplashPresenter.SplashView>() {

    interface SplashView {
        fun onDataBasePrepared()
        fun onDataBaseError(errorMessage: String)
    }

    fun loadAllData() {
        compositeDisposable.add(dataManager.checkDataBaseStatus()
            .flatMapCompletable { dbReady ->
                if (dbReady) {
                    Completable.complete()
                } else {
                    dataManager.loadDataFromJsonAndFeedDataBase()
                }
            }
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                view?.onDataBasePrepared()
            }, {
                it.printStackTrace()
                view?.onDataBaseError(it.getErrorMessage())
            }))
    }

}