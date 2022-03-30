package org.example

import java.io.File

abstract class Algorithm {
    abstract fun encrypt(file: File)
    abstract fun decrypt(file: File)
}