package org.example.algorithm.xor

import org.example.algorithm.multiplication.MultiplicationAlgorithm
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class XORAlgorithmTest {
    @Test
    fun encryptionApply(){
        val encryption = XORAlgorithm.Encryption()
        assertEquals(encryption.apply(0, 0), encryption.key.byte)
    }

    @Test
    fun decryptionApply(){
        val decryption = XORAlgorithm.Decryption(XORAlgorithm.Key(5))
        assertEquals(decryption.apply(0, 2), 7)
    }

}