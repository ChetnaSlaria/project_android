package com.sachtech.datingapp.utils


import com.sachtech.datingapp.data.FacebookUser
import com.sachtech.datingapp.data.GoogleUser
import com.sachtech.datingapp.data.User
import com.sachtech.datingapp.utils.Preferences.get
import com.sachtech.datingapp.utils.Preferences.getprefObject

fun getUid(): String? {
    return get(PrefKeys.INSTANCE.uid, "")!!
}

fun getCurrentUser(): User? {
    return getprefObject<User>(PrefKeys.INSTANCE.user)
}

fun facebookUser(): FacebookUser? {
    return getprefObject(PrefKeys.INSTANCE.facebookuser)
}

fun googleUser(): GoogleUser? {
    return getprefObject(PrefKeys.INSTANCE.googleuser)
}

fun getOrigin(): String? {
    return get(PrefKeys.INSTANCE.origin, "")
}

fun getColorComplexion(): String? {
    return get(PrefKeys.INSTANCE.colorcomplexion, "")
}

fun getHairColor(): String? {
    return get(PrefKeys.INSTANCE.haircolor, "")
}

fun getEyeColor(): String? {
    return get(PrefKeys.INSTANCE.eyecolor, "")
}

fun getProfession(): String? {
    return get(PrefKeys.INSTANCE.profession, "")
}

fun getEducation(): String? {
    return get(PrefKeys.EDUCATION, "")
}

fun getNationality(): String? {
    return get(PrefKeys.NATIONALITY, "")
}

fun getLatitude():String?{
    return get(PrefKeys.LATITUDE,"0.0")
}

fun getLongitude():String?{
    return get(PrefKeys.LONGITUDE,"0.0")
}

fun getLanguage():String?{
    return get(PrefKeys.INSTANCE.language,"")
}
fun getFcm():String?{
    return get(PrefKeys.INSTANCE.fcm,"")
}

