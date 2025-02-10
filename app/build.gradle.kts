plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("org.jlleitschuh.gradle.ktlint")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.fetchrewards"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.fetchrewards"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    // Apply ktlint to check code style during the build process
    tasks.named("check") {
        dependsOn("ktlintCheck")
    }

    tasks.named("check").configure {
        dependsOn("ktlintCheck")
    }
}

tasks.named("check") {
    dependsOn("ktlintCheck")
}

tasks.named("check").configure {
    dependsOn("ktlintCheck")
}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Navigation Jetpack
    implementation(libs.androidx.navigation.compose)

    // Splash API
    implementation(libs.androidx.core.splashscreen)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Material Design 3
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material)

    // Android Studio Preview support
    implementation(libs.androidx.ui.tooling.preview)

    // Compose UI
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.foundation)
    implementation(libs.ui)
    implementation(libs.androidx.compose.ui.ui)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Koin for Android compose
    // implementation(project.dependencies.platform("io.insert-koin:koin-bom:3.5.0"))
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose.v343)

    // Moshi
    // implementation(libs.moshi.v150)
    implementation(libs.retrofit2.converter.moshi) // Moshi converter for Retrofit
    implementation(libs.squareup.moshi.v1130) // Moshi core
    implementation(libs.moshi.kotlin) // Moshi Kotlin adapter
    ksp(libs.moshi.kotlin.codegen.v1150)

    // Unit testing dependencies
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)

    // Koin test dependencies
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit4)
}
