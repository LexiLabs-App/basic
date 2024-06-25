import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.binary.compatibility.validator)
    `maven-publish`
    signing
}

// THIS IS REQUIRED TO PREVENT the ":shared:testClasses" error
//task("testClasses")

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
//    TODO: enable when KTOR is available for WASM
//    wasmJs {
//        browser()
//        binaries.executable()
//    }

    listOf(
        iosX64(), // mobile
        iosArm64(), // mobile
        iosSimulatorArm64(), // mobile
        macosX64(), // desktop
        macosArm64() // desktop
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

//    linuxX64 {
//        binaries.staticLib {
//            baseName = "shared"
//        }
//    }
//
//
//    mingwX64 {
//        binaries.staticLib {
//            baseName = "shared"
//        }
//    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.coroutines.test)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
        }

        iosMain.dependencies {
        }

        macosMain.dependencies {
        }

//        jvmMain.dependencies {
//            implementation(libs.kotlinx.coroutines.swing)
//        }
//
//        jsMain.dependencies {
//        }
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
//        publishLibraryVariants("release", "debug")
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            pom {
                name = "Basic-Sound"
                groupId = "app.lexilabs.basic"
                artifactId = "basic-sound"
                version = "0.1.0"
                description =
                    "Integrate audio across all your Kotlin Multiplatform apps with a single library"
                url = "https://github.com/LexiLabs-Apps/basic-sound"
//                properties = mapOf(
//                    "myProp" to "value",
//                    "prop.with.dots" to "anotherValue"
//                )
                from(components["kotlin"])
                licenses {
                    license {
                        name = "MIT License"
                        url = "https://sound.basic.lexilabs.app/LICENSE"
                    }
                }
                developers {
                    developer {
                        id = "robertjamison"
                        name = "Robert Jamison"
                        email = "dev@lexilabs.app"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/LexiLabs-Apps/basic-sound.git"
                    developerConnection =
                        "scm:git:ssh://github.com:LexiLabs-Apps/basic-sound.git"
                    url = "https://github.com/LexiLabs-Apps/basic-sound"
                }
            }
        }
    }
    repositories {
        maven {
            url = uri(gradleLocalProperties(rootDir).getProperty("mavenServer"))
            name = "maven"
            credentials {
                username = gradleLocalProperties(rootDir).getProperty("mavenUsername")
                password = gradleLocalProperties(rootDir).getProperty("mavenPassword")
            }
        }
    }
}
signing {
    useGpgCmd()
    sign(publishing.publications["maven"])
}