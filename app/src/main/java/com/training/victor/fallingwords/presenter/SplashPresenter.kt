package com.training.victor.fallingwords.presenter

import com.training.victor.fallingwords.data.DataManager
import io.reactivex.Scheduler
import javax.inject.Inject

class SplashPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                          private val subscriberSchedulers: Scheduler,
                                          private val dataManager: DataManager) : Presenter<SplashPresenter.SplashView>() {



    interface SplashView {

    }

    fun loadAllData() {
        dataManager.loadDataFromJsonAndFeedDataBase()
    }

}