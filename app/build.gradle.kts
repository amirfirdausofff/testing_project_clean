plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.demo.testingproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.demo.testingproject"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    flavorDimensions += "mode"

    productFlavors {
        create("dev") {
            val baseUrl = "https://latest.services.cloud.thenoor.co/app/"
            dimension = "mode"
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        }
        create("uat") {
            val baseUrl = "https://latest.services.cloud.thenoor.co/app/"
            dimension = "mode"
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        }
        create("prod") {
            val baseUrl = "https://latest.services.cloud.thenoor.co/app/"
            dimension = "mode"
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.databinding:viewbinding:8.4.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // Koin core features
    implementation("io.insert-koin:koin-android:3.5.3")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    implementation("com.squareup.okhttp3:okhttp:3.14.7")
    implementation("com.squareup.okhttp3:logging-interceptor:3.14.7")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:3.14.7")
}