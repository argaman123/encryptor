package org.example.prompts

import java.util.*

abstract class Prompt <T> {
    protected val input = Scanner(System.`in`)
    abstract fun run() :T
}