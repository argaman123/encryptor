package org.example.prompts

import org.example.utils.SystemIOMock
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach

internal class MenuPromptTest {

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
    private fun setup(){
        systemIOMock.enable()
    }

    private enum class TestChoice {
        ChoiceOne, ChoiceTwo
    }

    private val testChoices = TestChoice.values()

    @Test
    fun runValidChoice() {
        val actionChoice = testChoices.size
        systemIOMock
            .inputLine(actionChoice)
            .stopInput()
        val action = MenuPrompt(testChoices).run()
        assertTrue(action in testChoices)
    }

    @Test
    fun runInvalidChoice() {
        val wrongAction = testChoices.size+1
        systemIOMock
            .inputLine(wrongAction)
            .stopInput()
        try {
            MenuPrompt(testChoices).run()
        } catch (e: java.util.NoSuchElementException) {
            assertTrue(systemIOMock.consumeOutput().contains(MenuPrompt.Errors.invalidChoice))
        }
    }
}