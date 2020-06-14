package com.sachtech.datingapp.networking


import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.storage.FirebaseStorage
import com.sachtech.datingapp.data.*
import com.sachtech.datingapp.model.LikedUserSnapshot
import com.sachtech.datingapp.utils.getUid


class FirebaseHelper {

    val firebaseStorage by lazy { FirebaseStorage.getInstance() }
    val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    val firebaseFireStore by lazy { FirebaseFirestore.getInstance() }
    val collection = "user"

    fun signUpUserWithEmail(email: String, password: String) =
        firebaseAuth.createUserWithEmailAndPassword(
            email,
            password
        )

    fun loginWithEmail(email: String, password: String) = firebaseAuth.signInWithEmailAndPassword(
        email,
        password
    )

    fun signInWithCredential(credential: AuthCredential) =
        firebaseAuth.signInWithCredential(credential)

    fun uploadImageToStorage(uri: Uri) =
        firebaseStorage.getReferenceFromUrl("gs://blub-5f1e7.appspot.com").child(getUid()!!).child(
            uri.lastPathSegment!!
        ).putFile(uri)

    fun addUserDetailsToDatabase(uid: String, user: User) =
        firebaseFireStore.collection(collection).document(uid).set(user)


    fun getUserDetailsFromDatabase(uid: String) =
        firebaseFireStore.collection(collection).document(uid).get()


    fun deleteUserFromFirebase(uid: String) =
        firebaseFireStore.collection(collection).document(uid).delete()

    fun getAllUsers() = firebaseFireStore.collection(collection).get()

    fun updateUserDetails(user: User) =
        firebaseFireStore.collection(collection).document(getUid()!!).set(
            user,
            SetOptions.merge()
        )


    fun updateProfileImageOnDatabase(
        uri: Map<String, String>
    ) = firebaseFireStore.collection("user").document(getUid()!!).update(uri)

    fun addUserToBlockList(
        uid: String
    ) = firebaseFireStore.collection("blockedUser").document(uid)

    fun addUserToNotInterestedList(
        uid: String
    ) = firebaseFireStore.collection("notInterestedUsers").document(uid)

    // fun getBlockUserList() = firebaseFireStore.collection("blockedUser").document().get()
    fun addUserToLikeList(): DocumentReference {
        return firebaseFireStore.collection("likedUsers").document()
    }

    fun updateLikeStatus(doc: String, user: LikeUsers) =
        firebaseFireStore.collection("likedUsers").document(doc).set(
            user,
            SetOptions.merge()
        )

    fun addUserToVisitList(): DocumentReference {
        return firebaseFireStore.collection("visitUsers").document()
    }

    fun getLikedUserList(uid: String) =
        firebaseFireStore.collection("likedUsers").whereEqualTo("liked_by_user_id", uid)

    fun getCurrentLikedUser(uid: String, secondUid: String, listener: (LikedUserSnapshot) -> Unit) {
        firebaseFireStore.collection("likedUsers").whereEqualTo("liked_by_user_id", uid).whereEqualTo("liked_to_user_id",secondUid).limit(1).get().addOnSuccessListener {
                querySnapshot ->
            if (querySnapshot.isEmpty && querySnapshot.documents.isEmpty()) {
                listener(LikedUserSnapshot(false, null))
            } else {
                listener(LikedUserSnapshot(true, querySnapshot.documents[0]))
            }
        }
    }

    fun deleteLikedUser(documentId: String) =
        firebaseFireStore.collection("likedUsers").document(documentId).delete()


    fun getVisitUserList(uid: String) =
        firebaseFireStore.collection("visitUsers").whereEqualTo("visit_to_user_id", uid)

    fun addToUserFriendList(uid: String) = getUserFriendList(uid)
    fun getUserFriendList(uid: String) =
        firebaseFireStore.collection("userFriend").document(uid).collection("friends")

    fun getUserFriend(uid: String, FriendUid: String) =
        firebaseFireStore.collection("userFriend").document(uid).collection("f" +
                "riends").document(
            FriendUid
        ).get()

    fun addReport(report: Report) =
        firebaseFireStore.collection("complaints").add(report)

    fun blockedUsers(block: Blockeduser) =
        firebaseFireStore.collection("blockedUser").add(block)

    fun getBlockedUser(uid: String) =
        firebaseFireStore.collection("blockedUser").whereEqualTo("block_by_user_id", uid).get()


    fun getNotInterestedUsers() =
        firebaseFireStore.collection("notInterestedUsers").document(getUid()!!).get()

    fun getLikedToMeUserList(uid: String) =
        firebaseFireStore.collection("likedUsers").whereEqualTo("liked_to_user_id", uid)

    fun deleteUser(user: User)=
        firebaseFireStore.collection("user").document(user.uid).delete()

}

