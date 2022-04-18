package org.example.prompts

import org.example.utils.SystemIOMock
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach

internal class RepeatedInputPromptTest {

    companion object {
        private var systemIOMock = SystemIOMock()

        @JvmStatic
        @BeforeAll
        fun enableIOMock() {
            systemIOMock.enable()
        }

        @JvmStatic
        @AfterAll
        fun disableIOMock() {
            systemIOMock.disable()
        }
    }

    @BeforeEach
    private fun setup() {
        systemIOMock.enable()
    }

    class AllowedException : Exception("ALLOWED EXCEPTION TEXT")
    class NotAllowedException : Exception("NOT ALLOWED EXCEPTION TEXT")

    class RepeatedInputPromptTester :
        RepeatedInputPrompt<String>("ENTER STRING: ", AllowedException(), convert = fun(text): String {
            when (text) {
                "ALLOWED ERROR" -> throw AllowedException()
                "NOT ALLOWED ERROR" -> throw NotAllowedException()
                else -> return text
            }
        })

    /*private val repeatedInputPrompt = RepeatedInputPrompt("ENTER STRING: ", AllowedException()) {
        when (it) {
            "ALLOWED ERROR" -> throw AllowedException()
            "NOT ALLOWED ERROR" -> throw NotAllowedException()
            else -> it
        }
    }*/

    @Test
    fun run() {
        systemIOMock
            .inputLine("TEST")
            .stopInput()
        assertTrue(RepeatedInputPromptTester().run() == "TEST")
    }

    @Test
    fun runAllowedError() {
        systemIOMock
            .inputLine("ALLOWED ERROR")
            .stopInput()
        try {
            RepeatedInputPromptTester().run()
            throw java.lang.AssertionError()
        } catch (e: java.util.NoSuchElementException) {
            assertTrue(systemIOMock.consumeOutput().contains("ERROR: ALLOWED EXCEPTION TEXT"))
        }
    }

    @Test
    fun runNotAllowedError() {
        systemIOMock
            .inputLine("NOT ALLOWED ERROR")
            .stopInput()
        try {
            RepeatedInputPromptTester().run()
        } catch (_: NotAllowedException) {
        }
    }
}