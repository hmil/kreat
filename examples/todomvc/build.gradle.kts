buildscript {
    repositories {
        mavenCentral()
        jcenter()
    }
}
plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.50"
}
repositories {
    mavenCentral()
    mavenLocal()
    jcenter()
}
group = "fr.hmil.kreat"
version = "0.0.1"

kotlin {
    // jvm()
    js {
        browser {
        }
        nodejs {
        }
    }
    // For ARM, should be changed to iosArm32 or iosArm64
    // For Linux, should be changed to e.g. linuxX64
    // For MacOS, should be changed to e.g. macosX64
    // For Windows, should be changed to e.g. mingwX64
    macosX64("macos") {
        binaries {
            executable("todomvc") {
                linkerOpts = mutableListOf("-L${project.projectDir}/../../kreat/kreat-qt-driver", "-lkreat-qt-driver")
            }
        }
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("fr.hmil.kreat:kreat:0.0.1")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        js().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
        js().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        macosX64().compilations["main"].defaultSourceSet {
            dependencies {
            }
        }
        macosX64().compilations["test"].defaultSourceSet {
        }
    }
}