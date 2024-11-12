plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.retrofit1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.retrofit1"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kapt {
        correctErrorTypes = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

//View model
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.lifecycle.viewmodel.compose4)
    implementation (libs.androidx.lifecycle.lifecycle.livedata.ktx5)
    implementation (libs.androidx.lifecycle.lifecycle.common.java85)

    // networking
    implementation (libs.retrofit)
    implementation (libs.converter.moshi)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation (libs.moshi.kotlin)
    ///kapt("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")
    implementation (libs.hilt.android.v252)
    kapt (libs.hilt.android.compiler.v252)
    implementation (libs.androidx.hilt.navigation.compose)

    // coil
    implementation(libs.coil.compose)

    // dagger -hilt
    implementation (libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.moshi.kotlin.codegen)

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.8.3")


    // hilt navigation

    //kapt ("com.google.dagger:hilt-compiler:2.48.1")

}