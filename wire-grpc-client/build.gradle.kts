import org.jetbrains.kotlin.gradle.dsl.JsModuleKind
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
  kotlin("multiplatform")
}

kotlin {
  applyDefaultHierarchyTemplate()

  jvm()
  if (System.getProperty("kjs", "true").toBoolean()) {
    js(IR) {
      configure(listOf(compilations.getByName("main"), compilations.getByName("test"))) {
        tasks.named<Kotlin2JsCompile>(compileKotlinTaskName).configure {
          compilerOptions {
            moduleKind.set(JsModuleKind.MODULE_UMD)
            sourceMap.set(true)
          }
        }
      }
      nodejs()
      browser()
    }
  }
  if (System.getProperty("knative", "true").toBoolean()) {
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    // Required to generate tests tasks: https://youtrack.jetbrains.com/issue/KT-26547
    linuxX64()
    macosX64()
    macosArm64()
    mingwX64()
    tvosX64()
    tvosArm64()
    tvosSimulatorArm64()
  }
  sourceSets {
    val commonMain by getting {
      dependencies {
        api(projects.wireRuntime)
        api(libs.okio.core)
        api(libs.kotlin.coroutines.core)
      }
    }
    val jvmMain by getting {
      dependencies {
        api(libs.okhttp.core)
      }
    }
  }
}

// TODO(egorand): Remove when https://github.com/srs/gradle-node-plugin/issues/301 is fixed
repositories.whenObjectAdded {
  if (this is IvyArtifactRepository) {
    metadataSources {
      artifact()
    }
  }
}
