package org.example.algorithms

import org.example.menus.ActionMenu.ActionChoice.*
import org.example.utils.SystemIOMock
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

internal class CaesarAlgorithmTest {


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

    private val encryptedText = "Testing Caesar Algorithm"

    @Test
    fun encrypt() {
        val file = Files.createFile(folderPath.resolve("encrypt.test")).toFile()
        file.writeText(encryptedText)
        CaesarAlgorithm().run(file, Encryption)
        assertTrue(Files.exists(file.toPath().resolveSibling("${file.name}.encrypted")))
        assertTrue(systemIOMock.consumeOutput() matches Regex("[\\n\\r.]*KEY: -?\\d+[\\n\\r.]*"))
    }

    @Test
    fun decrypt() {
        val file = Files.createFile(folderPath.resolve("decrypt.test")).toFile()
        file.writeText(encryptedText)
        val key = 15
        systemIOMock
            .inputLine(key)
            .stopInput()
        CaesarAlgorithm().run(file, Decryption)
        assertTrue(Files.exists(file.toPath().resolveSibling("${file.nameWithoutExtension}_decrypted.${file.extension}")))
    }

    @Test
    fun decryptIllegalKey() {
        val file = Files.createFile(folderPath.resolve("decryptIllegal.test")).toFile()
        file.writeText(encryptedText)
        val key = 21331223
        systemIOMock
            .inputLine(key)
            .stopInput()
        assertThrows(Algorithm.IllegalKey::class.java, fun() {
            CaesarAlgorithm().run(file, Decryption)
        })
    }

    @Test
    fun algorithmTest(){
        val file = Files.createFile(folderPath.resolve("algorithm.test")).toFile()
        file.writeText(encryptedText)
        CaesarAlgorithm().run(file, Encryption)
        val encryptedFile = folderPath.resolve("algorithm.test.encrypted").toFile()
        val key = systemIOMock.consumeOutput().replace(Regex("[\\n\\r.]*KEY: (-?\\d+)[\\n\\r.]*"), "$1").toByte()
        systemIOMock
            .inputLine(key)
            .stopInput()
        CaesarAlgorithm().run(encryptedFile, Decryption)
        val decryptedFile = folderPath.resolve("algorithm_decrypted.test").toFile()
        assertTrue(decryptedFile.readText() == encryptedText)
    }

}