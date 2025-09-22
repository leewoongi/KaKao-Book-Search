plugins {
    id("kakaobooksearch.data")
}

android{
    namespace = "com.woon.data.repository"
}

dependencies {
    implementation(project(":data:datasource"))
}