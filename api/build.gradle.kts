plugins {
}

group = "at.simstoe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("org.jetbrains:annotations:26.0.1")
}

java {
}

tasks.test {
    useJUnitPlatform()
}