package com.example.cammate.presentation.viewer.find_room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cammate.databinding.FragmentEnterRoomBinding


class EnterRoomFragment : Fragment() {
    private var _binding: FragmentEnterRoomBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var input = requireArguments().getString("checked_cammate")
        binding.tvEnterRoom.text = input + "님의\n 방으로 입장하기"

        binding.buttonFirst.setOnClickListener{

        }
    }
}