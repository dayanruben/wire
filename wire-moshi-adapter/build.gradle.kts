plugins {
  kotlin("jvm")
}

dependencies {
  implementation(projects.wireRuntime)
  api(libs.moshi)
  testImplementation(projects.wireTestUtils)
  testImplementation(libs.assertk)
  testImplementation(libs.junit)
  testImplementation(libs.moshiKotlin)
  testImplementation(libs.truth)
}
