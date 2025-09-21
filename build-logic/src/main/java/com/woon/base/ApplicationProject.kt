package com.woon.base

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project

internal fun Project.applicationProject(
    applicationExtension: ApplicationExtension
){
    applicationExtension.apply {
        namespace = "com.woon.kakaobooksearch"
        compileSdk = 36

        defaultConfig {
            applicationId = "com.woon.kakaobooksearch"
            minSdk = 24
            targetSdk = 36
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
}