import java.lang.Thread.sleep

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.8.1/userguide/building_java_projects.html
 */

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.4.20"

    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:29.0-jre")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    // Define the main class for the application.
    mainClass.set("com.gradle.workshop.AppKt")
}

tasks {
    whenTaskAdded {
        // here we can change/disable taks behaviour on the fly
        if (name == "IamSkipped") {
            enabled = false
        }
    }
    register("sayHello") {
        println("careful :-) weird behaviour")  // <-- Run during configuration phase
        doFirst {
            println("Proper place to say : hello!")
        }
        doLast {
            println("good bye...")
        }
    }
    // An Exec task example
    val listDir = register<Exec>("listDir") {
        group = "mycustomgroup"
        commandLine(
            "ls", "-l"
        )
    }
    val skippedTask = register("IamSkipped") {
        doLast {
            println("will never show")
        }
    }
    register("IamIncremental") {
        val fileCount = 10
        inputs.property("fileCount", fileCount)
        val generatedFileDir = file("$buildDir/generated")
        outputs.dir(generatedFileDir)
        doLast {
            generatedFileDir.mkdirs()
            for (i in 0..fileCount) {
                println("Create file $i and sleep")
                sleep(1000)
                val file = File(generatedFileDir, "${i}.txt")
                file.writeText("content:${i}")
                file.createNewFile()
            }
        }
    }
    register<Copy>("copyFile") {
        group = "mycustomgroup"
        from(file("$rootDir/app/src/test/resources/some-file.txt"))
        into(buildDir)
        dependsOn(listDir, skippedTask)
    }


}

tasks.register("sayHelloAgain") {
    doFirst {
        println("Just showing different syntax")
    }
}
