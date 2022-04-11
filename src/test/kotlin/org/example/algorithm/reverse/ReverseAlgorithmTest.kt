package org.example.algorithm.reverse

import org.example.algorithm.multiplication.MultiplicationAlgorithm
import org.example.algorithm.xor.XORAlgorithm
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ReverseAlgorithmTest {
    @Test
    fun encryptionApply(){
        val testKey = XORAlgorithm.Key(2)
        val encryption = ReverseAlgorithm.Encryption(ReverseAlgorithm.Key(testKey))
        assertEquals(encryption.apply(0, 1), testKey.decryption().apply(0, 1))
    }

    @Test
    fun decryptionApply(){
        val testKey = XORAlgorithm.Key(2)
        val decryption = ReverseAlgorithm.Decryption(ReverseAlgorithm.Key(testKey))
        assertEquals(decryption.apply(0, 1), testKey.encryption().apply(0, 1))
    }
}