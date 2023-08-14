// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  id("com.android.application") version "8.1.0" apply false
  id("org.jetbrains.kotlin.android") version "1.8.10" apply false
  id("com.google.dagger.hilt.android") version "2.44.2" apply false
  id("com.google.gms.google-services") version "4.3.15" apply false
  id("com.google.firebase.crashlytics") version "2.9.6" apply false
  kotlin("kapt") version "1.8.10" apply false
}