plugins {
  kotlin("jvm") version "2.1.10"
  id("org.jetbrains.intellij.platform") version "2.5.0"
}

group = "ai.yaaif"
version = "0.1.0"

repositories {
  mavenCentral()
  intellijPlatform { defaultRepositories() }
}

dependencies {
  intellijPlatform { intellijIdeaCommunity("2024.3.5") }
}

intellijPlatform {
  pluginConfiguration {
    id = "ai.yaaif.jetbrains"
    name = "YAAIF Platform"
    version = project.version.toString()
    vendor {
      name = "YAAIF"
      url = "https://yaaif.ai"
    }
    ideaVersion {
      sinceBuild = "243"
    }
  }
}

kotlin { jvmToolchain(21) }
