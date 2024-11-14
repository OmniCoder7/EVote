pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EVote"
include(":app")
include(":feature:login")
include(":feature:register")
include(":feature:home")
include(":core:data")
include(":domain")
include(":feature:root")
include(":feature:result")
include(":feature:invite")
include(":feature:upcoming")
