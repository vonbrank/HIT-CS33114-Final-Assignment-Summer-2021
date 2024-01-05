import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

group = "cn.edu.hit"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.springframework.boot.starter.data.jdbc)
    implementation(libs.springframework.boot.starter.web)
    implementation(libs.springframework.boot.starter.validation)
    implementation(libs.fasterxml.jackson.module.kotlin)
    implementation(libs.jetbrains.kotlin.reflect)
    implementation(libs.auth0.java.jwt)
    implementation(libs.favre.bcrypt)
    runtimeOnly(libs.mysql.connector.java)
    testImplementation(libs.springframework.boot.starter.test)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
