package com.training.victor.fallingwords.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.training.victor.fallingwords.R
import com.training.victor.fallingwords.utils.mainApplication

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainApplication().createPresenterComponent().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainApplication().releasePresenterComponent()
    }
}
