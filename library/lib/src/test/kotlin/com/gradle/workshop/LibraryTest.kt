/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.gradle.workshop

import kotlin.test.Test
import kotlin.test.assertTrue

class LibraryTest {
    @Test fun testSomeLibraryMethod() {
        val classUnderTest = Library()
        assertTrue(classUnderTest.someLibraryMethod(), "someLibraryMethod should return 'true'")
    }

    @Test fun testShowSomeColor() {
        val classUnderTest = Library()
        classUnderTest.showSomeColor()
    }
}
