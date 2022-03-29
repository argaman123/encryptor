package org.example

import java.io.File

enum class ActionChoice(val run: (File) -> Unit) {
    Encryption(fun(file: File) {
        println("Encryption simulation of file ${file.name}")
    }),
    Decryption(fun(file: File) {
        println("Decryption simulation of file ${file.name}")
    })
}
