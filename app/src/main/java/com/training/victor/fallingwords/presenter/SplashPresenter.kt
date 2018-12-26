package com.training.victor.fallingwords.presenter

import com.training.victor.fallingwords.data.DataManager
import com.training.victor.fallingwords.utils.getErrorMessage
import com.training.victor.fallingwords.utils.myTrace
import io.reactivex.Scheduler
import javax.inject.Inject

class SplashPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                          private val subscriberSchedulers: Scheduler,
                                          private val dataManager: DataManager) : Presenter<SplashPresenter.SplashView>() {



    interface SplashView {
        fun onDataBasePrepared()
        fun onDataBaseError(errorMessage: String)
        fun onDataBaseItemCountRetrieved(count: Int)
        fun onDataBaseItemCountError(errorMessage: String)
    }

    fun loadAllData() {
        compositeDisposable.add(dataManager.loadDataFromJsonAndFeedDataBase()
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                myTrace("loadAllData - all ok")
                view?.onDataBasePrepared()
            }, {
                myTrace("loadAllData - error!")
                it.printStackTrace()
                view?.onDataBaseError(it.getErrorMessage())
            }))
    }

    fun getDataBaseItemsCount() {
        compositeDisposable.add(dataManager.getDataBaseItemCount()
            .observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                view?.onDataBaseItemCountRetrieved(it)
            }, {
                view?.onDataBaseItemCountError(it.getErrorMessage())
            }))
    }

}