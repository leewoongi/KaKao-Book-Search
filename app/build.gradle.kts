plugins {
    id("kakaobooksearch.application")
}

dependencies {
    add("implementation", project(":feature:main"))
    add("implementation", project(":feature:detail"))
}