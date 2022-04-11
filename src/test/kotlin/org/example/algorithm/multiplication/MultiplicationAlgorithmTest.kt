package org.example.algorithm.multiplication

import org.example.algorithm.AlgorithmKey
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MultiplicationAlgorithmTest {
    @Test
    fun encryptionApply(){
        val encryption = MultiplicationAlgorithm.Encryption()
        assertEquals(encryption.apply(0, 1), encryption.key.byte)
    }

    @Test
    fun decryptionApply(){
        val decryption = MultiplicationAlgorithm.Decryption(MultiplicationAlgorithm.Key(3))
        assertEquals(decryption.apply(0, 3), 1)
    }

    @Test
    fun illegalKey(){
        assertThrows(AlgorithmKey.Illegal::class.java, fun() {
            MultiplicationAlgorithm.Key(2)
        })
    }
}