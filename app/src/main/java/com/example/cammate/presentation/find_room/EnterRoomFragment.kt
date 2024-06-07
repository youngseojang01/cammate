package com.example.cammate.presentation.find_room

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cammate.R
import com.example.cammate.databinding.FragmentEnterRoomBinding
import com.example.cammate.presentation.utils.animals
import com.example.cammate.presentation.utils.determiners
import java.util.Random


class EnterRoomFragment : Fragment() {
    private var _binding: FragmentEnterRoomBinding? = null
    private val binding get() = _binding!!
    private var userName : String =""

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

        val roomName = requireArguments().getString("selected_cammate") // 입장하려는 방 주인 이름
        binding.tvRoomName.text = "'" + roomName + "' 님의 방으로 입장하기"
/*        roomName = arguments?.getString("roomName")
        binding.enteringText.text = "$roomName 님의 방으로 입장하기"*/

        // 랜덤 이름 체크박스
        binding.checkRandom.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val randomDeterminer = determiners[Random().nextInt(determiners.size)]
                val randomAnimal = animals[Random().nextInt(animals.size)]
                val randomName = "$randomDeterminer $randomAnimal"
                binding.editName.setText(randomName)
            }
        }

        binding.btn.setOnClickListener {
            userName = binding.editName.text.toString() // 입장하려는 사람 이름
            val password: String = binding.editPassword.text.toString()
            /*            val tempMac = "0.0.0.0"
            val deviceId = getDeviceUid()*/
            val sendData = Bundle().also {
                it.putString("userName", userName)
                it.putString("roomName", roomName)
                it.putString("password", password) //password로 바꿔야 함
            }
            //init()
            /*          val roomData = PostRequest(deviceId, userName!!,password)
            val retrofitWork = RetrofitWork(roomData)
            retrofitWork.work()*/
            findNavController().navigate(R.id.action_EnterRoomFragment_to_WaitingRoomFragmentFind,sendData)

            binding.backBtn.setOnClickListener {
            // requireActivity().supportFragmentManager.popBackStack()
            findNavController().navigateUp()
            Log.d("tag", "백버튼 클릭")
        }

        //binding.buttonFirst.setOnClickListener{

        //}

/*        view.findViewById<Button>(R.id.btn).setOnClickListener{
           *//* try {
                //findNavController().navigate(R.id.action_enterRoomFragment_to)
            } catch (e: Exception){
                //Log.d("tag", "${e.message}")
            }*/
        /*
            val dialog = NetworkDialog()
            dialog.show((activity as AppCompatActivity).supportFragmentManager, "NetworkDialog")
        }*/
    }

    /*private fun init() {
        socketRepository = SocketRepository(this)
        userName?.let { socketRepository?.initSocket(it) }
        rtcClient = RTCClient(
            requireActivity().application,
            userName!!,
            socketRepository!!,
            object : PeerConnectionObserver() {
                override fun onIceCandidate(p0: IceCandidate?) {
                    super.onIceCandidate(p0)
                    rtcClient?.addIceCandidate(p0)
                    val candidate = hashMapOf(
                        "sdpMid" to p0?.sdpMid,
                        "sdpMLineIndex" to p0?.sdpMLineIndex,
                        "sdpCandidate" to p0?.sdp
                    )

                    socketRepository?.sendMessageToSocket(
                        MessageModel("ice_candidate", userName, roomName, candidate)
                    )

                }

                override fun onAddStream(p0: MediaStream?) {
                    super.onAddStream(p0)
                    //p0?.videoTracks?.get(0)?.addSink(binding.remoteView)
                    var tmp = p0?.videoTracks?.get(0)
                    //Log.d(TAG, "onAddStream: $p0")
                    Log.d("onAddStream", " MediaStream id : $p0 videoid : $tmp")

                }
            })
    }*/
}
}
/*
    override fun onNewMessage(message: MessageModel) {
        when(message.type){
            "call_response"->{ //전화 거는 사람
                if (message.data == "user is not online"){
                    //user is not reachable
*/
/*                    runOnUiThread {
                        Toast.makeText(this,"user is not reachable", Toast.LENGTH_LONG).show()

                    }*//*

                }else{
                    //we are ready for call, we started a call
*/
/*                    runOnUiThread {
                        setWhoToCallLayoutGone()
                        setCallLayoutVisible()
                        binding.apply {
                            //rtcClient?.initializeSurfaceView(localView)
                            rtcClient?.initializeSurfaceView(remoteView)
                            rtcClient?.startLocalVideo(remoteView) //remoteView로 바꿔야
                            rtcClient?.startAddStream()
                            rtcClient?.call(targetUserNameEt.text.toString())
                        }


                    }*//*

                    //rtcClient?.call(roomName!!)

                }
            }
            "answer_received" ->{
                val session = SessionDescription(
                    SessionDescription.Type.ANSWER,
                    message.data.toString()
                )
                rtcClient?.onRemoteSessionReceived(session)
*/
/*                runOnUiThread {
                    binding.remoteViewLoading.visibility = View.GONE
                }*//*

                Log.d("answer_received", "answer_received: session : $session")
            }
            "offer_received" ->{ // 전화 받는 사람
*/
/*                runOnUiThread {
                    setIncomingCallLayoutVisible()
                    binding.incomingNameTV.text = "${message.name.toString()} is calling you"
                    binding.acceptButton.setOnClickListener {
                        setIncomingCallLayoutGone()
                        setCallLayoutVisible()
                        setWhoToCallLayoutGone()

                        binding.apply {
                            //rtcClient?.initializeSurfaceView(localView)
                            rtcClient?.initializeSurfaceView(remoteView)
                            //rtcClient?.startLocalVideo(remoteView) //remote view
                        }
                        val session = SessionDescription(
                            SessionDescription.Type.OFFER,
                            message.data.toString()
                        )
                        rtcClient?.onRemoteSessionReceived(session)
                        rtcClient?.answer(message.name!!)
                        target = message.name!!
                        binding.remoteViewLoading.visibility = View.GONE

                    }
                    binding.rejectButton.setOnClickListener {
                        setIncomingCallLayoutGone()
                    }

                }*//*

                val session = SessionDescription(
                    SessionDescription.Type.OFFER,
                    message.data.toString()
                )
                rtcClient?.onRemoteSessionReceived(session)
                rtcClient?.answer(message.name!!)
                roomName = message.name!!
                //binding.remoteViewLoading.visibility = View.GONE
                Log.d("offer_Received", "offer_received")
            }
*/
/*            binding.rejectButton.setOnClickListener {
                setIncomingCallLayoutGone()
            }*//*


            "ice_candidate"->{
                try {
                    val receivingCandidate = gson.fromJson(gson.toJson(message.data),
                        IceCandidateModel::class.java)
                    rtcClient?.addIceCandidate(IceCandidate(receivingCandidate.sdpMid,
                        Math.toIntExact(receivingCandidate.sdpMLineIndex.toLong()),receivingCandidate.sdpCandidate))
                }catch (e:Exception){
                    e.printStackTrace()
                }
                Log.d("ice_Candidate", "ice_candidate")
            }
        }
    }
*/

//}