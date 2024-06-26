package com.example.cammate.presentation.make_room

import android.Manifest
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.cammate.R
import com.example.cammate.databinding.FragmentMakeRoomBinding
import com.example.cammate.presentation.chatting.ChatFragment
import com.example.cammate.presentation.utils.animals
import com.example.cammate.presentation.utils.determiners
import com.example.cammate.retrofit.PostRoom.PostRequest
import com.example.cammate.retrofit.RetrofitWork
import com.example.cammate.webRTC.Models.IceCandidateModel
import com.example.cammate.webRTC.Models.MessageModel
import com.example.cammate.webRTC.RTCClient
import com.example.cammate.webRTC.SocketRepository
import com.example.cammate.webRTC.utils.NewMessageInterface
import com.example.cammate.webRTC.utils.PeerConnectionObserver
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.permissionx.guolindev.PermissionX
import org.webrtc.IceCandidate
import org.webrtc.MediaStream
import org.webrtc.SessionDescription
import java.util.Random


class MakeRoomFragment : Fragment(){
    private var _binding: FragmentMakeRoomBinding? = null
    private val binding get() = _binding!!
    private var userName:String?=null
    private var socketRepository:SocketRepository?=null
    private var rtcClient : RTCClient?=null
    private var target:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMakeRoomBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val deviceId = getDeviceUid()

        // 랜덤 이름 체크박스
        binding.checkRandom.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val randomDeterminer = determiners[Random().nextInt(determiners.size)]
                val randomAnimal = animals[Random().nextInt(animals.size)]
                val randomName = "$randomDeterminer $randomAnimal"
                binding.editName.setText(randomName)
            }
        }

        // 방 만들기 버튼
        binding.buttonCreate.setOnClickListener {
            // 닉네임 비어있는지 체크
            userName = binding.editName.text.toString()
            if (userName == ""){
                binding.checkRandom.visibility = View.VISIBLE
            }

            else {
                PermissionX.init(requireActivity())
                        .permissions(
                                Manifest.permission.CAMERA
                        ).request { allGranted, _, _ ->
                            if (allGranted) {
                                userName = binding.editName.text.toString()
                                val password = binding.editPassword.text.toString()
                                val tempMac = "2.2.2.2"
                                val sendData = Bundle().also {
                                    it.putString("nickname", userName)
                                    it.putString("password", password) //password로 바꿔야 함
                                }
                                // tempMac -> deviceId로 변경해야 함
                                val roomData = PostRequest(tempMac, userName!!, password)
                                val retrofitWork = RetrofitWork(roomData)
                                retrofitWork.work()
                                findNavController().navigate(R.id.action_makeRoomFragment_to_waitingRoomFragment, sendData)
                            } else {
                                Toast.makeText(requireActivity(), "you should accept all permissions", Toast.LENGTH_LONG).show()
                            }
                        }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getDeviceUid(): String {
        val android_id = Settings.Secure.getString(
            context?.contentResolver,
            Settings.Secure.ANDROID_ID
        )
        return android_id
    }

}