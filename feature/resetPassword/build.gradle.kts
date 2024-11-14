plugins {
    alias(libs.plugins.evote.android.feature)
    alias(libs.plugins.evote.android.library.compose)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.ethyllium.resetpassword"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.animation)
    implementation(libs.androidx.animation.graphics)

    implementation(project(":domain"))
}