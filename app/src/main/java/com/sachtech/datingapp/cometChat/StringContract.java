package com.sachtech.datingapp.cometChat;

import android.graphics.Typeface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;


public final class StringContract {

   public static final class IntentString {
      @NotNull
      private static final String USER_ID = "user_id";
      @NotNull
      private static final String USER_NAME = "user_name";
      @NotNull
      private static final String USER_AVATAR = "user_avatar";
      @NotNull
      private static final String USER_STATUS = "user_status";
      @NotNull
      private static final String LAST_ACTIVE = "last_user";
      @NotNull
      private static final String GROUP_ID = "group_id";
      @NotNull
      private static final String GROUP_NAME = "group_name";
      @NotNull
      private static final String GROUP_ICON = "group_icon";
      @NotNull
      private static final String GROUP_OWNER = "group_owner";
      @NotNull
      private static final String IMAGE_TYPE = "image/*";
      @NotNull
      private static final String AUDIO_TYPE = "audio/*";
      @NotNull
      private static final String[] DOCUMENT_TYPE = new String[]{"*/*"};
      @NotNull
      private static final String[] EXTRA_MIME_TYPE = new String[]{"image/*", "video/*"};
      @NotNull
      private static final String[] EXTRA_MIME_DOC = new String[]{"text/plane", "text/html", "application/pdf", "application/msword", "application/vnd.ms.excel", "application/mspowerpoint", "application/zip"};
      @NotNull
      private static final String TITLE = "title";
      @NotNull
      private static final String POSITION = "position";
      @NotNull
      private static final String SESSION_ID = "session_id";
      @NotNull
      private static final String OUTGOING = "outgoing";
      @NotNull
      private static final String INCOMING = "incoming";
      @NotNull
      private static final String RECIVER_TYPE = "receiver_type";
      @NotNull
      private static final String URL = "image";
      @NotNull
      private static final String FILE_TYPE = "file_type";
      @NotNull
      private static final String ID = "id";
      @NotNull
      private static final String GROUP_DESCRIPTION = "description";
      @NotNull
      private static final String USER_SCOPE = "scope";
      public static final StringContract.IntentString.Companion Companion = new StringContract.IntentString.Companion(null);


      public static final class Companion {
         public Companion(Object o) {

         }

         @NotNull
         public final String getUSER_ID() {
            return StringContract.IntentString.USER_ID;
         }

         @NotNull
         public final String getUSER_NAME() {
            return StringContract.IntentString.USER_NAME;
         }

         @NotNull
         public final String getUSER_AVATAR() {
            return StringContract.IntentString.USER_AVATAR;
         }

         @NotNull
         public final String getUSER_STATUS() {
            return StringContract.IntentString.USER_STATUS;
         }

         @NotNull
         public final String getLAST_ACTIVE() {
            return StringContract.IntentString.LAST_ACTIVE;
         }

         @NotNull
         public final String getGROUP_ID() {
            return StringContract.IntentString.GROUP_ID;
         }

         @NotNull
         public final String getGROUP_NAME() {
            return StringContract.IntentString.GROUP_NAME;
         }

         @NotNull
         public final String getGROUP_ICON() {
            return StringContract.IntentString.GROUP_ICON;
         }

         @NotNull
         public final String getGROUP_OWNER() {
            return StringContract.IntentString.GROUP_OWNER;
         }

         @NotNull
         public final String getIMAGE_TYPE() {
            return StringContract.IntentString.IMAGE_TYPE;
         }

         @NotNull
         public final String getAUDIO_TYPE() {
            return StringContract.IntentString.AUDIO_TYPE;
         }

         @NotNull
         public final String[] getDOCUMENT_TYPE() {
            return StringContract.IntentString.DOCUMENT_TYPE;
         }

         @NotNull
         public final String[] getEXTRA_MIME_TYPE() {
            return StringContract.IntentString.EXTRA_MIME_TYPE;
         }

         @NotNull
         public final String[] getEXTRA_MIME_DOC() {
            return StringContract.IntentString.EXTRA_MIME_DOC;
         }

         @NotNull
         public final String getTITLE() {
            return StringContract.IntentString.TITLE;
         }

         @NotNull
         public final String getPOSITION() {
            return StringContract.IntentString.POSITION;
         }

         @NotNull
         public final String getSESSION_ID() {
            return StringContract.IntentString.SESSION_ID;
         }

