/*
package com.sachtech.datingapp.ui.login.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.util.Log
import android.view.View
import com.cometchat.pro.models.User
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.firestore.DocumentSnapshot
import com.mgazetti.android.util.FacebookCallbackListener
import com.mgazetti.android.util.Google
import com.sachtech.datingapp.R
import com.sachtech.datingapp.cometChat.CometChatAuth
import com.sachtech.datingapp.cometChat.listener.OnCometLogin
import com.sachtech.datingapp.data.FacebookUser
import com.sachtech.datingapp.extensions.onFocusChange
import com.sachtech.datingapp.extensions.showToast
import com.sachtech.datingapp.extensions.validateEmail
import com.sachtech.datingapp.extensions.validatePassword
import com.sachtech.datingapp.presenter.SignInPresenter
import com.sachtech.datingapp.ui.home.HomeActivity
import com.sachtech.datingapp.networking.Facebook
import com.sachtech.datingapp.networking.GoogleCallbackListener
import com.sachtech.datingapp.utils.*
import com.sachtech.datingapp.utils.PreferencesKt.setPref
import com.sachtech.datingapp.utils.PreferencesKt.setprefObject
import com.sachtech.datingapp.view.SignInView
import gurtek.mrgurtekbase.KotlinBaseFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_sign_in.*
import java.io.*

import java.net.URL


class SignInFragment : KotlinBaseFragment(R.layout.fragment_sign_in), SignInView,
    FacebookCallbackListener,
    GoogleCallbackListener, OnCometLogin {
    override fun onFbImageDownload(
        byteStream: InputStream,
        fbUser: FacebookUser
    ) {

        creteFbImageFile(byteStream, fbUser)
    }

    private fun creteFbImageFile(
        byteStream: InputStream,
        fbUser: FacebookUser
    ) {
        var theFile = createFile(".png")!!
        Observable.fromCallable {
            writeFile(byteStream, theFile.absolutePath)
        }.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
            {
                fbUser.profilePic = theFile.absolutePath
                setprefObject(PrefKeys.INSTANCE.facebookuser, fbUser)
                Log.e("fbuser", fbUser.toString())
                signInPresenter.signInWithCredentials(FacebookAuthProvider.getCredential(fbUser.accessToken!!))
            }, {

            })
    }

    private fun createFile(ext: String): File? {
        val mediaStorageDir = activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (!mediaStorageDir!!.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }
        var file = File(
            mediaStorageDir.path + File.separator
                    + System.currentTimeMillis() + ext
        )
        return file
    }

    fun writeFile(inputStream: InputStream? = null, absolutePath: String) {


        var `in`: InputStream? = null
        var out: OutputStream? = null
        try {
            `in` = inputStream

            val outFile = File(absolutePath)
            out = FileOutputStream(outFile)
            copyFile(`in`!!, out)
        } catch (e: IOException) {

        } finally {

            if (`in` != null) {
                try {
                    `in`.close()
                } catch (e: IOException) {
                    // NOOP
                }

            }
            if (out != null) {
                try {
                    out.close()
                } catch (e: IOException) {
                    // NOOP
                }

            }


        }
    }

    @Throws(IOException::class)
    private fun copyFile(`in`: InputStream, out: OutputStream) {
        val buffer = ByteArray(1024)
        var read: Int


        var linehasData = true
        while (linehasData) {
            //loge("Read", "inside while")
            read = `in`.read(buffer)
            if (read != -1) {
                out.write(buffer, 0, read)
            } else {
                linehasData = false
            }

        }
    }

    val signInPresenter by lazy { SignInPresenter(this) }
    val facebook by lazy { Facebook(this, this) }
    val google by lazy { Google(this, this) }
    val cometAuth by lazy { CometChatAuth(context!!) }
    val bundle by lazy { Bundle() }
    var passwordText: String? = null
    var emailText: String? = null
    override fun onCometLoginFailure(localizedMessage: String) {
        hideProgress()
        showToast("Sign In Failed")
    }

    override fun onCometLoginSuccess(p0: User?) {
        hideProgress()
        signInEmail.setText("")
        signInPassword.setText("")
        //setPref(PrefKeys.COMETUSER, p0)
        setPref(PrefKeys.INSTANCE.password, passwordText)
        setPref(PrefKeys.INSTANCE.email, emailText)

        baselistener.openA(HomeActivity::class)
        activity?.finishAffinity()
    }


    override fun onFailure(message: String) {
        hideProgress()
        showToast(message)
    }

    override fun onGoogleSignInSuccess(account: AuthCredential) {
        signInPresenter.signInWithCredentials(account)
    }

    override fun onFacebookUserDetails(fbuser: FacebookUser) {
        //setprefObject(PrefKeys.FACEBOOKUSER, fbuser)
        //
        //  signInPresenter.downloadFbImage(fbuser.profilePic!!,fbuser)

        Observable.fromCallable {

            val url = URL(fbuser.profilePic!!)
            url.openConnection().getInputStream()


        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            creteFbImageFile(it, fbuser)
        }, {
            Log.e("ex", "" + it?.localizedMessage)
        })

    }

    override fun onUserDetails(it: Task<DocumentSnapshot>) {

        Log.e("data", it.result?.data.toString())
        if (it.result?.data != null) {
            setprefObject(
                PrefKeys.INSTANCE.user,
                it.result?.toObject(com.sachtech.datingapp.data.User::class.java)
            )
            Log.e(
                "userObj",
                it.result?.toObject(com.sachtech.datingapp.data.User::class.java).toString()
            )
            Log.e("userData", it.result?.data.toString())
             val user=it.result?.toObject(com.sachtech.datingapp.data.User::class.java)
  if(user?.accountStatus==Constants.NOT_VERIFIED)
            {
                hideProgress()
              baselistener.navigateToFragment(NotVerifiedStatusFragment::class)
            }
            else{

            cometAuth.loginToCometChat(getUid()!!, this)
          //  }
        } else {
            hideProgress()
            baselistener.navigateToFragment(ProfileImageFragment::class, bundle)
        }
    }


    override fun onFacebookLoginSuccess(result: LoginResult?) {
        showProgress()
        signInPresenter.getFacebookUserDetails(result?.accessToken!!)
    }

    override fun onSignIn(result: AuthResult) {

        Log.e("signInResult", result.user.toString())
        setPref(PrefKeys.INSTANCE.uid, result.user?.uid?.toLowerCase())
        bundle.putString(IntentString.NAME, result.user?.email?.substringBefore("@"))
        bundle.putString(IntentString.EMAIL, result.user?.email)
        signInPresenter.getUserDetails(result.user?.uid?.toLowerCase())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        google.init()
        //videoView.setVideoURI(Uri.parse("android.resource://" + context?.packageName + "/" + R.raw.video_1))

        CommonUtil.hideKeyboard(activity!!)

        setOnClick()
        setSpan()
        signInFacebook.setOnClickListener {
            facebook.init()
        }

        signInGoogle.setOnClickListener {
            showProgress()
            google.signInWithGoogle()
        }
    }

    private fun onItemFocusChange() {
        onFocusChange(signInEmail, email, "Email")
        onFocusChange(signInPassword, password, "Password")
    }

    private fun setSpan() {
        val span = signInSignUpText.text.toString()
        val spannableString = SpannableString(span)
        spannableString.setSpan(object : URLSpan("") {
            override fun onClick(widget: View) {
                signInEmail.setText("")
                signInPassword.setText("")
                baselistener.navigateToFragment(SignUpFragment::class)
            }

        }, 17, 24, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        signInSignUpText.text = spannableString
        signInSignUpText.movementMethod = LinkMovementMethod.getInstance()

    }

    private fun setOnClick() {
        signInBtn.setOnClickListener {
            signIn()
        }
        signInForgotPassword.setOnClickListener {
            baselistener.navigateToFragment(
                ForgotPasswordFragment::class
            )
        }
        signInEmail.setOnTouchListener { view, motionEvent ->

            view.isFocusable = true
            view.isFocusableInTouchMode = true
            return@setOnTouchListener false
        }
        signInPassword.setOnTouchListener { view, motionEvent ->

            view.isFocusable = true
            view.isFocusableInTouchMode = true
            return@setOnTouchListener false
        }
    }

    private fun signIn() {
        emailText = signInEmail.text.toString()
        passwordText = signInPassword.text.toString()
        if(!emailText!!.validateEmail())
        {
           showToast("Enter valid Email")
        }
        if(!passwordText!!.validatePassword())
        {
            showToast("Enter valid password")
        }
        if (emailText!!.validateEmail() && passwordText!!.validatePassword()) {
            showProgress()
            signInPresenter.signIn(emailText!!, passwordText!!)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == google.RC_SIGN_IN) {
            google.onActivityResult(requestCode, resultCode, data)
        } else facebook.onActivityResult(requestCode, resultCode, data)
    }

}
*/
