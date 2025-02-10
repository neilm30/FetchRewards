// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false
}

buildscript {
    repositories {
        google()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath(libs.kotlin.gradle.plugin)
    }
}

