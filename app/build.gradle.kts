plugins {
    alias(libs.plugins.evote.android.application.compose)
    alias(libs.plugins.evote.android.application)
    alias(libs.plugins.evote.android.koin)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.ethyllium.evote"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ethyllium.evote"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(project(":feature:login"))
    implementation(project(":feature:register"))
    implementation(project(":feature:root"))
    implementation(project(":feature:result"))
    implementation(project(":feature:upcoming"))
    implementation(project(":feature:invite"))
    implementation(project(":feature:home"))
    implementation(project(":domain"))
    implementation(project(":core:data"))

    implementation(libs.navigation.compose)
    implementation(libs.navigation.runtime.ktx)
    implementation(libs.androidx.material)

    implementation(libs.androidx.core.splashscreen)

    // Test
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)

    // Serialization
    implementation(libs.kotlinx.serialization.json)
}