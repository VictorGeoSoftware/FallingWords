package com.training.victor.fallingwords.di.modules

import com.training.victor.fallingwords.data.DataManager
import com.training.victor.fallingwords.di.ViewScope
import com.training.victor.fallingwords.presenter.MainPresenter
import com.training.victor.fallingwords.presenter.SplashPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class PresenterModule {
    companion object {
        const val ANDROID_SCHEDULER = "ANDROID_SCHEDULER"
        const val TASK_SCHEDULER = "TASK_SCHEDULER"
    }


    @Provides
    @Named(ANDROID_SCHEDULER)
    fun provideAndroidScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named(TASK_SCHEDULER)
    fun provideTaskScheduler(): Scheduler = Schedulers.newThread()


    @Provides
    @ViewScope
    fun provideSplashPresenter(@Named(ANDROID_SCHEDULER) androidScheduler:Scheduler,
                               @Named(TASK_SCHEDULER) taskScheduler:Scheduler,
                               dataManager: DataManager) = SplashPresenter(androidScheduler, taskScheduler, dataManager)

    @Provides
    @ViewScope
    fun provideMainPresenter(@Named(ANDROID_SCHEDULER) androidScheduler:Scheduler,
                               @Named(TASK_SCHEDULER) taskScheduler:Scheduler,
                               dataManager: DataManager) = MainPresenter(androidScheduler, taskScheduler, dataManager)
}