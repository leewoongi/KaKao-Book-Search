plugins {
    id("kakaobooksearch.feature")
}

android{
    namespace = "com.woon.main"
}

dependencies {
    implementation(project(":feature:home"))
    implementation(project(":feature:favorite"))
    implementation(project(":feature:detail"))
}