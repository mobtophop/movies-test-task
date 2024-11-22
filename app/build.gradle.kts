plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "2.0.21"
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0-RC2"
}

private val API_ENDPOINT = "ENDPOINT"
private val MOVIE_TOKEN = "MOVIE_TOKEN"
private val apiEndpoint = "\"https://api.themoviedb.org/3/\""
private val apiToken = "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5MjZhMjE1MDA1NTg3NzkzYTE5NzQ4ZGMxZDFkMzQ1YSIsIm5iZiI6MTczMTk4NDc1OS43ODA3ODQ0LCJzdWIiOiI2NzNhYWEwNDljMTZkYWZhMDZmOWUxOWYiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.tnDZx_xC18FUOM0rUgI3GaBA-qRbRPquq6u-bcm_8RI\""

android {
    namespace = "com.example.moviestesttask"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.moviestesttask"
        minSdk = 26
        targetSdk = 35
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
            buildConfigField("String", API_ENDPOINT, apiEndpoint)
            buildConfigField("String", MOVIE_TOKEN, apiToken)
        }
        debug {
            buildConfigField("String", API_ENDPOINT, apiEndpoint)
            buildConfigField("String", MOVIE_TOKEN, apiToken)
        }
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
        buildConfig = true
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
	implementation(libs.androidx.material3.android)
	testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Dependency Injection
    implementation(libs.hilt.android.v2511)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    // Network
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.logging.interceptor)

    // Paging 3
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)

    // Async
    implementation(libs.coil.compose)

    // Room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    kapt(libs.compiler)
    kapt(libs.androidx.lifecycle.common)
    implementation(libs.androidx.room.ktx)

    // Shimmer
    implementation(libs.compose.shimmer)

    // Test
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.room.testing)
    androidTestImplementation(libs.androidx.junit.v113)
    androidTestImplementation(libs.androidx.espresso.core.v340)
}

kapt {
    correctErrorTypes = true
}