package org.example.algorithm.caesar

import org.example.algorithm.DecryptionMethod
import org.example.algorithm.EncryptionMethod
import org.example.algorithm.key.ByteKey

class CaesarAlgorithm {

    class Encryption(key: ByteKey = ByteKey()) : EncryptionMethod<ByteKey>(key) {
        override fun apply(index: Int, byte: Byte): Byte {
            val sum = (byte + key.byte).toByte()
            return (if (sum > Byte.MAX_VALUE)
                Byte.MIN_VALUE + (sum - Byte.MAX_VALUE - 1)
            else
                sum).toByte()
        }
    }

    class Decryption(key: ByteKey) : DecryptionMethod<ByteKey>(key) {
        override fun apply(index: Int, byte: Byte): Byte {
            val sum = (byte - key.byte).toByte()
            return (if (sum < Byte.MIN_VALUE)
                Byte.MAX_VALUE - (Byte.MIN_VALUE - sum - 1)
            else
                sum).toByte()
        }
    }
}