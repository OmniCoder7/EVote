import org.gradle.kotlin.dsl.credentials

plugins {
    alias(libs.plugins.evote.android.feature)
    alias(libs.plugins.evote.android.library.compose)
}

android {
    namespace = "com.ethyllium.root"

    buildTypes {
        release {
            buildConfigField("String", "CLIENT_ID", "\"830173247881-k03onp7masm10r63mprseg37hlql64dk.apps.googleusercontent.com\"")
        }
        debug {
            buildConfigField("String", "CLIENT_ID", "\"830173247881-k03onp7masm10r63mprseg37hlql64dk.apps.googleusercontent.com\"")
        }
    }

}

dependencies {
    implementation(libs.androidx.animation)
    implementation(libs.androidx.animation.graphics)

    implementation(project(":domain"))

    //Google SSO
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
}