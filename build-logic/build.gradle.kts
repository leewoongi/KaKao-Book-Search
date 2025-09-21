import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

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

        register("Hilt") {
            id = "kakaobooksearch.hilt"
            implementationClass = "com.woon.plugin.HiltPlugin"
        }
    }
}
