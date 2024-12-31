plugins {
    application
}

group = "at.simstoe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":api"))
    implementation("org.jline:jline:3.21.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass = "at.simstoe.cloud.launcher.CloudLauncher"
}

tasks.test {
    useJUnitPlatform()
}
