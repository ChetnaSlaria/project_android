/*
package com.sachtech.datingapp.networking;

import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sachtech.datingapp.data.Blockeduser;
import com.sachtech.datingapp.data.LikeUsers;
import com.sachtech.datingapp.data.Report;
import com.sachtech.datingapp.data.User;
import com.sachtech.datingapp.utils.PrefDataKt;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

class FirebaseHelper {

FirebaseFirestore firebaseFirestore;
FirebaseStorage firebaseStorage;
FirebaseAuth auth;
   String collection = "user";

   public FirebaseFirestore getFirebaseFireStore() {
      firebaseFirestore=FirebaseFirestore.getInstance();
      return firebaseFirestore;
   }

   public FirebaseAuth getAuth() {
      auth=FirebaseAuth.getInstance();
      return auth;
   }

   public FirebaseStorage getFirebaseStorage() {
      firebaseStorage=FirebaseStorage.getInstance();
      return firebaseStorage;
   }

   public final Task<com.google.firebase.auth.AuthResult> signUpUserWithEmail(@NotNull String email, @NotNull String password) {
      Task<com.google.firebase.auth.AuthResult> emailAndPassword = getAuth().createUserWithEmailAndPassword(email, password);
      return emailAndPassword;
   }

   public final Task<com.google.firebase.auth.AuthResult> loginWithEmail(@NotNull String email, @NotNull String password) {
      Task<com.google.firebase.auth.AuthResult> task = getAuth().signInWithEmailAndPassword(email, password);
      return task;
   }

   public final Task<com.google.firebase.auth.AuthResult> signInWithCredential(@NotNull AuthCredential credential) {
      Task<com.google.firebase.auth.AuthResult> var10000 = getAuth().signInWithCredential(credential);
      return var10000;
   }

   @NotNull
   public final UploadTask uploadImageToStorage(@NotNull Uri uri) {
      StorageReference ref = getFirebaseStorage().getReferenceFromUrl("gs://blub-5f1e7.appspot.com");
      String uid = PrefDataKt.getUid();
      ref = ref.child(uid);
      uid = uri.getLastPathSegment();

      UploadTask uploadTask = ref.child(uid).putFile(uri);
      return uploadTask;
   }

   public final Task<Void> addUserDetailsToDatabase(@NotNull String uid, @NotNull User user) {
      Task<Void> var10000 = getFirebaseFireStore().collection(collection).document(uid).set(user);
      return var10000;
   }

   public final Task<DocumentSnapshot> getUserDetailsFromDatabase(@NotNull String uid) {
      Task<DocumentSnapshot> var10000 = getFirebaseFireStore().collection(this.collection).document(uid).get();
      return var10000;
   }

   public final Task<Void> deleteUserFromFirebase(@NotNull String uid) {
      Task<Void> var10000 = getFirebaseFireStore().collection(this.collection).document(uid).delete();
      return var10000;
   }

   public final Task<QuerySnapshot> getAllUsers() {
      Task<QuerySnapshot> var10000 = getFirebaseFireStore().collection(this.collection).get();
      return var10000;
   }

   public final Task<Void> updateUserDetails(@NotNull User user) {
      CollectionReference var10000 = getFirebaseFireStore().collection(this.collection);
      String uid = PrefDataKt.getUid();
      Task<Void> var2 = var10000.document(uid).set(user, SetOptions.merge());
      return var2;
   }

   public final Task<Void> updateProfileImageOnDatabase(Map<String, Object> uri) {
      Intrinsics.checkParameterIsNotNull(uri, "uri");
      CollectionReference var10000 = getFirebaseFireStore().collection("user");
      String var10001 = PrefDataKt.getUid();
      Task<Void> var2 = var10000.document(var10001).update(uri);
      return var2;
   }

   @NotNull
   public final DocumentReference addUserToBlockList(@NotNull String uid) {
      DocumentReference var10000 = getFirebaseFireStore().collection("blockedUser").document(uid);
      return var10000;
   }

   @NotNull
   public final DocumentReference addUserToNotInterestedList(@NotNull String uid) {
      DocumentReference var10000 = getFirebaseFireStore().collection("notInterestedUsers").document(uid);
      return var10000;
   }

   @NotNull
   public final DocumentReference addUserToLikeList() {
      DocumentReference var10000 = getFirebaseFireStore().collection("likedUsers").document();
      return var10000;
   }

   public final Task<Void> updateLikeStatus(@NotNull String doc, @NotNull LikeUsers user) {
      Task<Void> var10000 = getFirebaseFireStore().collection("likedUsers").document(doc).set(user, SetOptions.merge());
      return var10000;
   }

   @NotNull
   public final DocumentReference addUserToVisitList() {
      DocumentReference var10000 = getFirebaseFireStore().collection("visitUsers").document();
      return var10000;
   }

   @NotNull
   public final Query getLikedUserList(@NotNull String uid) {
      Query var10000 = getFirebaseFireStore().collection("likedUsers").whereEqualTo("liked_by_user_id", uid);
      return var10000;
   }

   public final void getCurrentLikedUser(@NotNull String uid, @NotNull String secondUid) {
      getFirebaseFireStore().collection("likedUsers").whereEqualTo("liked_by_user_id", uid).whereEqualTo("liked_to_user_id", secondUid).limit(1L).get().addOnSuccessListener((OnSuccessListener)(new OnSuccessListener() {

         public void onSuccess(Object var1) {
            this.onSuccess((QuerySnapshot)var1);
         }

         public final void onSuccess(QuerySnapshot querySnapshot) {
            if (querySnapshot.isEmpty() && querySnapshot.getDocuments().isEmpty()) {
              // listener.invoke(new LikedUserSnapshot(false, (DocumentSnapshot)null));
            } else {
               //listener.invoke(new LikedUserSnapshot(true, (DocumentSnapshot)querySnapshot.getDocuments().get(0)));
            }

         }
      }));
   }

   public final Task<Void> deleteLikedUser(@NotNull String documentId) {
      Task<Void> var10000 = getFirebaseFireStore().collection("likedUsers").document(documentId).delete();
      return var10000;
   }

   @NotNull
   public final Query getVisitUserList(@NotNull String uid) {
      Query var10000 = getFirebaseFireStore().collection("visitUsers").whereEqualTo("visit_to_user_id", uid);
      return var10000;
   }

   @NotNull
   public final CollectionReference addToUserFriendList(@NotNull String uid) {
      Intrinsics.checkParameterIsNotNull(uid, "uid");
      return this.getUserFriendList(uid);
   }

   @NotNull
   public final CollectionReference getUserFriendList(@NotNull String uid) {
      CollectionReference var10000 = getFirebaseFireStore().collection("userFriend").document(uid).collection("friends");
      return var10000;
   }

   public final Task<DocumentSnapshot> getUserFriend(@NotNull String uid, @NotNull String FriendUid) {
      Task<DocumentSnapshot> var10000 = getFirebaseFireStore().collection("userFriend").document(uid).collection("friends").document(FriendUid).get();
      return var10000;
   }

   public final Task<DocumentReference> addReport(@NotNull Report report) {
      Task<DocumentReference> var10000 = getFirebaseFireStore().collection("complaints").add(report);
      return var10000;
   }

   public final Task<DocumentReference> blockedUsers(@NotNull Blockeduser block) {
      Task<DocumentReference> var10000 = getFirebaseFireStore().collection("blockedUser").add(block);
      return var10000;
   }

   public final Task<QuerySnapshot> getBlockedUser(@NotNull String uid) {
      Task<QuerySnapshot> var10000 = this.getFirebaseFireStore().collection("blockedUser").whereEqualTo("block_by_user_id", uid).get();
      return var10000;
   }

   public final Task<DocumentSnapshot> getNotInterestedUsers() {
      CollectionReference var10000 = this.getFirebaseFireStore().collection("notInterestedUsers");
      String var10001 = PrefDataKt.getUid();
      Task<DocumentSnapshot> var1 = var10000.document(var10001).get();
      return var1;
   }

   @NotNull
   public final Query getLikedToMeUserList(@NotNull String uid) {
      Query var10000 = this.getFirebaseFireStore().collection("likedUsers").whereEqualTo("liked_to_user_id", uid);
      return var10000;
   }

   public final Task<Void> deleteUser(@NotNull User user) {
      Task<Void> var10000 = this.getFirebaseFireStore().collection("user").document(user.getUid()).delete();
      return var10000;
   }

}
*/
