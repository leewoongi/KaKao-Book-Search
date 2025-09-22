package com.woon.base

import com.android.build.api.dsl.CommonExtension
import com.woon.ext.findLibrary
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.glideProject(
    commonExtension: CommonExtension<*, *, *, *, *, *>
){
    commonExtension.apply {
        dependencies {
            "implementation"(findLibrary("glide"))
            "implementation"(findLibrary("glide-compiler"))
            "implementation"(findLibrary("glide-compose"))
        }
    }
}