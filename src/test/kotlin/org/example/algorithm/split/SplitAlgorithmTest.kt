package org.example.algorithm.split

import org.example.algorithm.caesar.CaesarAlgorithm
import org.example.algorithm.xor.XORAlgorithm
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SplitAlgorithmTest {
    @Test
    fun encryptionApply(){
        val firstKey = CaesarAlgorithm.Key(2)
        val secondKey = XORAlgorithm.Key(2)
        val encryption = SplitAlgorithm.Encryption(SplitAlgorithm.Key(firstKey, secondKey))
        assertEquals(encryption.apply(0, 2), secondKey.encryption().apply(0, 2))
        assertEquals(encryption.apply(1, 2), firstKey.encryption().apply(1, 2))
    }

    @Test
    fun decryptionApply(){
        val firstKey = CaesarAlgorithm.Key(2)
        val secondKey = XORAlgorithm.Key(2)
        val decryption = SplitAlgorithm.Decryption(SplitAlgorithm.Key(firstKey, secondKey))
        assertEquals(decryption.apply(0, 2), secondKey.decryption().apply(0, 2))
        assertEquals(decryption.apply(1, 2), firstKey.decryption().apply(1, 2))
    }
}