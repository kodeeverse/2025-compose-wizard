import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("plugin.compose")
}

android {
  namespace = "boo.kodeeverse.composemagic"

  compileSdk {
    version = release(36)
  }

  defaultConfig {
    applicationId = "boo.kodeeverse.composemagic"
    minSdk = 24
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
  }

  buildFeatures {
    compose = true
  }
}

kotlin {
  compilerOptions {
    allWarningsAsErrors = true
    extraWarnings = true
    jvmTarget = JvmTarget.JVM_21
  }
}

composeCompiler {
  metricsDestination = project.layout.projectDirectory.dir("reports")
  reportsDestination = project.layout.projectDirectory.dir("reports")
}

dependencies {
  implementation(libs.androidx.activity)
  implementation(libs.androidx.core)

  implementation(libs.compose.activity)
  implementation(libs.compose.runtime)
  implementation(libs.compose.material3)
}
