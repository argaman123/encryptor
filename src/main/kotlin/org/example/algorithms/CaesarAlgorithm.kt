package org.example.algorithms

import java.io.File
import java.util.Scanner
import kotlin.random.Random

class CaesarAlgorithm : Algorithm() {
    override fun encrypt(file: File) {
        val key = Random.nextBytes(1)[0]
        println("KEY: $key")
        val fileBytes = file.readBytes()
        fileBytes.forEachIndexed { i, byte ->
            val sum = byte + key
            fileBytes[i] = (if (sum > Byte.MAX_VALUE) (Byte.MIN_VALUE + (sum - Byte.MAX_VALUE - 1)) else sum).toByte()
        }
        /* MAXVALUE + 1 => MINVALUE
         MAXVALUE + X => MINVALUE + (X-1)
         SUM = MAXVALUE + X
         X = SUM - MAXVALUE
         SUM => MINVALUE + (SUM - MAXVALUE - 1)*/
        File(file.absolutePath+".encrypted").writeBytes(fileBytes)
    }

    override fun decrypt(file: File) {
        print("Enter the key: ")
        var key :Byte = 0
        try {
             key = Scanner(System.`in`).nextByte()
        } catch (e: java.lang.Exception){
            throw IllegalKey()
        }
        val fileBytes = file.readBytes()
        fileBytes.forEachIndexed { i, byte ->
            val sum = byte - key
            fileBytes[i] = (if (sum < Byte.MIN_VALUE) (Byte.MAX_VALUE - (Byte.MIN_VALUE - sum - 1)) else sum).toByte()
        }
        /* MINVALUE - 1 => MAXVALUE
         MINVALUE - X => MAXVALUE - (X-1)
         SUM = MINVALUE - X
         X = MINVALUE - SUM
         SUM => MAXVALUE - (MINVALUE - SUM - 1)*/
        val originalFilePath = file.absolutePath.removeSuffix(".encrypted")
        val lastDot = originalFilePath.lastIndexOf(".")
        val originalFileName = originalFilePath.substring(0, lastDot)
        val originalFileExtension = originalFilePath.substring(lastDot)
        File("${originalFileName}_decrypted$originalFileExtension").writeBytes(fileBytes)
    }
}