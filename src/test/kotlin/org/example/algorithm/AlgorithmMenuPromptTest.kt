package org.example.algorithm

import org.example.algorithm.caesar.CaesarAlgorithm
import org.example.algorithm.caesar.CaesarPrompt
import org.example.utils.SystemIOMock
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach

internal class AlgorithmMenuPromptTest {
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

    @Test
    fun run() {
        systemIOMock
            .inputLine("1")
            .stopInput()
        assertTrue(AlgorithmMenuPrompt().run() is CaesarPrompt)
    }

}