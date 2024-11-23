plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("dagger.hilt.android.plugin")
  id("com.google.gms.google-services")
  id("com.google.firebase.crashlytics")
  id("org.jetbrains.kotlin.plugin.compose")
  id("com.google.devtools.ksp")
}

android {
  namespace = "com.matheusvalbert.programmercalculator"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.matheusvalbert.programmercalculator"
    minSdk = 21
    targetSdk = 35
    versionCode = 14
    versionName = "1.3.1"

    packaging {
      resources.excludes.add("META-INF/license.txt")
      resources.excludes.add("META-INF/notice.txt")
    }
  }

  buildTypes {
    release {
      isDebuggable = false
      isMinifyEnabled = true
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
    debug {
      isDebuggable = true
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }

  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.15"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
  implementation("androidx.core:core-ktx:1.15.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
  implementation("androidx.activity:activity-compose:1.9.3")
  implementation(platform("androidx.compose:compose-bom:2024.11.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3")
  implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")
  implementation("com.google.dagger:hilt-android:2.52")
  ksp("com.google.dagger:hilt-android-compiler:2.52")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
  ksp("androidx.hilt:hilt-compiler:1.2.0")
  //noinspection GradleDependency need to be 6.0.*, to support SDK 21
  implementation("org.springframework:spring-expression:6.0.23")
  implementation("com.google.firebase:firebase-analytics-ktx")
  implementation("com.google.firebase:firebase-crashlytics-ktx")
  implementation("androidx.datastore:datastore-preferences:1.1.1")
  implementation("com.google.android.play:review-ktx:2.0.2")
}

hilt {
  enableAggregatingTask = true
}
