package com.training.victor.fallingwords.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.training.victor.fallingwords.R
import com.training.victor.fallingwords.presenter.SplashPresenter
import com.training.victor.fallingwords.utils.mainApplication
import com.training.victor.fallingwords.utils.myTrace
import com.training.victor.fallingwords.utils.showRequestErrorMessage
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashPresenter.SplashView {

    @Inject lateinit var splashPresenter: SplashPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mainApplication().createPresenterComponent().inject(this)

        splashPresenter.view = this
        splashPresenter.loadAllData()

    }

    override fun onDestroy() {
        super.onDestroy()
        mainApplication().releasePresenterComponent()
    }


    override fun onDataBasePrepared() {
        myTrace("onDataBasePrepared! :: ${splashPresenter.getDataBaseItemsCount()}")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun onDataBaseError(errorMessage: String) {
        myTrace("onDataBaseError!")
        showRequestErrorMessage(errorMessage)
    }

    override fun onDataBaseItemCountRetrieved(count: Int) {
        myTrace("onDataBaseItemCountRetrieved! :: $count")
    }

    override fun onDataBaseItemCountError(errorMessage: String) {
        myTrace("onDataBaseItemCountError! :: $errorMessage")
    }
}