         @NotNull
         public final String getOUTGOING() {
            return StringContract.IntentString.OUTGOING;
         }

         @NotNull
         public final String getINCOMING() {
            return StringContract.IntentString.INCOMING;
         }

         @NotNull
         public final String getRECIVER_TYPE() {
            return StringContract.IntentString.RECIVER_TYPE;
         }

         @NotNull
         public final String getURL() {
            return StringContract.IntentString.URL;
         }

         @NotNull
         public final String getFILE_TYPE() {
            return StringContract.IntentString.FILE_TYPE;
         }

         @NotNull
         public final String getID() {
            return StringContract.IntentString.ID;
         }

         @NotNull
         public final String getGROUP_DESCRIPTION() {
            return StringContract.IntentString.GROUP_DESCRIPTION;
         }

         @NotNull
         public final String getUSER_SCOPE() {
            return StringContract.IntentString.USER_SCOPE;
         }

        /* private Companion() {
         }*/

         // $FF: synthetic method
        /* public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
         }*/
      }
   }


   public static final class ViewType {
      public static final int RIGHT_TEXT_MESSAGE = 334;
      public static final int LEFT_TEXT_MESSAGE = 734;
      public static final int LEFT_IMAGE_MESSAGE = 528;
      public static final int RIGHT_IMAGE_MESSAGE = 834;
      public static final int LEFT_VIDEO_MESSAGE = 580;
      public static final int RIGHT_VIDEO_MESSAGE = 797;
      public static final int RIGHT_AUDIO_MESSAGE = 70;
      public static final int LEFT_AUDIO_MESSAGE = 79;
      public static final int LEFT_FILE_MESSAGE = 24;
      public static final int RIGHT_FILE_MESSAGE = 55;
      public static final int CALL_MESSAGE = 84;
      public static final int ACTION_MESSAGE = 99;
      public static final int RIGHT_LOCATION_MESSAGE = 58;
      public static final int LEFT_LOCATION_MESSAGE = 59;
      public static final int RIGHT_MEDIA_REPLY_MESSAGE = 345;
      public static final int RIGHT_TEXT_REPLY_MESSAGE = 346;
      public static final int LEFT_MEDIA_REPLY_MESSAGE = 756;
      public static final int LEFT_TEXT_REPLY_MESSAGE = 748;
    //  public static final StringContract.ViewType.Companion Companion = new StringContract.ViewType.Companion((DefaultConstructorMarker)null);

/*      @Metadata(
         mv = {1, 1, 16},
         bv = {1, 0, 3},
         k = 1,
         d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0016"},
         d2 = {"Lcom/sachtech/datingapp/cometChat/StringContract$ViewType$Companion;", "", "()V", "ACTION_MESSAGE", "", "CALL_MESSAGE", "LEFT_AUDIO_MESSAGE", "LEFT_FILE_MESSAGE", "LEFT_IMAGE_MESSAGE", "LEFT_LOCATION_MESSAGE", "LEFT_MEDIA_REPLY_MESSAGE", "LEFT_TEXT_MESSAGE", "LEFT_TEXT_REPLY_MESSAGE", "LEFT_VIDEO_MESSAGE", "RIGHT_AUDIO_MESSAGE", "RIGHT_FILE_MESSAGE", "RIGHT_IMAGE_MESSAGE", "RIGHT_LOCATION_MESSAGE", "RIGHT_MEDIA_REPLY_MESSAGE", "RIGHT_TEXT_MESSAGE", "RIGHT_TEXT_REPLY_MESSAGE", "RIGHT_VIDEO_MESSAGE", "app"}
      )
      public static final class Companion {
         private Companion() {
         }

         // $FF: synthetic method
       //  public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
         }
      }*/
   }


   public static final class Font {
      @NotNull
      public static Typeface title;
      @NotNull
      public static Typeface name;
      @NotNull
      public static Typeface status;
      @NotNull
      public static Typeface message;
      public static final StringContract.Font.Companion Companion = new StringContract.Font.Companion(null);


      public static final class Companion {
         public Companion(Object o) {

         }

         @NotNull
         public final Typeface getTitle() {
            Typeface var10000 = StringContract.Font.title;
            if (var10000 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("title");
            }

            return var10000;
         }

         public final void setTitle(@NotNull Typeface var1) {
            Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
            StringContract.Font.title = var1;
         }

         @NotNull
         public final Typeface getName() {
            Typeface var10000 = StringContract.Font.name;
            if (var10000 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("name");
            }

            return var10000;
         }

