/*
package com.sachtech.datingapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;
import com.sachtech.datingapp.app.DatingApp;
import com.sachtech.datingapp.extensions.CommonsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class PreferencesKt {
   private static final SharedPreferences prefs;

   public static final SharedPreferences getPrefs() {
      return prefs;
   }

   public static final void setPref(@NotNull String key, @Nullable Object value) {
      Editor editor = prefs.edit();
      if (value instanceof String) {
         if (editor != null) {
            editor.putString(key, (String)value);
         }
      } else if (value instanceof Integer) {
         if (editor != null) {
            editor.putInt(key, ((Number)value).intValue());
         }
      } else if (value instanceof Long) {
         if (editor != null) {
            editor.putLong(key, ((Number)value).longValue());
         }
      } else if (value instanceof Boolean) {
         if (editor != null) {
            editor.putBoolean(key, (Boolean)value);
         }
      } else if (value instanceof Float) {
         if (editor != null) {
            editor.putFloat(key, ((Number)value).floatValue());
         }
      }

      if (editor != null) {
         editor.apply();
      }

   }

   // $FF: synthetic method
   @Nullable
   public static final Object get(@NotNull String key, @Nullable Object defaultValue) throws Throwable {
      KClass var3 = Reflection.getOrCreateKotlinClass(Object.class);
      SharedPreferences var10000;
      Object var10002;
      Object var7;
      if (Intrinsics.areEqual(var3, Reflection.getOrCreateKotlinClass(String.class))) {
         var10000 = getPrefs();
         String var6;
         if (var10000 != null) {
            var10002 = defaultValue;
            if (!(defaultValue instanceof String)) {
               var10002 = null;
            }

            var6 = var10000.getString(key, (String)var10002);
         } else {
            var6 = null;
         }

         var7 = (Object)var6;
      } else if (Intrinsics.areEqual(var3, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
         var10000 = getPrefs();
         Integer var8;
         if (var10000 != null) {
            var10002 = defaultValue;
            if (!(defaultValue instanceof Integer)) {
               var10002 = null;
            }

            var8 = var10000.getInt(key, (Integer)var10002 != null ? (Integer)var10002 : 1);
         } else {
            var8 = null;
         }

         var7 = (Object)var8;
      } else if (Intrinsics.areEqual(var3, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
         var10000 = getPrefs();
         Boolean var9;
         if (var10000 != null) {
            var10002 = defaultValue;
            if (!(defaultValue instanceof Boolean)) {
               var10002 = null;
            }

            var9 = var10000.getBoolean(key, (Boolean)var10002 != null ? (Boolean)var10002 : false);
         } else {
            var9 = null;
         }

         var7 = (Object)var9;
      } else if (Intrinsics.areEqual(var3, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
         var10000 = getPrefs();
         Float var4;
         if (var10000 != null) {
            var10002 = defaultValue;
            if (!(defaultValue instanceof Float)) {
               var10002 = null;
            }

            var4 = var10000.getFloat(key, (Float)var10002 != null ? (Float)var10002 : -1.0F);
         } else {
            var4 = null;
         }

         var7 = (Object)var4;
      } else {
         if (!Intrinsics.areEqual(var3, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            throw (Throwable)(new UnsupportedOperationException("Not yet implemented"));
         }

         var10000 = getPrefs();
         Long var5;
         if (var10000 != null) {
            var10002 = defaultValue;
            if (!(defaultValue instanceof Long)) {
               var10002 = null;
            }

            var5 = var10000.getLong(key, (Long)var10002 != null ? (Long)var10002 : -1L);
         } else {
            var5 = null;
         }

         var7 = (Object)var5;
      }

      return var7;
   }

   public static final void clearPref() {
      Editor editor = prefs.edit();
      if (editor != null) {
         Editor var10000 = editor.clear();
         if (var10000 != null) {
            var10000.apply();
         }
      }

   }

   public static final void clearPrefKey(@NotNull String key) {
      Editor editor = prefs.edit();
      editor.remove(key);
      if (editor != null) {
         editor.apply();
      }

   }

   // $FF: synthetic method
   public static final void setprefObject(@NotNull String key, @Nullable Object obj) {
      if (CommonsKt.isNull(obj)) {
         clearPrefKey(key);
      } else {
         setPref(key, (new Gson()).toJson(obj));
      }

   }

   @Nullable
   public static final Object getprefObject(@NotNull String key) {
      Gson var10000 = new Gson();
      Object defaultValue$iv = "";
      Gson var5 = var10000;
      boolean get = false;
      KClass var4 = Reflection.getOrCreateKotlinClass(String.class);
      SharedPreferences var8;
      String var9;
      if (Intrinsics.areEqual(var4, Reflection.getOrCreateKotlinClass(String.class))) {
         var8 = getPrefs();
         var9 = var8 != null ? var8.getString(key, defaultValue$iv) : null;
      } else {
         String var10002;
         if (Intrinsics.areEqual(var4, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            var8 = getPrefs();
            Integer var10;
            if (var8 != null) {
               var10002 = defaultValue$iv;
               if (!(defaultValue$iv instanceof Integer)) {
                  var10002 = null;
               }

               var10 = var8.getInt(key, (Integer)var10002 != null ? (Integer)var10002 : 1);
            } else {
               var10 = null;
            }

            var9 = (String)var10;
         } else if (Intrinsics.areEqual(var4, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            var8 = getPrefs();
            Boolean var11;
            if (var8 != null) {
               var10002 = defaultValue$iv;
               if (!(defaultValue$iv instanceof Boolean)) {
                  var10002 = null;
               }

               var11 = var8.getBoolean(key, (Boolean)var10002 != null ? (Boolean)var10002 : false);
            } else {
               var11 = null;
            }

            var9 = (String)var11;
         } else if (Intrinsics.areEqual(var4, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
            var8 = getPrefs();
            Float var12;
            if (var8 != null) {
               var10002 = defaultValue$iv;
               if (!(defaultValue$iv instanceof Float)) {
                  var10002 = null;
               }

               var12 = var8.getFloat(key, (Float)var10002 != null ? (Float)var10002 : -1.0F);
            } else {
               var12 = null;
            }

            var9 = (String)var12;
         } else {
            if (!Intrinsics.areEqual(var4, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
               throw (Throwable)(new UnsupportedOperationException("Not yet implemented"));
            }

            var8 = getPrefs();
            Long var7;
            if (var8 != null) {
               var10002 = defaultValue$iv;
               if (!(defaultValue$iv instanceof Long)) {
                  var10002 = null;
               }

               var7 = var8.getLong(key, (Long)var10002 != null ? (Long)var10002 : -1L);
            } else {
               var7 = null;
            }

            var9 = (String)var7;
         }
      }

      String var6 = var9;
      Intrinsics.reifiedOperationMarker(4, "T");
      return var5.fromJson(var6, Object.class);
   }

   static {
      Context var10000 = DatingApp.application;
      Intrinsics.checkExpressionValueIsNotNull(var10000, "DatingApp.application");
      prefs = var10000.getApplicationContext().getSharedPreferences("DatingYOOOOO", 0);
   }
}
*/
