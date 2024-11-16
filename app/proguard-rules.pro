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

-keep class com.google.android.** { *; }
-keep class com.google.firebase.** { *; }
-keep class org.springframework.** { *; }
-keep class com.matheusvalbert.programmercalculator.core.event.** { *; }
-keep class com.matheusvalbert.programmercalculator.core.usecase.CalculatorUseCases
-keep class com.google.android.play.core.ktx.** { *; }
-keep class com.google.android.play.core.tasks.** { *; }
-keep class com.google.android.play.core.common.PlayCoreDialogWrapperActivity
-dontwarn io.**
-dontwarn java.**
-dontwarn javax.**
-dontwarn jdk.**
-dontwarn joptsimple.**
-dontwarn kotlin.**
-dontwarn org.**
-dontwarn reactor.**
-dontwarn rx.**
-dontwarn com.google.**