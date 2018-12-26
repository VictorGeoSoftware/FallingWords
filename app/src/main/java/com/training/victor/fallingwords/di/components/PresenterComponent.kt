package com.training.victor.fallingwords.di.components

import com.training.victor.fallingwords.di.modules.PresenterModule
import com.training.victor.fallingwords.di.ViewScope
import com.training.victor.fallingwords.ui.MainActivity
import com.training.victor.fallingwords.ui.SplashActivity
import dagger.Subcomponent

@ViewScope
@Subcomponent(modules = [PresenterModule::class])
interface PresenterComponent {
    fun inject(target: SplashActivity)
    fun inject(target: MainActivity)
}