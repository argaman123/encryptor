package org.example.algorithm.doubleAlg

import org.example.algorithm.caesar.CaesarAlgorithm
import org.example.algorithm.xor.XORAlgorithm
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DoubleAlgorithmTest {
    @Test
    fun encryptionApply(){
        val encryption = DoubleAlgorithm.Encryption(DoubleAlgorithm.Key(CaesarAlgorithm.Key(1), XORAlgorithm.Key(0)))
        assertEquals(encryption.apply(0, 0), 1)
    }

    @Test
    fun decryptionApply(){
        val decryption = DoubleAlgorithm.Decryption(DoubleAlgorithm.Key(CaesarAlgorithm.Key(1), XORAlgorithm.Key(0)))
        assertEquals(decryption.apply(0, 1), 0)
    }
}