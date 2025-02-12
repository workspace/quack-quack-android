/*
 * Designed and developed by 2022 SungbinLand, Team Duckie
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/master/LICENSE
 */

BundleInsideHelper.forInsideLintJar(project)

plugins {
    id(ConventionEnum.AndroidLint)
    id(ConventionEnum.JvmJUnit4)
    id(ConventionEnum.JvmDokka)
}

dependencies {
    bundleInside(projects.commonLint)
}
