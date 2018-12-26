package com.training.victor.fallingwords

import android.app.Application
import com.training.victor.fallingwords.di.components.AppComponent
import com.training.victor.fallingwords.di.components.DaggerAppComponent
import com.training.victor.fallingwords.di.components.PresenterComponent
import com.training.victor.fallingwords.di.modules.AppModule
import com.training.victor.fallingwords.di.modules.PresenterModule

class MainApplication: Application() {
    private val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this) ).build()
    }
    private var presenterComponent: PresenterComponent? = null


    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }


    fun createPresenterComponent(): PresenterComponent {
        return component.plus(PresenterModule())
    }

    fun releasePresenterComponent() {
        presenterComponent = null
    }
}