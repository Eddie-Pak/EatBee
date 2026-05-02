plugins {
    alias(libs.plugins.eatbee.android.library)
    alias(libs.plugins.eatbee.android.compose)
    alias(libs.plugins.eatbee.android.coil)
    alias(libs.plugins.eatbee.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.eatbee.presentation"
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.naver.map.sdk)
    implementation(libs.kotlinx.serialization.json)
}