import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id("com.android.application") version "8.13.0"
  kotlin("android") version "2.2.20"
  kotlin("plugin.compose") version "2.2.20"
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
    jvmTarget = JvmTarget.JVM_21
  }
}

composeCompiler {
  metricsDestination = project.layout.projectDirectory.dir("reports")
  reportsDestination = project.layout.projectDirectory.dir("reports")
}

dependencies {
  implementation("androidx.activity:activity-ktx:1.11.0")
  implementation("androidx.activity:activity-compose:1.11.0")

  implementation("androidx.compose.runtime:runtime:1.9.3")
  implementation("androidx.compose.material3:material3:1.5.0-alpha06")

  // https://github.com/jisungbin/kotlin-dumper
  kotlinCompilerPluginClasspath("land.sungbin:kotlin-dumper:0.1.7")
}
