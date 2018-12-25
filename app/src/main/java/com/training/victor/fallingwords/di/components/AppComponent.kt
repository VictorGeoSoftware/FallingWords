package com.training.victor.fallingwords.di.components

import android.app.Application
import com.training.victor.fallingwords.di.modules.DataBaseModule
import com.training.victor.fallingwords.di.modules.DataManagerModule
import com.training.victor.fallingwords.di.modules.AppModule
import com.training.victor.fallingwords.di.modules.PresenterModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataBaseModule::class, DataManagerModule::class])
interface AppComponent {
    fun inject(application: Application)
    fun plus(presenterModule: PresenterModule): PresenterComponent
}