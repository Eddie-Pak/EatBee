plugins {
    `kotlin-dsl`
}

group = "com.eatbee.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    jvmToolchain(21)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.hilt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "eatbee.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "eatbee.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = "eatbee.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "eatbee.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidNetwork") {
            id = "eatbee.android.network"
            implementationClass = "AndroidNetworkConventionPlugin"
        }
        register("jvmLibrary") {
            id = "eatbee.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}