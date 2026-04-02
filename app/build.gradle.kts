plugins {
    alias(libs.plugins.eatbee.android.application)
    alias(libs.plugins.eatbee.android.hilt)
    alias(libs.plugins.secrets.gradle.plugin)
}

android {
    namespace = "com.eatbee.app"

    buildFeatures {
        buildConfig = true
    }
}

secrets {
    propertiesFileName = "local.properties"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":presentation"))
}