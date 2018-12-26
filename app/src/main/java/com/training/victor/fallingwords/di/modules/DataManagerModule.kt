package com.training.victor.fallingwords.di.modules

import android.content.res.AssetManager
import com.training.victor.fallingwords.data.DataManager
import com.training.victor.fallingwords.data.room.TranslationDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DataManagerModule {

    @Provides
    @Singleton
    open fun provideDataManager(assetManager: AssetManager, translationDataBase: TranslationDataBase): DataManager
            = DataManager(assetManager, translationDataBase)
}