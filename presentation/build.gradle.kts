plugins {
    alias(libs.plugins.eatbee.android.library)
    alias(libs.plugins.eatbee.android.compose)
    alias(libs.plugins.eatbee.android.hilt)
}

android {
    namespace = "com.eatbee.presentation"
}

dependencies {
    implementation(project(":domain"))
}