package org.example.algorithm

import org.example.utils.SystemIOMock
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

class EmptyKey : AlgorithmKey("empty") {
    override fun encryption() = EncryptionTester()
    override fun decryption() = DecryptionTester()
    override fun toString() = ""
}

class EncryptionTester : EncryptionMethod<EmptyKey>(EmptyKey()) {
    override fun apply(index: Int, byte: Byte): Byte {
        return 0
    }
}

class DecryptionTester : DecryptionMethod<EmptyKey>(EmptyKey()) {
    override fun apply(index: Int, byte: Byte): Byte {
        return 0
    }
}

internal class TimingObserverTest {
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

    @TempDir
    lateinit var folderPath: Path

    @Test
    fun updateEncryption() {
        val testFile = folderPath.resolve("encryption.test").toFile()
        testFile.createNewFile()
        val encryption = EncryptionTester()
        encryption.addObserver(TimingObserver())
        encryption.apply(testFile)
        val output = systemIOMock.consumeOutput()
        systemIOMock.log(output)
        assertTrue(
            output matches Regex(
                "[\\s\\S]*Encryption started successfully" +
                        "[\\s\\S]*Encryption finished successfully, took \\d+ms" +
                        "[\\s\\S]*"
            )
        )
    }

    @Test
    fun updateDecryption() {
        val testFile = folderPath.resolve("decryption.test").toFile()
        testFile.createNewFile()
        val decryption = DecryptionTester()
        decryption.addObserver(TimingObserver())
        decryption.apply(testFile)
        val output = systemIOMock.consumeOutput()
        systemIOMock.log(output)
        assertTrue(
            output matches Regex(
                "[\\s\\S]*Decryption started successfully" +
                        "[\\s\\S]*Decryption finished successfully, took \\d+ms" +
                        "[\\s\\S]*"
            )
        )
    }

}