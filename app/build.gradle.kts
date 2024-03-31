plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("dagger.hilt.android.plugin")
  id("com.google.gms.google-services")
  id("com.google.firebase.crashlytics")
  kotlin("kapt")
}

android {
  namespace = "com.matheusvalbert.programmercalculator"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.matheusvalbert.programmercalculator"
    minSdk = 28
    targetSdk = 34
    versionCode = 4
    versionName = "1.1.1"

    multiDexEnabled = true

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }

    packaging {
      resources.excludes.add("META-INF/license.txt")
      resources.excludes.add("META-INF/notice.txt")
    }
  }

  buildTypes {
    release {
      isDebuggable = false
      isMinifyEnabled = true
      isShrinkResources = true
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
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.4.3"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
  implementation("androidx.activity:activity-compose:1.8.2")
  implementation(platform("androidx.compose:compose-bom:2023.03.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3")
  implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")
  implementation("com.google.dagger:hilt-android:2.44.2")
  kapt("com.google.dagger:hilt-android-compiler:2.44.2")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
  kapt("androidx.hilt:hilt-compiler:1.2.0")
  implementation("org.springframework:spring-expression:6.0.11")
  implementation("com.google.firebase:firebase-analytics-ktx")
  implementation("com.google.firebase:firebase-crashlytics-ktx")
  implementation("androidx.datastore:datastore-preferences-core:1.0.0")
  implementation("com.google.android.play:review-ktx:2.0.1")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest")
}

kapt {
  correctErrorTypes = true
}

hilt {
  enableAggregatingTask = true
}
