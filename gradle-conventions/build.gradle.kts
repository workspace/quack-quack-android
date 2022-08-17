/*
 * Designed and developed by 2022 SungbinLand, Team Duckie
 *
 * [build.gradle.kts] created by Ji Sungbin on 22. 8. 14. 오전 12:51
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/sungbinland/quack-quack/blob/main/LICENSE
 */

plugins {
    `kotlin-dsl`
}

group = "land.sungbin.duckie.quackquack.plugin"

dependencies {
    implementation(libs.build.gradle)
    implementation(libs.build.kotlin)
}

gradlePlugin {
    val prefix = "quackquack"
    plugins {
        register("applicationPlugin") {
            id = "$prefix.application.plugin"
            implementationClass = "ApplicationPlugin"
        }
        register("applicationComposePlugin") {
            id = "$prefix.application.compose.plugin"
            implementationClass = "ApplicationComposePlugin"
        }
        register("applicationJacocoPlugin") {
            id = "$prefix.application.jacoco.plugin"
            implementationClass = "ApplicationJacocoPlugin"
        }
        register("libraryPlugin") {
            id = "$prefix.library.plugin"
            implementationClass = "LibraryPlugin"
        }
        register("libraryComposePlugin") {
            id = "$prefix.library.compose.plugin"
            implementationClass = "LibraryComposePlugin"
        }
        register("libraryComposeUiTestPlugin") {
            id = "$prefix.library.compose.uitest.plugin"
            implementationClass = "LibraryComposeUiTestPlugin"
        }
        register("libraryJacocoPlugin") {
            id = "$prefix.library.jacoco.plugin"
            implementationClass = "LibraryJacocoPlugin"
        }
        register("benchmarkPlugin") {
            id = "$prefix.benchmark.plugin"
            implementationClass = "BenchmarkPlugin"
        }
        register("projectJacoco") {
            id = "$prefix.project.jacoco.plugin"
            implementationClass = "ProjectJacocoPlugin"
        }
        register("lint") {
            id = "$prefix.lint"
            implementationClass = "LintPlugin"
        }
    }
}