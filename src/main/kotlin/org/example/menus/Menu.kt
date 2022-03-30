package org.example.menus

import java.util.*

abstract class Menu <T> {
    protected val input = Scanner(System.`in`)
    abstract fun run() :T
}