         public final void setName(@NotNull Typeface var1) {
           // Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
            StringContract.Font.name = var1;
         }

         @NotNull
         public final Typeface getStatus() {
            Typeface var10000 = StringContract.Font.status;
            if (var10000 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("status");
            }

            return var10000;
         }

         public final void setStatus(@NotNull Typeface var1) {
          //  Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
            StringContract.Font.status = var1;
         }

         @NotNull
         public final Typeface getMessage() {
            Typeface var10000 = StringContract.Font.message;
            if (var10000 == null) {
               Intrinsics.throwUninitializedPropertyAccessException("message");
            }

            return var10000;
         }

         public final void setMessage(@NotNull Typeface var1) {
            Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
            StringContract.Font.message = var1;
         }

      /*   private Companion() {
         }

         // $FF: synthetic method
         public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
         }
*/      }
   }


   public static final class Color {
      private static int primaryColor;
      private static int primaryDarkColor;
      private static int accentColor;
      private static int rightMessageColor;
      private static int leftMessageColor;
      private static int iconTint;
      private static int white = android.graphics.Color.parseColor("#ffffff");
      private static int black = android.graphics.Color.parseColor("#000000");
      private static int grey = android.graphics.Color.parseColor("#CACACC");
      private static int inactiveColor = android.graphics.Color.parseColor("#9e9e9e");
      public static final StringContract.Color.Companion Companion = new StringContract.Color.Companion(null);

      public static final class Companion {
         public Companion(Object o) {

         }

         public final int getPrimaryColor() {
            return StringContract.Color.primaryColor;
         }

         public final void setPrimaryColor(int var1) {
            StringContract.Color.primaryColor = var1;
         }

         public final int getPrimaryDarkColor() {
            return StringContract.Color.primaryDarkColor;
         }

         public final void setPrimaryDarkColor(int var1) {
            StringContract.Color.primaryDarkColor = var1;
         }

         public final int getAccentColor() {
            return StringContract.Color.accentColor;
         }

         public final void setAccentColor(int var1) {
            StringContract.Color.accentColor = var1;
         }

         public final int getRightMessageColor() {
            return StringContract.Color.rightMessageColor;
         }

         public final void setRightMessageColor(int var1) {
            StringContract.Color.rightMessageColor = var1;
         }

         public final int getLeftMessageColor() {
            return StringContract.Color.leftMessageColor;
         }

         public final void setLeftMessageColor(int var1) {
            StringContract.Color.leftMessageColor = var1;
         }

         public final int getIconTint() {
            return StringContract.Color.iconTint;
         }

         public final void setIconTint(int var1) {
            StringContract.Color.iconTint = var1;
         }

         public final int getWhite() {
            return StringContract.Color.white;
         }

         public final void setWhite(int var1) {
            StringContract.Color.white = var1;
         }

         public final int getBlack() {
            return StringContract.Color.black;
         }

         public final void setBlack(int var1) {
            StringContract.Color.black = var1;
         }

         public final int getGrey() {
            return StringContract.Color.grey;
         }

         public final void setGrey(int var1) {
            StringContract.Color.grey = var1;
         }

         public final int getInactiveColor() {
            return StringContract.Color.inactiveColor;
         }

         public final void setInactiveColor(int var1) {
            StringContract.Color.inactiveColor = var1;
         }

       /*  private Companion() {
         }

         // $FF: synthetic method
         public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
         }*/
      }
   }

   public static final class Dimensions {
      private static int marginStart = 16;
      private static int marginEnd = 16;
      private static float cardViewCorner = 24.0F;
      private static float cardViewElevation = 8.0F;
      public static final StringContract.Dimensions.Companion Companion = new StringContract.Dimensions.Companion(null);

      public static final class Companion {
         public Companion(Object o) {
         }

         public final int getMarginStart() {
            return StringContract.Dimensions.marginStart;
         }

         public final void setMarginStart(int var1) {
            StringContract.Dimensions.marginStart = var1;
         }

         public final int getMarginEnd() {
            return StringContract.Dimensions.marginEnd;
         }

         public final void setMarginEnd(int var1) {
            StringContract.Dimensions.marginEnd = var1;
         }

         public final float getCardViewCorner() {
            return StringContract.Dimensions.cardViewCorner;
         }

         public final void setCardViewCorner(float var1) {
            StringContract.Dimensions.cardViewCorner = var1;
         }

