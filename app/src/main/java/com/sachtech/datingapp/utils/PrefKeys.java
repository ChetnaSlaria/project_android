package com.sachtech.datingapp.utils;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class PrefKeys {
   @NotNull
   private static String PREF_MARRIAGE_PLANS = "prefMarriagePlans";
   @NotNull
   private static String PREF_LIFE_AFTER_MARRIAGE = "prefLifeAfterMarriage";
   @NotNull
   private static String PREF_PRAYS = "prefPrays";
   @NotNull
   private static String PREF_HALAL = "prefHalal";
   @NotNull
   private static String PREF_WEAR = "prefWear";
   @NotNull
   private static String PREF_BEARD = "prefBeard";
   @NotNull
   private static String PREF_REVERT = "prefRevert";
   @NotNull
   private static String PREF_QURAN = "prefQuran";
   @NotNull
   private static String SWIPECOUNT = "swipeCount";
   @NotNull
   private static String PREF_EDUCATION = "prefEducation";
   @NotNull
   private static String PREF_PROFESSION = "prefProfession";
   @NotNull
   private static String PREF_HAIRCOLOR = "prefHairColor";
   @NotNull
   private static String PREF_COLORCOMPLEXION = "prefColorComplexion";
   @NotNull
   private static String PREF_EYECOLOR = "prefEyeColor";
   @NotNull
   private static String PREF_ORIGIN = "prefOrigin";
   @NotNull
   private static String LOCATION = "location";
   @NotNull
   private static String USERPREFS = "user_preferences";
   @NotNull
   private static String PASSWORD = "password";
   @NotNull
   private static String EMAIL = "email";
   @NotNull
   private static String USERNAME = "user_name";
   @NotNull
   private static String ALLUSERS = "all_users";
   @NotNull
   private static String LANGUAGE = "language";
   @NotNull
   private static String SELECTEDUSER = "selected_user";
   @NotNull
   private static String SELECTEDCHATUSER = "selected_chat_user";
   @NotNull
   public static final String COMETUSER = "comet_user";
   @NotNull
   public static final String LATITUDE = "latitude";
   @NotNull
   public static final String LONGITUDE = "longitude";
   @NotNull
   public static final String NATIONALITY = "nationality";
   @NotNull
   public static final String EDUCATION = "education";
   @NotNull
   private static String PROFESSION = "profession";
   @NotNull
   private static String EYECOLOR = "eye_color";
   @NotNull
   private static String HAIRCOLOR = "hair_color";
   @NotNull
   private static String COLORCOMPLEXION = "color_complexion";
   @NotNull
   private static String ORIGIN = "origin";
   @NotNull
   private static String GOOGLEUSER = "google_user";
   @NotNull
   private static String USERS = "users";
   @NotNull
   private static String FACEBOOKUSER = "fb_user";
   @NotNull
   private static String USER = "userTTT";
   @NotNull
   private static String UID = "uid";
   @NotNull
   private static String FCM = "fcm";
   @NotNull
   private static String isDataSaved = "isDataSaved";
   @NotNull
   private static String LOOKINGFOR = "lookingFor";
   public static final PrefKeys INSTANCE;

   @NotNull
   public final String getPREF_MARRIAGE_PLANS() {
      return PREF_MARRIAGE_PLANS;
   }

   @NotNull
   public final String getPREF_LIFE_AFTER_MARRIAGE() {
      return PREF_LIFE_AFTER_MARRIAGE;
   }

   @NotNull
   public final String getPREF_PRAYS() {
      return PREF_PRAYS;
   }

   @NotNull
   public final String getPREF_HALAL() {
      return PREF_HALAL;
   }

   @NotNull
   public final String getPREF_WEAR() {
      return PREF_WEAR;
   }

   @NotNull
   public final String getPREF_BEARD() {
      return PREF_BEARD;
   }

   @NotNull
   public final String getPREF_REVERT() {
      return PREF_REVERT;
   }

   @NotNull
   public final String getPREF_QURAN() {
      return PREF_QURAN;
   }

   @NotNull
   public final String getSWIPECOUNT() {
      return SWIPECOUNT;
   }

   @NotNull
   public final String getPREF_EDUCATION() {
      return PREF_EDUCATION;
   }

   @NotNull
   public final String getPREF_PROFESSION() {
      return PREF_PROFESSION;
   }

   @NotNull
   public final String getPREF_HAIRCOLOR() {
      return PREF_HAIRCOLOR;
   }

   @NotNull
   public final String getPREF_COLORCOMPLEXION() {
      return PREF_COLORCOMPLEXION;
   }

   @NotNull
   public final String getPREF_EYECOLOR() {
      return PREF_EYECOLOR;
   }

   @NotNull
   public final String getPREF_ORIGIN() {
      return PREF_ORIGIN;
   }

   @NotNull
   public final String getLOCATION() {
      return LOCATION;
   }

   @NotNull
   public final String getUSERPREFS() {
      return USERPREFS;
   }

   @NotNull
   public final String getPASSWORD() {
      return PASSWORD;
   }

   @NotNull
   public final String getEMAIL() {
      return EMAIL;
   }

   @NotNull
   public final String getUSERNAME() {
      return USERNAME;
   }

   @NotNull
   public final String getALLUSERS() {
      return ALLUSERS;
   }

   @NotNull
   public final String getLANGUAGE() {
      return LANGUAGE;
   }

   @NotNull
   public final String getSELECTEDUSER() {
      return SELECTEDUSER;
   }

   @NotNull
   public final String getSELECTEDCHATUSER() {
      return SELECTEDCHATUSER;
   }

   @NotNull
   public final String getPROFESSION() {
      return PROFESSION;
   }

   @NotNull
   public final String getEYECOLOR() {
      return EYECOLOR;
   }

   @NotNull
   public final String getHAIRCOLOR() {
      return HAIRCOLOR;
   }

   @NotNull
   public final String getCOLORCOMPLEXION() {
      return COLORCOMPLEXION;
   }

   @NotNull
   public final String getORIGIN() {
      return ORIGIN;
   }

   @NotNull
   public final String getGOOGLEUSER() {
      return GOOGLEUSER;
   }

   @NotNull
   public final String getUSERS() {
      return USERS;
   }

   @NotNull
   public final String getFACEBOOKUSER() {
      return FACEBOOKUSER;
   }

   @NotNull
   public final String getUSER() {
      return USER;
   }

   @NotNull
   public final String getUID() {
      return UID;
   }

   @NotNull
   public final String getFCM() {
      return FCM;
   }

   @NotNull
   public final String isDataSaved() {
      return isDataSaved;
   }

   @NotNull
   public final String getLOOKINGFOR() {
      return LOOKINGFOR;
   }

   private PrefKeys() {
   }

   static {
      PrefKeys var0 = new PrefKeys();
      INSTANCE = var0;
      PREF_MARRIAGE_PLANS = "prefMarriagePlans";
      PREF_LIFE_AFTER_MARRIAGE = "prefLifeAfterMarriage";
      PREF_PRAYS = "prefPrays";
      PREF_HALAL = "prefHalal";
      PREF_WEAR = "prefWear";
      PREF_BEARD = "prefBeard";
      PREF_REVERT = "prefRevert";
      PREF_QURAN = "prefQuran";
      SWIPECOUNT = "swipeCount";
      PREF_EDUCATION = "prefEducation";
      PREF_PROFESSION = "prefProfession";
      PREF_HAIRCOLOR = "prefHairColor";
      PREF_COLORCOMPLEXION = "prefColorComplexion";
      PREF_EYECOLOR = "prefEyeColor";
      PREF_ORIGIN = "prefOrigin";
      LOCATION = "location";
      USERPREFS = "user_preferences";
      PASSWORD = "password";
      EMAIL = "email";
      USERNAME = "user_name";
      ALLUSERS = "all_users";
      LANGUAGE = "language";
      SELECTEDUSER = "selected_user";
      SELECTEDCHATUSER = "selected_chat_user";
      PROFESSION = "profession";
      EYECOLOR = "eye_color";
      HAIRCOLOR = "hair_color";
      COLORCOMPLEXION = "color_complexion";
      ORIGIN = "origin";
      GOOGLEUSER = "google_user";
      USERS = "users";
      FACEBOOKUSER = "fb_user";
      USER = "userTTT";
      UID = "uid";
      FCM = "fcm";
      isDataSaved = "isDataSaved";
      LOOKINGFOR = "lookingFor";
   }
}
