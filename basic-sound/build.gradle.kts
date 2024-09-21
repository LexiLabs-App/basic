import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.binary.compatibility.validator)
    alias(libs.plugins.dokka)
    `maven-publish`
    signing
}

kotlin {

    // FORCES CHECK OF PUBLIC API DECLARATIONS
    explicitApi()

//    jvm()

    js {
        browser {
            webpackTask {
                mainOutputFileName = "shared.js"
            }
        }
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    listOf(
        iosX64(), // mobile
        iosArm64(), // mobile
        iosSimulatorArm64(), // mobile
        macosX64(), // desktop
        macosArm64(), // desktop
        tvosX64(), // tv
        tvosArm64(), // tv
        tvosSimulatorArm64(), // tv
        watchosX64(), // watch
        watchosArm32(), // watch
        watchosArm64(), // watch
        watchosDeviceArm64(), // watch
        watchosSimulatorArm64(), // watch
    ).forEach {
        it.binaries.framework {
            baseName = "basic-sound"
            isStatic = true
        }
    }

//    linuxX64 {
//        binaries.staticLib {
//            baseName = "basic-sound"
//        }
//    }
//
//
//    mingwX64 {
//        binaries.staticLib {
//            baseName = "basic-sound"
//        }
//    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.coroutines.test)
            implementation(project(":basic-logging"))
        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
        }

        appleMain.dependencies {  }

//        jvmMain.dependencies {}

        jsMain.dependencies {}
//
//        linuxMain.dependencies {
//            implementation(libs.ktor.client.curl)
//        }
//
//        mingwMain.dependencies {
//            implementation(libs.ktor.client.winhttp)
//        }

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

android {
    namespace = "app.lexilabs.basic.sound"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
