plugins {
    alias(libs.plugins.evote.android.feature)
    alias(libs.plugins.evote.android.library.compose)
}

android {
    namespace = "com.ethyllium.home"
}

dependencies {
    implementation(project(":domain"))
}