plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.0"

    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.guava:guava:31.0.1-jre")
    testImplementation("org.assertj:assertj-core:3.24.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    api("org.apache.commons:commons-math3:3.6.1")
}

tasks.test {
    useJUnitPlatform()
}
