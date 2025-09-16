plugins {
  alias(libs.plugins.android.application) apply false
  kotlin("android") version libs.versions.kotlin apply false
  kotlin("plugin.compose") version libs.versions.kotlin apply false
}
