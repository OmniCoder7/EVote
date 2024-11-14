plugins {
    alias(libs.plugins.evote.android.feature)
    alias(libs.plugins.evote.android.library.compose)
}

android {
    namespace = "com.ethyllium.invite"
}

dependencies {
    implementation(project(":domain"))
    implementation(platform(libs.coil.bom))
    implementation(libs.coil.compose)
    implementation(libs.coil)
}