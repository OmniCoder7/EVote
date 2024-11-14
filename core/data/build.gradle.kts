import org.gradle.kotlin.dsl.android

plugins {
    alias(libs.plugins.evote.android.library)
    alias(libs.plugins.evote.android.koin)
    alias(libs.plugins.evote.android.room)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.ethyllium.data"
    buildFeatures.buildConfig = true

    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", "\"http://172.17.5.170:8080/\"")
            buildConfigField("String", "KEY_ALIAS", "\"922f010a-c92e-48b2-a582-4988d76f983e\"")
            buildConfigField("String", "CLIENT_ID", "\"830173247881-k03onp7masm10r63mprseg37hlql64dk.apps.googleusercontent.com\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"http://172.17.5.170:8080/\"")
            buildConfigField("String", "KEY_ALIAS", "\"922f010a-c92e-48b2-a582-4988d76f983e\"")
            buildConfigField("String", "CLIENT_ID", "\"830173247881-k03onp7masm10r63mprseg37hlql64dk.apps.googleusercontent.com\"")
        }
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.room.paging)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.auth)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(platform(libs.ktor.client.bom))
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.ktor.client.gson)

    implementation(project(":domain"))

    // Test
    androidTestImplementation (libs.androidx.runner)
    androidTestImplementation (libs.androidx.rules)
    androidTestImplementation(libs.ui.test.junit4)

    // Data Store
    implementation(libs.androidx.datastore)
}