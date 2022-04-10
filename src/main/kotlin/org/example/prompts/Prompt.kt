package org.example.prompts

import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.util.*

abstract class Prompt <T> {
    protected val input = Scanner(System.`in`)
    abstract fun run() :T
    companion object {
        fun readKey(file: File): Any {
            val fi = FileInputStream(file)
            val oi = ObjectInputStream(fi)
            val key = oi.readObject()
            oi.close()
            fi.close()
            return key
        }
    }
}