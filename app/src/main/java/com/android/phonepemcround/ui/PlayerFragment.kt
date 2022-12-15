package com.android.phonepemcround.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.android.phonepemcround.databinding.FragPlayerBinding
import javax.inject.Inject

class PlayerFragment : Fragment() {

    @Inject
    lateinit var viewModel: PlayViewModel

    private var binding: FragPlayerBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragPlayerBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            viewModel.loadData()
            lifecycleScope.launchWhenStarted {
                viewModel.uiState.collect {
                    when (it) {
                        is Loading -> showLoading()
                        is Success -> showGameView(it.data)
                        is Failure -> showFailureUI()
                        is GameFinished -> showFinishedUI()
                        is CorrectAnswer -> showCorrectAnswerUI()
                        is InCorrectAnswer -> showInCorrectAnswerUI()
                    }
                }
            }

            btn.setOnClickListener {
                viewModel.checkAnswer(etUserInput.toString())
            }
        }
    }

    private fun showInCorrectAnswerUI() {

    }

    private fun showCorrectAnswerUI() {

    }

    private fun showFinishedUI() {

    }

    private fun showFailureUI() {

    }

    private fun showGameView(uiState: UiData) {

    }

    private fun showLoading() {
        binding?.run {
            progress.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}