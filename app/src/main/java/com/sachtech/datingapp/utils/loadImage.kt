package com.sachtech.datingapp.utils

import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import java.io.File

inline fun <reified T> ImageView.loadImage(source: T) {
    when (T::class) {
       /* String::class -> Picasso.get().load(source as String)?.noFade()?.into(this)
           *//* .apply(requestOptions())*//*

        Uri::class -> Picasso.get().load(source as Uri).into(this)
        File::class->Picasso.get().load(source as File).into(this)*/
        String::class ->   Picasso.get().load(source as String).transform(CircleTransform()).into(this)
        Uri::class ->   Picasso.get().load(source as Uri).transform(CircleTransform()).into(this)
        File::class ->   Picasso.get().load(source as File).transform(CircleTransform()).into(this)
    }

}
/*fun ImageView.requestOptions(): RequestOptions {
    return RequestOptions()
        .useUnlimitedSourceGeneratorsPool(true)
        .override(width, height)
}*/
