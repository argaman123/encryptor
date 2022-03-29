package org.example

import junit.framework.TestCase
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream
import java.io.PrintStream
import kotlin.test.assertContains


class MainMenuTest : TestCase(){

    private val sysin = System.`in`
    private val sysout = System.out
    private var pos = PipedOutputStream()
    private var pis = PipedInputStream(pos)
    private var inputStream = PrintStream(pos)
    private var outputStream = ByteArrayOutputStream()

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
        val actionChoice = "1"
        val file = "C:\\Users\\argam\\IdeaProjects\\encryptor\\src\\main\\kotlin\\org\\example\\test"
        inputStream.println(actionChoice)
        inputStream.println(file)
        val userChoices :MainMenu.UserChoices = mainMenu.getUserChoices()
        assertContains(ActionChoice.values(), userChoices.action)
        assertTrue(userChoices.file.exists())
        assertTrue(userChoices.file.isFile)
        cleanup()
    }

    @Test
    fun testGetUserChoices_WrongAction() {
        setup()
        val wrongAction = "0"
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
        val actionChoice = "1"
        val file = "none"
        inputStream.println(actionChoice)
        inputStream.println(file)
        inputStream.close()
        try {
            MainMenu().getUserChoices()
        } catch (e: java.util.NoSuchElementException) {
            assertTrue(outputStream.toString().contains(MainMenu.Errors.badSourceFile))
        }
        cleanup()
    }

}