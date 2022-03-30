package org.example

import java.io.*

class SystemIOMock {

    private lateinit var pos: PipedOutputStream
    private lateinit var pis: PipedInputStream
    private lateinit var inputStream :PrintStream
    private var outputStream :ByteArrayOutputStream = ByteArrayOutputStream()
    private val sysout: PrintStream = System.out
    private val sysin: InputStream = System.`in`

    fun log(text: Any){
        sysout.println(text)
    }

    fun inputLine(text: Any){
        inputStream.println(text)
    }

    fun stopInput(){
        inputStream.close()
    }

    fun consumeOutput() :String{
        val output = outputStream.toString()
        outputStream.reset()
        return output
    }

    fun enable(input: Boolean = true, output: Boolean = true){
        if (output) {
            System.setOut(PrintStream(outputStream))
        }
        if (input) {
            pos = PipedOutputStream()
            pis = PipedInputStream(pos)
            inputStream = PrintStream(pos)
            System.setIn(pis)
        }
    }

    fun disable(input: Boolean = true, output: Boolean = true){
        if (output) System.setOut(sysout)
        if (input) System.setIn(sysin)
    }

}