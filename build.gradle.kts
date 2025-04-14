plugins {
    kotlin("jvm") version "2.1.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(22)
}
application {
    mainClass.set("MainKt") // Note the `Kt` suffix
}
tasks.named<JavaExec>("run") {
    standardInput = System.`in`  // 👈 Enable interactive input
}