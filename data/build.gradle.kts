plugins {
    alias(libs.plugins.eatbee.android.library)
    alias(libs.plugins.eatbee.android.hilt)
    alias(libs.plugins.eatbee.android.network)
}

android {
    namespace = "com.eatbee.data"
}

dependencies {
    implementation(project(":domain"))
}