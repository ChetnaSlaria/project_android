# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class gurtek.mrgurtekbase.adapter.BaseViewHolder { *; }

# keep the specified class members from being removed or renamed
# only if the class is preserved
-keepclassmembers class gurtek.mrgurtekbase.adapter.BaseViewHolder { *; }

# keep the class and specified members from being renamed only
-keepnames class gurtek.mrgurtekbase.adapter.BaseViewHolder { *; }

# keep the specified class members from being renamed only
-keepclassmembernames class gurtek.mrgurtekbase.adapter.BaseViewHolder { *; }

 -keepattributes Signature
 -keepattributes *Annotation*
 -keep class okhttp3.** { *; }
 -keep interface okhttp3.** { *; }
 -dontwarn okhttp3.**
 -keep class sun.misc.Unsafe { *; }
 -dontwarn java.nio.file.*
 -dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

  -dontwarn retrofit.**
  -keep class retrofit.** { *; }
  -keepattributes Signature
  -keepattributes Exceptions

  -keep class sun.misc.Unsafe { *; }
   -keep class com.google.gson.stream.** { *; }