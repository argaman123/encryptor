package org.example.algorithm.caesar

import org.example.algorithm.AlgorithmKey
import org.example.algorithm.caesar.CaesarPrompt
import org.example.prompts.ActionMenuPrompt
import org.example.utils.SystemIOMock
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach

internal class CaesarPromptTest {

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
        //val caesarPrompt = CaesarPrompt(ActionMenuPrompt.ActionChoice.Decryption)
        val key = Byte.MIN_VALUE
        systemIOMock
            .inputLine(key)
            .stopInput()
        //val algorithm = caesarPrompt.run() as CaesarAlgorithm.Decryption
        //assertTrue(algorithm.key.byte == key)
    }

    @Test
    fun runIllegalKey() {
        //val caesarPrompt = CaesarPrompt(ActionMenuPrompt.ActionChoice.Decryption)
        val key = Byte.MAX_VALUE + 1
        systemIOMock
            .inputLine(key)
            .stopInput()
        try {
            //caesarPrompt.run()
        } catch (e: java.util.NoSuchElementException) {
            assertTrue(systemIOMock.consumeOutput().contains(AlgorithmKey.Illegal().message.toString()))
        }
    }


}