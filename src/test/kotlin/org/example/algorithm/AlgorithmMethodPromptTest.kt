package org.example.algorithm

import org.example.algorithm.caesar.CaesarAlgorithm
import org.example.prompts.ActionMenuPrompt
import org.example.utils.SystemIOMock
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.nio.file.Path

internal class AlgorithmMethodPromptTest {
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

    @TempDir
    lateinit var folderPath: Path

    @Test
    fun runEncryption() {
        systemIOMock
            .inputLine("1")
            .stopInput()
        assertTrue(AlgorithmMethodPrompt(ActionMenuPrompt.ActionChoice.Encryption).run() is CaesarAlgorithm.Encryption)
    }

    @Test
    fun runDecryption() {
        val keyPath = folderPath.resolve("key.bin").toAbsolutePath().toString()
        val f = FileOutputStream(File(keyPath))
        val o = ObjectOutputStream(f)
        o.writeObject(CaesarAlgorithm.Key(0))
        o.close()
        f.close()
        systemIOMock
            .inputLine(keyPath)
            .stopInput()
        assertTrue(AlgorithmMethodPrompt(ActionMenuPrompt.ActionChoice.Decryption).run() is CaesarAlgorithm.Decryption)
    }

}