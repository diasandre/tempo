plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.0"

    `java-library`
    `maven-publish`
}

group = "com.github.diasandre.tempo"
version = "0.1.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
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

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        )
    }
}

java {
    withSourcesJar()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.diasandre"
            artifactId = "tempo"
            version = "0.1.0"

            from(components["java"])
        }
    }
}
