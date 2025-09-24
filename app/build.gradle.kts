plugins {
    id("kakaobooksearch.application")
}

dependencies {
    add("implementation", project(":feature:home"))
    add("implementation", project(":feature:main"))
    add("implementation", project(":feature:favorite"))
    add("implementation", project(":data:repository"))
    add("implementation", project(":data:datasource"))
}