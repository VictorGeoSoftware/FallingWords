package com.training.victor.fallingwords.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.training.victor.fallingwords.R
import com.training.victor.fallingwords.presenter.SplashPresenter
import com.training.victor.fallingwords.utils.mainApplication
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject lateinit var splashPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mainApplication().createPresenterComponent().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainApplication().releasePresenterComponent()
    }
}
