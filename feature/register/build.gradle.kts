plugins {
    alias(libs.plugins.evote.android.feature)
    alias(libs.plugins.evote.android.library.compose)
}

android {
    namespace = "com.ethyllium.register"
}

dependencies {
    implementation(libs.androidx.animation)
    implementation(libs.androidx.animation.graphics)

    implementation(project(":domain"))
}