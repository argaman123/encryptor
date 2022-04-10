package org.example.algorithm.caesar

import org.example.algorithm.ByteKey
import org.example.algorithm.DecryptionMethod
import org.example.algorithm.EncryptionMethod
import kotlin.random.Random

class CaesarAlgorithm {

    class Key(byte: Byte) : ByteKey(byte, "Caesar") {
        override fun encryption() = Encryption(this)
        override fun decryption() = Decryption(this)
    }

    class Encryption(key: Key = Key(Random.nextBytes(1)[0])) : EncryptionMethod<Key>(key) {
        override fun apply(index: Int, byte: Byte): Byte {
            val sum = (byte + key.byte).toByte()
            return (if (sum > Byte.MAX_VALUE)
                Byte.MIN_VALUE + (sum - Byte.MAX_VALUE - 1)
            else
                sum).toByte()
        }
    }

    class Decryption(key: Key) : DecryptionMethod<Key>(key) {
        override fun apply(index: Int, byte: Byte): Byte {
            val sum = (byte - key.byte).toByte()
            return (if (sum < Byte.MIN_VALUE)
                Byte.MAX_VALUE - (Byte.MIN_VALUE - sum - 1)
            else
                sum).toByte()
        }
    }
}