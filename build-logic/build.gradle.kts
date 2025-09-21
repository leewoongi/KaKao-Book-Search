import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}


// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("Application") {
            id = "kakaobooksearch.application"
            implementationClass = "com.woon.plugin.ApplicationPlugin"
        }

        register("Feature") {
            id = "kakaobooksearch.feature"
            implementationClass = "com.woon.plugin.FeaturePlugin"
        }

        register("Domain") {
            id = "kakaobooksearch.domain"
            implementationClass = "com.woon.plugin.DomainPlugin"
        }

        register("Data") {
            id = "kakaobooksearch.data"
            implementationClass = "com.woon.plugin.DataPlugin"
        }
    }
}
