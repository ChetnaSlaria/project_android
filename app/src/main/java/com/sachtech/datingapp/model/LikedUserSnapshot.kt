package com.sachtech.datingapp.model

import com.google.firebase.firestore.DocumentSnapshot

data class LikedUserSnapshot(val status: Boolean = false, val documentSnapshot: DocumentSnapshot?)