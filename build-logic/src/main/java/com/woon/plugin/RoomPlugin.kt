package com.woon.plugin

import androidx.room.gradle.RoomExtension
import com.woon.ext.findLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


class RoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply {
                apply("androidx.room")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                "implementation"(findLibrary("room-runtime"))
                "implementation"(findLibrary("room-ktx"))
                "implementation"(findLibrary("room-paging"))
                "implementation"(findLibrary("room-testing"))
                add("ksp", findLibrary("room-compiler"))
            }
        }
    }
}