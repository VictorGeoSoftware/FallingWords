package com.training.victor.fallingwords.presenter

import com.training.victor.fallingwords.data.DataManager
import com.training.victor.fallingwords.data.model.TranslationViewModel
import io.reactivex.Scheduler
import javax.inject.Inject

class MainPresenter @Inject constructor(private val androidSchedulers: Scheduler,
                                        private val subscriberSchedulers: Scheduler,
                                        private val dataManager: DataManager): Presenter<MainPresenter.MainView>() {

    interface MainView {
        fun onNewTranslationAvailable(translationViewModel: TranslationViewModel)
        fun onNewTranslationError(error: String)
        fun onSupposedTranslationAvailable(supposedTranslation: TranslationViewModel)
        fun onSupposedTranslationError(error: String)
        fun onSkippedCountUpdated(skippedCount: Int)
        fun onRightCountUpdated(rightCount: Int)
        fun onWrongCountUpdated(wrongCount: Int)
        fun askForNewTranslation()
        fun onGameFinished(rightCount: Int, skippedCount: Int, wrongCount: Int)
    }

    companion object {
        private const val GAME_LIMIT = 25
    }


    private var mCurrentTranslation: TranslationViewModel? = null
    private var mSupposedTranslation: TranslationViewModel? = null

    private var mTotalGamesCount = 0
    private var mWrongCount = 0
    private var mSkippedCount = 0
    private var mRightCount = 0


    fun getNewKeyWord() {
        if (mTotalGamesCount < GAME_LIMIT) {
            compositeDisposable.add(dataManager.getNewKeyWord()
                .observeOn(androidSchedulers)
                .subscribeOn(subscriberSchedulers)
                .subscribe({
                    mCurrentTranslation = it
                    view?.onNewTranslationAvailable(it)
                }, {
                    it.printStackTrace()
                    view?.onNewTranslationError(it.message ?: "Unkown")
                }))
        }
    }

    fun getSupposedTranslation() {
        compositeDisposable.add(dataManager.getNewKeyWord().observeOn(androidSchedulers)
            .subscribeOn(subscriberSchedulers)
            .subscribe({
                mSupposedTranslation = it
                view?.onSupposedTranslationAvailable(it)
            }, {
                it.printStackTrace()
                view?.onSupposedTranslationError(it.message ?: "Unkown")
            }))
    }

    fun addOneToSkippedCount() {
        mSkippedCount += 1
        updateTotalGamesCount()
        view?.onSkippedCountUpdated(mSkippedCount)
        view?.askForNewTranslation()
    }

    fun checkIfWrongIsOk() {
        if (!isTranslationRight()) {
            mRightCount += 1
            view?.onRightCountUpdated(mRightCount)
        } else {
            mWrongCount += 1
            view?.onWrongCountUpdated(mWrongCount)
        }

        updateTotalGamesCount()
        view?.askForNewTranslation()
    }

    fun checkIfRightIsOk() {
        if (isTranslationRight()) {
            mRightCount += 1
            view?.onRightCountUpdated(mRightCount)
        } else {
            mWrongCount += 1
            view?.onWrongCountUpdated(mWrongCount)
        }

        updateTotalGamesCount()
        view?.askForNewTranslation()
    }

    private fun updateTotalGamesCount() {
        mTotalGamesCount += 1

        if (mTotalGamesCount >= GAME_LIMIT) {
            view?.onGameFinished(mRightCount, mSkippedCount, mWrongCount)
        }
    }

    private fun isTranslationRight(): Boolean {
        return mCurrentTranslation?.translation == mSupposedTranslation?.translation
    }

    fun restartSession() {
        mCurrentTranslation = null
        mSupposedTranslation = null

        mTotalGamesCount = 0
        mWrongCount = 0
        mSkippedCount = 0
        mRightCount = 0

        view?.onRightCountUpdated(mRightCount)
        view?.onSkippedCountUpdated(mSkippedCount)
        view?.onWrongCountUpdated(mWrongCount)

        view?.askForNewTranslation()
    }
}