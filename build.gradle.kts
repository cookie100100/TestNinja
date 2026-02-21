plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation( "junit:junit:4.13.2")
    testImplementation( "org.seleniumhq.selenium:selenium-java:4.18.1")
    testImplementation( "io.cucumber:cucumber-java:7.14.0")
    testImplementation( "io.cucumber:cucumber-junit:7.14.0")
    testImplementation( "io.github.bonigarcia:webdrivermanager:5.7.0")
    testImplementation( "org.slf4j:slf4j-simple:2.0.13")

}