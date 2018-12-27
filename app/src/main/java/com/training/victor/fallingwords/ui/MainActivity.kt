package com.training.victor.fallingwords.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.training.victor.fallingwords.R
import com.training.victor.fallingwords.data.Constants.Companion.END_DIALOG_TAG
import com.training.victor.fallingwords.data.model.TranslationViewModel
import com.training.victor.fallingwords.presenter.MainPresenter
import com.training.victor.fallingwords.utils.mainApplication
import com.training.victor.fallingwords.utils.showRequestErrorMessage
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainPresenter.MainView, GameFinishedDialog.GameFinishedListener {

    @Inject lateinit var mainPresenter: MainPresenter
    lateinit var animation: Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainApplication().createPresenterComponent().inject(this)

        mainPresenter.view = this
        mainPresenter.getNewKeyWord()

        animation = AnimationUtils.loadAnimation(this, R.anim.turn_down)


        btnWrong.setOnClickListener {
            removeTextAnimation(txtFallingWord)
            mainPresenter.checkIfWrongIsOk()
        }

        btnRight.setOnClickListener {
            removeTextAnimation(txtFallingWord)
            mainPresenter.checkIfRightIsOk()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mainApplication().releasePresenterComponent()
    }


    override fun onNewTranslationAvailable(translationViewModel: TranslationViewModel) {
        txtShownText.text = translationViewModel.key
        mainPresenter.getSupposedTranslation()
    }

    override fun onNewTranslationError(error: String) {
        showRequestErrorMessage(error)
    }

    override fun onSupposedTranslationAvailable(supposedTranslation: TranslationViewModel) {
        txtFallingWord.text = supposedTranslation.translation

        animation.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) { }
            override fun onAnimationStart(animation: Animation?) { }

            override fun onAnimationEnd(animation: Animation?) {
                mainPresenter.addOneToSkippedCount()
            }
        })

        txtFallingWord.startAnimation(animation)
    }

    override fun onSupposedTranslationError(error: String) {
        showRequestErrorMessage(error)
    }

    override fun onSkippedCountUpdated(skippedCount: Int) {
        txtSkipped.text = skippedCount.toString()
    }

    override fun onRightCountUpdated(rightCount: Int) {
        txtRight.text = rightCount.toString()

    }

    override fun onWrongCountUpdated(wrongCount: Int) {
        txtWorng.text = wrongCount.toString()
    }

    override fun askForNewTranslation() {
        mainPresenter.getNewKeyWord()
    }

    override fun onGameFinished(rightCount: Int, skippedCount: Int, wrongCount: Int) {
        val endDialog = GameFinishedDialog.newInstance(
            rightCount.toString(),
            skippedCount.toString(),
            wrongCount.toString(),
            this)
        endDialog.isCancelable = false
        endDialog.show(supportFragmentManager, END_DIALOG_TAG)
    }



    override fun onContinueWithGame() {
        mainPresenter.restartSession()
    }

    override fun onExitGame() {
        finish()
    }



    private fun removeTextAnimation(textView: TextView) {
        textView.animation.setAnimationListener(null)
        textView.clearAnimation()
    }
}
