package com.training.victor.fallingwords.ui

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.victor.fallingwords.R
import kotlinx.android.synthetic.main.dialog_game_finished.*

class GameFinishedDialog: DialogFragment() {

    companion object {
        private const val ARG_HITS = "HITS"
        private const val ARG_SKIP = "SKIP"
        private const val ARG_WONG = "WONG"

        fun newInstance(hits: String, skipped: String, wrongs: String, listener: GameFinishedListener): GameFinishedDialog {
            val args = Bundle()
            args.putString(ARG_HITS, hits)
            args.putString(ARG_SKIP, skipped)
            args.putString(ARG_WONG, wrongs)

            val fragment = GameFinishedDialog()
            fragment.arguments = args
            fragment.listener = listener
            return fragment
        }
    }

    private var listener: GameFinishedListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_game_finished, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val hits = arguments?.getString(ARG_HITS)
        val skipped = arguments?.getString(ARG_HITS)
        val wrongs = arguments?.getString(ARG_HITS)

        txtHits.text = String.format(getString(R.string.hits_count), hits)
        txtSkip.text = String.format(getString(R.string.skipped_count), skipped)
        txtWorngs.text = String.format(getString(R.string.misses_count), wrongs)

        btnExit.setOnClickListener {
            listener?.onExitGame()
            dismiss()
        }

        btnContinue.setOnClickListener {
            listener?.onContinueWithGame()
            dismiss()
        }
    }


    interface GameFinishedListener {
        fun onContinueWithGame()
        fun onExitGame()
    }
}