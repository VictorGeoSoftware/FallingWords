package com.training.victor.development.di.modules

import com.training.victor.fallingwords.data.DataManager
import com.training.victor.fallingwords.data.room.TranslationDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DataManagerModule {

    @Provides
    @Singleton
    open fun provideDataManager(translationDataBase: TranslationDataBase): DataManager = DataManager(translationDataBase)
}