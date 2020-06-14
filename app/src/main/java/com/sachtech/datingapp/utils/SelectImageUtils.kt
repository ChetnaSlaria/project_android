/*
package com.sachtech.datingapp.utils

import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.MediaStore.Images
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.sachtech.datingapp.ui.home.dialog.ImageChooserDialog
import cool.rishab.gallerydemo.FileUtils
import cool.rishab.gallerydemo.checkPermissions
import cool.rishab.gallerydemo.withPermissions
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream
import java.lang.Exception


class SelectImageUtils(val context: Context) {
    var CAMERA_REQUEST_CODE = 10
    var GALLERY_REQUEST_CODE = 20
    var bitmap: Bitmap? = null
    var path: String? = null
    var hasImage= false
    var selectImageListener: SelectImageUtils.OnSelectImageListener? = null
    var dialog: Dialog? = null
    var uri:Uri?=null
    var view:ImageView?= null
    //Method to select Image from camera
    fun selectImageFromCamera(
        activity: Fragment,
        verification_image: ImageView?
    ) {
        this.view=verification_image
        //check permissions of camera and write external storage

    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)

   checkPermissions(

                    arrayOf(
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ).withPermissions(activity.activity!!){
                        openCamera(activity)
                    }


            //otherwise it goes to onRequestPermissionResult
    else
                activity.requestPermissions(
                    arrayOf(
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ), CAMERA_REQUEST_CODE
                )

    }

    //method to open camera
    private fun openCamera(activity: Fragment) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        //after capturing image,it gives the result in onActivityResult method
        activity.startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    //method to select image from gallery
    fun selectImageFromGallery(activity: Fragment) {

  if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
            if (checkPermissions(

                    arrayOf(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ).withPermissions(activity = activity.activity!!){
                        openGallery(activity)
                    }

            //otherwise it returns the permission result into onRequestPermissionResult method
  {
                activity.requestPermissions(
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    GALLERY_REQUEST_CODE
                )
            }

    }

    //method to open the gallery
    private fun openGallery(activity: Fragment) {
        val intent = Intent(Intent.ACTION_PICK,Images.Media.EXTERNAL_CONTENT_URI)
        //after selecting image, it gives the result in onActivityResult method
        activity.startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    //method to select image from chooser

    fun openChooser(activity: Fragment, image: ImageView) {
       this.view=image
        createDialog(activity = activity)
    }


    private fun createDialog(activity: Fragment) {
        val dialog= ImageChooserDialog(activity)
        dialog.setStyle(DialogFragment.STYLE_NO_FRAME, 0)
        dialog.show(activity.fragmentManager!!,"image")
    }

    //listener to give the result to the fragment
    fun selectImage(selectListener: OnSelectImageListener) {
        selectImageListener = selectListener
    }

    //after capturing image or selecting image from gallery,it gives the result here

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {


            if (data != null) {
                if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
                    val bitmap = data.extras?.get("data") as Bitmap
                    uri = getImageUri(context, bitmap)
                    path = FileUtils.getPath(context, uri!!)
                } else if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
                    uri = data.data
                    path = FileUtils.getPath(context, uri!!)
                }

                selectImageListener!!.onImageSelected(uri!!, view!!)
            }
            if (dialog != null) {
                dialog?.dismiss()
            }
        }catch (e:Exception){

        }
    }

    //method to convert bitmap into uri
    private fun getImageUri(context: Context, photo: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = Images.Media.insertImage(context.contentResolver, photo, "Title", null)
        return Uri.parse(path)
    }

    //after requesting for permissions,it gives the permission result here
  fun onRequestPermissionResult(
        activity: Fragment,
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_REQUEST_CODE)
            selectImageFromCamera(activity, view)
        else if (requestCode == GALLERY_REQUEST_CODE)
            selectImageFromGallery(activity)
    }




    //to send the path of image to the fragment
    interface OnSelectImageListener {
        fun onImageSelected(path: Uri,view:ImageView?)
    }

}

*/
