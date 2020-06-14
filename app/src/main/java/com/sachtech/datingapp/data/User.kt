package com.sachtech.datingapp.data

import androidx.annotation.Keep
import java.io.Serializable

@Keep
class User : Serializable {
    var uid: String = ""
    var fcm: String = ""
    var isOnline: Boolean = true
    var lastLogin: Int = 0
    var accountStatus: String? = ""

    var name: String = ""
    var age: String = ""
    var dob: Long = 0
    var email: String = ""
    var gender: String = ""
    var profilePic: String = ""
    var aboutFamily: String = ""
    var aboutMe: String = ""
    var profileHighlight: String = ""
    var languages: List<String>? = null
    var images: ArrayList<String>? = null
    var location: String = ""
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var sect: String = ""
    var wear: String = ""
    var beard: String = ""
    var revert: String = ""
    var halal: String = ""
    var prays: String = ""
    var completedQuran: String = ""
    var nationality: String = ""
    var origin: String = ""
    var maritalStatus: String = ""
    var currentLivingArrangment: String = ""
    var livingArrangmentAfterMarriage: String = ""
    var build: String = ""
    var eyeColour: String = ""
    var hairColour: String = ""
    var height: String = ""
    var colorComplexion: String = ""
    var disablility: String = ""
    var education: String = ""
    var profession: String = ""
    var marriagePlans: String = ""
    var createdOn: Long = 0
    var verificationImage: String = ""
    var private = false
    var lookingFor: String = ""
    var isSubscribed:Boolean=false
    var subscriptionFrom: Long = 0
    var subscriptionTill:Int=0
    var subscriptionType:String=""
}