         public final float getCardViewElevation() {
            return StringContract.Dimensions.cardViewElevation;
         }

         public final void setCardViewElevation(float var1) {
            StringContract.Dimensions.cardViewElevation = var1;
         }

      }
   }

   public static final class ListenerName {
      @NotNull
      private static final String MESSAGE_LISTENER = "message_listener";
      @NotNull
      private static final String USER_LISTENER = "user_listener";
      @NotNull
      private static final String GROUP_EVENT_LISTENER = "group_event_listener";
      @NotNull
      private static final String CALL_EVENT_LISTENER = "call_event_listener";
      public static final StringContract.ListenerName.Companion Companion = new StringContract.ListenerName.Companion(null);


      public static final class Companion {
         public Companion(Object o) {

         }

         @NotNull
         public final String getMESSAGE_LISTENER() {
            return StringContract.ListenerName.MESSAGE_LISTENER;
         }

         @NotNull
         public final String getUSER_LISTENER() {
            return StringContract.ListenerName.USER_LISTENER;
         }

         @NotNull
         public final String getGROUP_EVENT_LISTENER() {
            return StringContract.ListenerName.GROUP_EVENT_LISTENER;
         }

         @NotNull
         public final String getCALL_EVENT_LISTENER() {
            return StringContract.ListenerName.CALL_EVENT_LISTENER;
         }
      }
   }


   public static final class RequestPermission {
      @NotNull
      private static final String[] RECORD_PERMISSION = new String[]{"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"};
      @NotNull
      private static final String[] CAMERA_PERMISSION = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
      @NotNull
      private static final String[] VIDEO_CALL_PERMISSION = new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
      @NotNull
      private static final String[] STORAGE_PERMISSION = new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"};
      public static final StringContract.RequestPermission.Companion Companion = new StringContract.RequestPermission.Companion(null);

      public static final class Companion {
         public Companion(Object o) {

         }

         @NotNull
         public final String[] getRECORD_PERMISSION() {
            return StringContract.RequestPermission.RECORD_PERMISSION;
         }

         @NotNull
         public final String[] getCAMERA_PERMISSION() {
            return StringContract.RequestPermission.CAMERA_PERMISSION;
         }

         @NotNull
         public final String[] getVIDEO_CALL_PERMISSION() {
            return StringContract.RequestPermission.VIDEO_CALL_PERMISSION;
         }

         @NotNull
         public final String[] getSTORAGE_PERMISSION() {
            return StringContract.RequestPermission.STORAGE_PERMISSION;
         }
      }
   }

   public static final class RequestCode {
      private static final int ADD_GALLERY = 1;
      private static final int ADD_DOCUMENT = 2;
      private static final int ADD_SOUND = 3;
      private static final int TAKE_PHOTO = 5;
      private static final int LOCATION = 15;
      private static final int TAKE_VIDEO = 7;
      private static final int LEFT = 8;
      private static final int RECORD_CODE = 10;
      private static final int VIDEO_CALL = 12;
      private static final int VOICE_CALL = 24;
      private static final int FILE_WRITE = 25;
      public static final StringContract.RequestCode.Companion Companion = new StringContract.RequestCode.Companion(null);

      public static final class Companion {
         public Companion(Object o) {

         }

         public final int getADD_GALLERY() {
            return StringContract.RequestCode.ADD_GALLERY;
         }

         public final int getADD_DOCUMENT() {
            return StringContract.RequestCode.ADD_DOCUMENT;
         }

         public final int getADD_SOUND() {
            return StringContract.RequestCode.ADD_SOUND;
         }

         public final int getTAKE_PHOTO() {
            return StringContract.RequestCode.TAKE_PHOTO;
         }

         public final int getLOCATION() {
            return StringContract.RequestCode.LOCATION;
         }

         public final int getTAKE_VIDEO() {
            return StringContract.RequestCode.TAKE_VIDEO;
         }

         public final int getLEFT() {
            return StringContract.RequestCode.LEFT;
         }

         public final int getRECORD_CODE() {
            return StringContract.RequestCode.RECORD_CODE;
         }

         public final int getVIDEO_CALL() {
            return StringContract.RequestCode.VIDEO_CALL;
         }

         public final int getVOICE_CALL() {
            return StringContract.RequestCode.VOICE_CALL;
         }

         public final int getFILE_WRITE() {
            return StringContract.RequestCode.FILE_WRITE;
         }

      }
   }
}
