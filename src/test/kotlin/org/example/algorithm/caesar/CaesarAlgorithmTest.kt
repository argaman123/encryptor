package org.example.algorithm.caesar

import org.example.algorithm.caesar.CaesarAlgorithm
import org.example.algorithm.key.ByteKey
import org.example.utils.SystemIOMock
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.io.TempDir
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
        CaesarAlgorithm.Encryption().apply(file)
        assertTrue(Files.exists(file.toPath().resolveSibling("${file.name}.encrypted")))
        assertTrue(systemIOMock.consumeOutput() matches Regex("[\\n\\r.]*KEY: -?\\d+[\\n\\r.]*"))
    }

    @Test
    fun decrypt() {
        val file = Files.createFile(folderPath.resolve("decrypt.test")).toFile()
        file.writeText(encryptedText)
        val key: Byte = 15
        CaesarAlgorithm.Decryption(ByteKey(key)).apply(file)
        assertTrue(Files.exists(file.toPath().resolveSibling("${file.nameWithoutExtension}_decrypted.${file.extension}")))
    }

    @Test
    fun algorithmTest(){
        val file = Files.createFile(folderPath.resolve("algorithm.test")).toFile()
        file.writeText(encryptedText)
        CaesarAlgorithm.Encryption().apply(file)
        val encryptedFile = folderPath.resolve("algorithm.test.encrypted").toFile()
        val key = systemIOMock.consumeOutput().replace(Regex("[\\n\\r.]*KEY: (-?\\d+)[\\n\\r.]*"), "$1").toByte()
        CaesarAlgorithm.Decryption(ByteKey(key)).apply(encryptedFile)
        val decryptedFile = folderPath.resolve("algorithm_decrypted.test").toFile()
        assertTrue(decryptedFile.readText() == encryptedText)
    }

}