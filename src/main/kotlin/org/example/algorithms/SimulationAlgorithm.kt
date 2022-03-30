package org.example.algorithms

import java.io.File

class SimulationAlgorithm : Algorithm() {
    override fun encrypt(file: File) {
        println("encryption simulation of file ${file.name}")
    }

    override fun decrypt(file: File) {
        println("decryption simulation of file ${file.name}")
    }
}