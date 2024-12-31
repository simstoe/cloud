plugins {
    application
}

group = "at.simstoe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/LodiaNET-Development/smash-api")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

java {
    modularity.inferModulePath = true
}

dependencies {
    implementation(project(":api"))
    implementation("org.jline:jline:3.21.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainModule = "at.simstoe.cloud.launcher"
    mainClass = "at.simstoe.cloud.launcher.CloudLauncher"
}

tasks.test {
    useJUnitPlatform()
}
