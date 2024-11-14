import org.gradle.kotlin.dsl.android

plugins {
    alias(libs.plugins.evote.android.library)
    alias(libs.plugins.evote.android.koin)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.ethyllium.domain"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}