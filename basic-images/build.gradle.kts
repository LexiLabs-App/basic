import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.binary.compatibility.validator)
    alias(libs.plugins.dokka)
//    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
    `maven-publish`
    signing
}

kotlin {

    // FORCES CHECK OF PUBLIC API DECLARATIONS
    explicitApi()

//    jvm()
//
//    js {
//        browser {
//            webpackTask {
//                mainOutputFileName = "shared.js"
//            }
//        }
//        binaries.executable()
//    }
//
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        browser()
//        binaries.executable()
//    }

    listOf(
        iosX64(), // mobile
        iosArm64(), // mobile
        iosSimulatorArm64(), // mobile
        macosX64(), // desktop
        macosArm64(), // desktop
//        tvosX64(), // tv
//        tvosArm64(), // tv
//        tvosSimulatorArm64(), // tv
//        watchosX64(), // watch
//        watchosArm32(), // watch
//        watchosArm64(), // watch
//        watchosDeviceArm64(), // watch
//        watchosSimulatorArm64(), // watch
    ).forEach {
        it.binaries.framework {
            baseName = "basic-images"
            isStatic = true
        }
    }

//    linuxX64 {
//        binaries.staticLib {
//            baseName = "basic-images"
//        }
//    }
//
//    mingwX64 {
//        binaries.staticLib {
//            baseName = "basic-images"
//        }
//    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":basic-logging"))
            implementation(libs.compose.foundation)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.resources)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.ktor.client.ios)
        }
        macosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.ktor.client.ios)
        }
//        jvmMain.dependencies {}
//        jsMain.dependencies {}
//        linuxMain.dependencies {}
//        mingwMain.dependencies {}
    }

    //https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    targets.withType<KotlinNativeTarget> {
        compilations["main"].compileTaskProvider.configure{
            compilerOptions {
                freeCompilerArgs.add("-Xexport-kdoc")
            }
        }
    }

    // https://youtrack.jetbrains.com/issue/KT-61573
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    // Android JVM target target options
    androidTarget {
        publishLibraryVariants("release", "debug")
        compilations.all{
            compileTaskProvider.configure{
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
    }
}

composeCompiler {
    enableStrongSkippingMode = true

    reportsDestination = layout.buildDirectory.dir("compose_compiler")
    stabilityConfigurationFile = rootProject.layout.projectDirectory.file("stability_config.conf")
}

android {
    namespace = "app.lexilabs.basic.images"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures{
        compose = true
    }
}