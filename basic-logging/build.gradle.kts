import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.binary.compatibility.validator)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kover)
    `maven-publish`
    signing
}

// THIS IS REQUIRED TO PREVENT the ":shared:testClasses" error
//task("testClasses")

kotlin {

    // FORCES CHECK OF PUBLIC API DECLARATIONS
    explicitApi()

    jvm()

    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
        compilerOptions {
            freeCompilerArgs.add("-Xwasm-attach-js-exception")
        }
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
            baseName = "basic-logging"
            isStatic = true
        }
    }

    linuxX64 {
        binaries.staticLib {
            baseName = "basic-logging"
        }
    }

    mingwX64 {
        binaries.staticLib {
            baseName = "basic-logging"
        }
    }

    sourceSets {

        commonMain.dependencies {}
        androidMain.dependencies {}
        appleMain.dependencies {}
        jvmMain.dependencies {}
        jsMain.dependencies {}
        linuxMain.dependencies {}
        mingwMain.dependencies {}
    }

    //https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    targets.withType<KotlinNativeTarget> {
        compilations["main"].compileTaskProvider.configure{
            compilerOptions {
                freeCompilerArgs.add("-Xexport-kdoc")
            }
        }
    }

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
    namespace = "app.lexilabs.basic.logging"
    compileSdk = 34

    defaultConfig {
        minSdk = 1 // was 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}