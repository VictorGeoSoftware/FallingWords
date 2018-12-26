package com.training.victor.fallingwords

import android.support.test.InstrumentationRegistry
import com.training.victor.fallingwords.di.TestAppModule
import com.training.victor.fallingwords.di.TestDataBaseModule
import com.training.victor.fallingwords.di.components.AppComponent
import com.training.victor.fallingwords.di.modules.AppModule
import com.training.victor.fallingwords.di.modules.DataBaseModule
import com.training.victor.fallingwords.di.modules.DataManagerModule
import com.training.victor.fallingwords.test.SplashPresenterTest
import dagger.Component
import javax.inject.Singleton


abstract class ParentInstrumentalTest {
    open lateinit var testNetworkComponent: TestAppComponent



    @Singleton
    @Component(modules = [AppModule::class, DataBaseModule::class, DataManagerModule::class])
    interface TestAppComponent : AppComponent {
        fun inject(target: SplashPresenterTest)
    }

    open fun setUp() {
        testNetworkComponent = DaggerParentInstrumentalTest_TestAppComponent.builder()
            .appModule(TestAppModule(InstrumentationRegistry.getTargetContext()))
            .dataBaseModule(TestDataBaseModule())
            .build()
    }
}