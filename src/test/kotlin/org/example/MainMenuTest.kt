package org.example

import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.*
import kotlin.test.assertContains


class MainMenuTest {

    private val sysin = System.`in`
    private val sysout = System.out
    private var pos = PipedOutputStream()
    private var pis = PipedInputStream(pos)
    private var inputStream = PrintStream(pos)
    private var outputStream = ByteArrayOutputStream()

    @get:Rule
    val folder = TemporaryFolder()

    private fun setup(){
        pos = PipedOutputStream()
        pis = PipedInputStream(pos)
        inputStream = PrintStream(pos)
        outputStream = ByteArrayOutputStream()
        System.setIn(pis)
        System.setOut(PrintStream(outputStream))
    }

    private fun cleanup() {
        System.setOut(sysout)
        System.setIn(sysin)
    }

    @Test
    fun testGetUserChoices_CorrectActionAndFile() {
        setup()
        val mainMenu = MainMenu()
        val actionChoice = ActionChoice.values().size
        val file = folder.newFile("file.test").absolutePath
        inputStream.println(actionChoice)
        inputStream.println(file)
        inputStream.close()
        val userChoices :MainMenu.UserChoices = mainMenu.getUserChoices()
        assertContains(ActionChoice.values(), userChoices.action)
        assertTrue(userChoices.file.exists())
        assertTrue(userChoices.file.isFile)
        cleanup()
    }

    @Test
    fun testGetUserChoices_WrongAction() {
        setup()
        val wrongAction = ActionChoice.values().size+1
        inputStream.println(wrongAction)
        inputStream.close()
        try {
            MainMenu().getUserChoices()
        } catch (e: java.util.NoSuchElementException) {
            assertTrue(outputStream.toString().contains(MainMenu.Errors.badActionChoice))
        }
        cleanup()

    }

    @Test
    fun testGetUserChoices_WrongFile() {
        setup()
        val actionChoice = ActionChoice.values().size
        inputStream.println(actionChoice)
        inputStream.println(File(folder.root, "nothing.test").absolutePath)
        inputStream.close()
        try {
            MainMenu().getUserChoices()
        } catch (e: java.util.NoSuchElementException) {
            assertTrue(outputStream.toString().contains(MainMenu.Errors.badSourceFile))
        }
        cleanup()
    }

}