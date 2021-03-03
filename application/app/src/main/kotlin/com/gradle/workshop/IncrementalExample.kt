package com.gradle.workshop

class IncrementalExample {

    fun hello(): String {
        val unused = "I generate a warning"
        return "hello"
    }
}
