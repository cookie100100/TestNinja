plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation( "org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation( "org.seleniumhq.selenium:selenium-java:4.41.0")
    testImplementation( "io.cucumber:cucumber-java:7.14.0")
    testImplementation( "io.cucumber:cucumber-junit-platform-engine:7.14.0")
    testImplementation("org.junit.platform:junit-platform-suite:1.10.2")
    testImplementation( "io.github.bonigarcia:webdrivermanager:5.7.0")
    testImplementation("org.slf4j:slf4j-api:2.0.17")
}
tasks.test{
    useJUnitPlatform()
}