package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.io.TempDir
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.assertContains

class MainMenuTest {

    private val sysin = System.`in`
    private val sysout = System.out
    private var pos = PipedOutputStream()
    private var pis = PipedInputStream(pos)
    private var inputStream = PrintStream(pos)
    private var outputStream = ByteArrayOutputStream()

    @TempDir
    lateinit var folderPath: Path

    @BeforeEach
    private fun setup(){
        pos = PipedOutputStream()
        pis = PipedInputStream(pos)
        inputStream = PrintStream(pos)
        outputStream = ByteArrayOutputStream()
        System.setIn(pis)
        System.setOut(PrintStream(outputStream))
    }

    @AfterEach
    private fun cleanup() {
        System.setOut(sysout)
        System.setIn(sysin)
    }

    @Test
    fun testGetUserChoices_CorrectActionAndFile() {
        val mainMenu = MainMenu()
        val actionChoice = ActionChoice.values().size
        val file = Files.createFile(folderPath.resolve("file.test")).toAbsolutePath()
        inputStream.println(actionChoice)
        inputStream.println(file)
        inputStream.close()
        val userChoices :MainMenu.UserChoices = mainMenu.getUserChoices()
        assertContains(ActionChoice.values(), userChoices.action)
        Assertions.assertTrue(userChoices.file.exists())
        Assertions.assertTrue(userChoices.file.isFile)
    }

    @Test
    fun testGetUserChoices_WrongAction() {
        val wrongAction = ActionChoice.values().size+1
        inputStream.println(wrongAction)
        inputStream.close()
        try {
            MainMenu().getUserChoices()
        } catch (e: java.util.NoSuchElementException) {
            Assertions.assertTrue(outputStream.toString().contains(MainMenu.badActionChoice))
        }
    }

    @Test
    fun testGetUserChoices_WrongFile() {
        val actionChoice = ActionChoice.values().size
        inputStream.println(actionChoice)
        inputStream.println(folderPath.resolve("nothing.test").toAbsolutePath())
        inputStream.close()
        try {
            MainMenu().getUserChoices()
        } catch (e: java.util.NoSuchElementException) {
            Assertions.assertTrue(outputStream.toString().contains(MainMenu.badSourceFile))
        }
    }

}