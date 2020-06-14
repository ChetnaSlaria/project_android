/*
package com.sachtech.datingapp.ui.chat.activity

import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import com.cometchat.pro.constants.CometChatConstants
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.BaseMessage
import com.sachtech.datingapp.R
import com.sachtech.datingapp.cometChat.CometConstants
import com.sachtech.datingapp.helper.CometChatAudioHelper
import com.sachtech.datingapp.helper.OutgoingAudioHelper
import com.sachtech.datingapp.utils.loadImage
import gurtek.mrgurtekbase.KotlinBaseActivity
import kotlinx.android.synthetic.main.activity_call.*

class CallActivity : KotlinBaseActivity(), View.OnClickListener, OnMessageUpdate {
    override fun onUpdate(messageList: BaseMessage) {

    }

    override fun onError(error: CometChatException?) {

    }

    private lateinit var name: String
    private lateinit var id: String
    private var imageUrl: String? = null
    private lateinit var sessionID: String
    private lateinit var notification: Uri
    private var isOutGoing: Boolean = false
    private var cometChatAudioHelper: CometChatAudioHelper? = null

    val cometMessage by lazy { CometChatMessaging() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
        cometChatAudioHelper = CometChatAudioHelper(this)
        cometChatAudioHelper?.initAudio()
        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        try {

            if (intent?.action?.equals(CometChatConstants.CALL_TYPE_VIDEO)!!) {
                if (!CCPermissionHelper.hasPermissions(
                        this,
                        *arrayOf(
                            CCPermissionHelper.REQUEST_PERMISSION_CAMERA,
                            CCPermissionHelper.REQUEST_PERMISSION_RECORD_AUDIO
                        )
                    )
                ) {
                    CCPermissionHelper.requestPermissions(
                        this,
                        arrayOf(
                            CCPermissionHelper.REQUEST_PERMISSION_CAMERA,
                            CCPermissionHelper.REQUEST_PERMISSION_RECORD_AUDIO
                        ),
                        StringContract.RequestCode.VIDEO_CALL
                    )
                }

            } else if (intent?.action.equals(CometChatConstants.CALL_TYPE_AUDIO)) {
                if (!CCPermissionHelper.hasPermissions(
                        this,
                        *arrayOf(CCPermissionHelper.REQUEST_PERMISSION_RECORD_AUDIO)
                    )
                ) {

                    CCPermissionHelper.requestPermissions(
                        this, arrayOf(CCPermissionHelper.REQUEST_PERMISSION_RECORD_AUDIO),
                        StringContract.RequestCode.VOICE_CALL
                    )
                }
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        tvUserName.text = intent?.getStringExtra(StringContract.IntentString.USER_NAME)
        intent?.getStringExtra(StringContract.IntentString.USER_ID)
        intent?.getStringExtra(StringContract.IntentString.SESSION_ID)



        circular_userPic.loadImage(intent?.getStringExtra(StringContract.IntentString.USER_AVATAR))
        content.startRippleAnimation()
        user_image.loadImage(intent?.getStringExtra(StringContract.IntentString.USER_AVATAR))


        if (intent.hasExtra(StringContract.IntentString.RECIVER_TYPE)) {

            if (intent.getStringExtra(StringContract.IntentString.RECIVER_TYPE).equals(
                    CometChatConstants.RECEIVER_TYPE_GROUP
                )
            ) {

                name = intent.getStringExtra(StringContract.IntentString.GROUP_NAME)
                id = intent.getStringExtra(StringContract.IntentString.GROUP_ID)
                imageUrl = intent?.getStringExtra(StringContract.IntentString.GROUP_ICON)
            } else {
                name = intent.getStringExtra(StringContract.IntentString.USER_NAME)
                id = intent.getStringExtra(StringContract.IntentString.USER_ID)
                imageUrl = intent?.getStringExtra(StringContract.IntentString.USER_AVATAR)
            }
        }

        if (intent?.type.equals(StringContract.IntentString.INCOMING)) {
            cometChatAudioHelper?.startIncomingAudio(notification, true)
            isOutGoing = false
        } else if (intent?.type.equals(StringContract.IntentString.OUTGOING)) {

            cometChatAudioHelper?.startOutgoingAudio(OutgoingAudioHelper.Type.IN_COMMUNICATION)
            val rl = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )

            rl.addRule(RelativeLayout.CENTER_HORIZONTAL)
            rl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            rl.bottomMargin = 56

            isOutGoing = true
            hangUp.layoutParams = rl
            acceptCall.visibility = View.GONE
            tvCallText.text = "Ringing..."

        }

        acceptCall.setOnClickListener(this)
        hangUp.setOnClickListener(this)

        sessionID = intent.getStringExtra(StringContract.IntentString.SESSION_ID)


    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when (requestCode) {

            StringContract.RequestCode.VOICE_CALL -> if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {

            } else {
                Toast.makeText(this, "Voice Call", Toast.LENGTH_SHORT).show()
            }
            StringContract.RequestCode.VIDEO_CALL -> if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
            } else {
                Toast.makeText(this, "Video Call", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(p0: View?) {

        when (p0?.id) {
            R.id.acceptCall -> {
                cometChatAudioHelper?.stop(false)
                cometMessage.acceptCall(sessionID, main_call_view, this@CallActivity)
            }

            R.id.hangUp -> {
                cometChatAudioHelper?.stop(false)
                if (isOutGoing) {
                    cometMessage.rejectCall(
                        sessionID,
                        CometChatConstants.CALL_STATUS_CANCELLED,
                        this@CallActivity
                    )
                } else {
                    cometMessage.rejectCall(
                        sessionID,
                        CometChatConstants.CALL_STATUS_REJECTED,
                        this@CallActivity
                    )
                }

            }

        }
    }

    override fun onStart() {
        super.onStart()
        cometMessage.addCallListener(main_call_view, this, CometConstants.CallListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        cometMessage.removeCallListener(CometConstants.CallListener)
        cometChatAudioHelper?.stop(false)
    }

}
*/
