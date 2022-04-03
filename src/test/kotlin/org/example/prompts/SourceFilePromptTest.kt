package org.example.prompts

import org.example.prompts.SourceFilePrompt.BadSourceFile
import org.example.utils.SystemIOMock
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path

internal class SourceFilePromptTest {

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
    fun runFileExists() {
        val fileChoice = Files.createFile(folderPath.resolve("file.test"))
        systemIOMock
            .inputLine(fileChoice.toAbsolutePath())
            .stopInput()
        val file = SourceFilePrompt().run()
        assertTrue(file.absolutePath == fileChoice.toAbsolutePath().toString())
        assertTrue(file.exists())
        assertTrue(file.isFile)
    }

    @Test
    fun runFileNotExist() {
        systemIOMock
            .inputLine(folderPath.resolve("nothing.test").toAbsolutePath())
            .stopInput()
        try {
            SourceFilePrompt().run()
        } catch (e: java.util.NoSuchElementException) {
            assertTrue(systemIOMock.consumeOutput().contains(BadSourceFile().message!!))
        }
    }
}