plugins {
    alias(libs.plugins.eatbee.android.library)
    alias(libs.plugins.eatbee.android.hilt)
    alias(libs.plugins.eatbee.android.network)
    alias(libs.plugins.eatbee.android.dataStore)
    alias(libs.plugins.eatbee.android.room)
}

android {
    namespace = "com.eatbee.data"
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.play.services.location)
    implementation(libs.kotlinx.coroutines.play.services)
}