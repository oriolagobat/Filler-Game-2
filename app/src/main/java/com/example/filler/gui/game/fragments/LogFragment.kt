package com.example.filler.gui.game.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.filler.databinding.LogFragmentBinding
import com.example.filler.gui.game.viewmodel.GUIGameViewModel
import com.example.filler.log.Logger

class LogFragment : Fragment() {
    private lateinit var gameViewModel: GUIGameViewModel
    private lateinit var binding: LogFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LogFragmentBinding.inflate(inflater, container, false)
        gameViewModel = ViewModelProvider(this)[GUIGameViewModel::class.java]
        binding.logTextView.text = Logger.getLog()
        Logger.logList.observe(viewLifecycleOwner) {
            binding.logTextView.text = Logger.getLog()
        }
        return binding.root
    }


}